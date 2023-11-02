package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestValueObjects {
    @Test
    public void testLogin(){
        Login login;
        //Test the maxSize of login
        Assertions.assertThrows(InvalidInput.class,()->Login.create("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") );

        //Test the invalid character
        Assertions.assertThrows(InvalidInput.class,()->Login.create("{Thomas") );
        Assertions.assertThrows(InvalidInput.class,()->Login.create("Thomas}") );
        Assertions.assertThrows(InvalidInput.class,()->Login.create("Thom+as") );
        login = Login.create("Thomas");
        Assertions.assertEquals(login.toString(),"Thomas");

    }
    @Test
    public void testPassword(){
        Password password;
        //Test the maxSize of the password
        Assertions.assertThrows(InvalidInput.class,()->Password.create("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        password = Password.create("Thomas");
        Assertions.assertNotEquals(password.toString(),"Thomas");
        Password checkEncryption = Password.create("Thomas");
        Assertions.assertEquals(password.toString(),checkEncryption.toString());

        Password checkCaps = Password.create("thomas");
        Assertions.assertNotEquals(password.toString(),checkCaps.toString());


    }
}