package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

@Value
public class Password  {
    String password;

    private Password(String password) {
        this.password = password;
    }

    public static Password create(String password){
        final int maxSize = 200;
        if (password.length() > maxSize) {
            throw new InvalidInput(" Password must be less than "+maxSize+" characters ");
        }
        else{
            return new Password(password);
        }
    }

    public String toString(){
        return this.password;
    }



}
