package fr.gorisse.erp.backend.entity.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

import java.util.Arrays;

@Value
public class Discount {
    int value;

    @JsonCreator
    private Discount(@JsonProperty("value")int discount){
        this.value = discount;
    }
    public static Discount create(int discount){
        int[] possibleValues = {0,10,20,30,50};
        if(Arrays.stream(possibleValues).anyMatch(x-> x == discount)){
            return new Discount(discount);
        }
        throw new InvalidInput("Invalid discount value");

    }
}
