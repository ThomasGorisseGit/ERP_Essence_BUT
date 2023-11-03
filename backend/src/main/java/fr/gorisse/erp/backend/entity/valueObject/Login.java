package fr.gorisse.erp.backend.entity.valueObject;


import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

@Value
public class Login {
    String login;

    private Login(String login){
        this.login = login;
    }
    public static Login create(String login){
        final int maxSize = 30;
        if(login.length()>maxSize) {
            throw new InvalidInput(" Login must be less than "+maxSize+" characters");
        }
        if(!hasValidChar(login))
        {
            throw new InvalidInput(" The login contains invalid characters. ");
        }

        return new Login(login);

    }
    private static boolean hasValidChar(String login){
        boolean isValid = true;
        for(int i =0;i<login.length();i++){
            char currentChar = login.charAt(i);
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
        final char[] authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_./#*@&éàùè'-123456789".toCharArray();

        while (i<authorizedChar.length && !founded){
            if(authorizedChar[i]==character) founded = true;
            i++;
        }
        return founded;
    }
    public String toString(){
        return this.login;
    }
}
