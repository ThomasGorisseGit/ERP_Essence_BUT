package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

@Value
public class Siret {
    String siret;
    private Siret(String siren, String nic){
        this.siret = siren + nic;
    }
    private Siret(String siret){
        this.siret = siret;
    }
    public static Siret create(String siren,String nic){
        final int sirenLength = 9;
        final int nicLength = 5;
        if(siren.length() != sirenLength){
            throw new InvalidInput("SIREN length is invalid");
        }
        if(nic.length() != nicLength){
            throw new InvalidInput("NIC length is invalid");
        }
        if(!hasValidChar(siren+nic)){
            throw new InvalidInput("SIREN or NIC is composed of invalid characters");
        }
        return new Siret(siren,nic);

    }
    public static Siret create(String siret){
        final int siretLength = 15;

        if(siret.length() != siretLength){
            throw new InvalidInput("SIRET length is invalid");
        }
        if(!hasValidChar(siret)){
            throw new InvalidInput("SIRET is composed of invalid characters");
        }
        return new Siret(siret);

    }

    private static boolean hasValidChar(String siret){
        boolean isValid = true;
        for(int i =0;i<siret.length();i++){
            char currentChar = siret.charAt(i);
            if (!contains(currentChar)) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
    private static boolean contains(char character){
        boolean founded = false;
        int i =0 ;
        final char[] authorizedChar = "123456789".toCharArray();

        while (i<authorizedChar.length && !founded){
            if(authorizedChar[i]==character) founded = true;
            i++;
        }
        return founded;
    }
    public String toString(){
        return this.siret;
    }
}
