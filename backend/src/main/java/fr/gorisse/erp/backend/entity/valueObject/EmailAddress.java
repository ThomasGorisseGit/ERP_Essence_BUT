package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

import java.util.regex.Pattern;

@Value
public class EmailAddress {
    String email;
    private EmailAddress(String email){
        this.email = email;
    }
    public static EmailAddress create(String email){
        if(Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches()){
            return new EmailAddress(email);
        }
        throw new InvalidInput("Email is not valid");
    }
    public String toString(){
        return this.email;
    }
}

