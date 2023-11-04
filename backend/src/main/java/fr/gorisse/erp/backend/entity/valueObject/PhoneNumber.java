package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

import java.util.regex.Pattern;

@Value
public class PhoneNumber {
    String phoneNumber;
    private PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public static PhoneNumber create(String phoneNumber){
        if(Pattern.compile("^(?:(?:(?:\\+|00)33[ ]?(?:\\(0\\)[ ]?)?)|0){1}[1-9]{1}([ .-]?)(?:\\d{2}\\1?){3}\\d{2}$").matcher(phoneNumber).matches()){
            return new PhoneNumber(phoneNumber);
        }
        throw new InvalidInput("Invalid Phone number");
    }
    public String toString(){
        return this.phoneNumber;
    }
}
