package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import jakarta.persistence.Converter;
import jakarta.persistence.Embeddable;
import lombok.ToString;
import lombok.Value;

import java.util.Arrays;
import java.util.Objects;


@Value
public class Status {
    String userStatus;
    private Status(String userStatus ){
        this.userStatus = userStatus;
    }
    public static Status create(String userStatus){
        if(Arrays.stream(StatusSalary.values()).anyMatch(word -> Objects.equals(word.toString(), userStatus))){
            return new Status(userStatus);
        }
        throw new InvalidInput("Status is incorrect");
    }

    public static double getSalary(String userStatus){
        if(Arrays.stream(StatusSalary.values()).anyMatch(word -> Objects.equals(word.toString(), userStatus))){
            return StatusSalary.valueOf(userStatus).getSalary();
        }
        throw new InvalidInput("Status is incorrect");
    }
    public String toString(){
        return this.userStatus;
    }
}
