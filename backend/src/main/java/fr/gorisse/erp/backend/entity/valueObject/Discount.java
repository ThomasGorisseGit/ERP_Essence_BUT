package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Value
public class Discount {
    int value;

    private Discount(int discount){
        this.value = discount;
    }
    public static Discount create(int discount){
        int[] possibleValues = {10,20,30,50};
        if(Arrays.stream(possibleValues).anyMatch(x-> x == discount)){
            return new Discount(discount);
        }
        throw new InvalidInput("Invalid discount value");

    }
}
