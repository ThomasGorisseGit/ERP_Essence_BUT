package fr.gorisse.erp.backend.entity.valueObject;


import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Login {
    private final String login;

    private Login(String login){
        this.login = login;
    }
    public static Login create(String login){
        final int maxSize = 15;
        final char[] authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_./#*@&éàùè'-".toCharArray();
        boolean isValidLogin = false;

        for(int i =0;i<login.length();i++){
            int j =0;
            while (j<authorizedChar.length && !isValidLogin){
                if(authorizedChar[j] == login.charAt(i))
                {
                    isValidLogin =true;
                }
                j++;
            }
        }
        if(isValidLogin && login.length()<=maxSize){
            return new Login(login);
        }
        throw new RuntimeException("invalid Login");
    }
    public String toString(){
        return this.login;
    }
}
