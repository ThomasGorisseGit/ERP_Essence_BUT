package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestValueObjects {
    @Test
    public void testLogin(){
        Login login;
        //Test the maxSize of login
        assertThrows(InvalidInput.class,()->Login.create("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") );

        //Test the invalid character
        assertThrows(InvalidInput.class,()->Login.create("{Thomas") );
        assertThrows(InvalidInput.class,()->Login.create("Thomas}") );
        assertThrows(InvalidInput.class,()->Login.create("Thom+as") );
        login = Login.create("Thomas");
        assertEquals(login.toString(),"Thomas");

    }
    @Test
    public void testPassword(){
        Password password;
        //Test the maxSize of the password
        assertThrows(InvalidInput.class,()->Password.create("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        password = Password.create("Thomas");
        /* TODO Add password hash
        assertNotEquals(password.toString(),"Thomas");
        Password checkEncryption = Password.create("Thomas");
        assertEquals(password.toString(),checkEncryption.toString());

        Password checkCaps = Password.create("thomas");
        assertNotEquals(password.toString(),checkCaps.toString());
        */

    }
    @Test
    public void testSiret(){
        Siret siret;
        String validSiret = "12354531231238";
        assertThrows(InvalidInput.class,()->Siret.create("1111111111","111"));
        assertThrows(InvalidInput.class,()->Siret.create("111111111111a1"));
        assertDoesNotThrow(()->Siret.create(validSiret));
        siret = Siret.create(validSiret);
        assertEquals(siret.getSiret(),validSiret);

    }
    @Test
    public void emailAddress(){
        EmailAddress emailAddress;
        String validEmail = "tho.go@gmail.com";
        String invalidEmail1 = "tttttt@tttt.t";
        String invalidEmail2 = "tttttt.tt";
        String invalidEmail3 = "@tttt.tt";
        assertThrows(InvalidInput.class,()->EmailAddress.create(invalidEmail1));
        assertThrows(InvalidInput.class,()->EmailAddress.create(invalidEmail2));
        assertThrows(InvalidInput.class,()->EmailAddress.create(invalidEmail3));
        assertDoesNotThrow(()->EmailAddress.create(validEmail));
        emailAddress = EmailAddress.create(validEmail);
        assertEquals(emailAddress.getEmail(),validEmail);

    }
    @Test
    public void phoneNumber(){
        PhoneNumber phoneNumber;
        String validPhone = "0607080919";
        String invalidPhone1 = "1007080919";
        String invalidPhone2 = "060708091920";
        String invalidPhone3 = "06070819";
        assertThrows(InvalidInput.class,()->PhoneNumber.create(invalidPhone1));
        assertThrows(InvalidInput.class,()->PhoneNumber.create(invalidPhone2));
        assertThrows(InvalidInput.class,()->PhoneNumber.create(invalidPhone3));
        assertDoesNotThrow(()->PhoneNumber.create(validPhone));
        phoneNumber = PhoneNumber.create(validPhone);
        assertEquals(phoneNumber.getPhoneNumber(),validPhone);

    }
}