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
        if(Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\n\n").matcher(email).matches()){
            return new EmailAddress(email);
        }
        throw new InvalidInput("Email is not valid");
    }
    public String toString(){
        return this.email;
    }
}

