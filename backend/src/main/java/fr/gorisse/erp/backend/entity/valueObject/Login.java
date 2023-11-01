package fr.gorisse.erp.backend.entity.valueObject;


import fr.gorisse.erp.backend.exceptions.InvalidInput;
public class Login {
    private final String login;

    private Login(String login){
        this.login = login;
    }
    public static Login create(String login){
        final int maxSize = 15;
        if(login.length()>maxSize) {
            throw new InvalidInput(" Login must be less than "+maxSize+" characters");
        }


        final char[] authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_./#*@&éàùè'-123456789".toCharArray();
        boolean isValidLogin=false;

        for(int i =0;i<login.length();i++){
            isValidLogin = false;
            int j =0;
            while (j<authorizedChar.length && !isValidLogin){
                if(authorizedChar[j] == login.charAt(i))
                {
                    isValidLogin =true;
                }
                j++;
            }
        }
        if(isValidLogin){
            return new Login(login);
        }
        throw new InvalidInput(" The login contains invalid characters. ");
    }
    public String toString(){
        return this.login;
    }
}
