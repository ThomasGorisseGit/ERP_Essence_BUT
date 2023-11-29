package fr.gorisse.erp.backend.entity.valueObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import lombok.Value;

import java.util.Arrays;

@Value
public class SubscriptionPrice {
    double price;
    @JsonCreator
    private SubscriptionPrice(@JsonProperty("price") double price){
        this.price = price;
    }
    public static SubscriptionPrice create(double price){
        double[] possibleValues = {0.00,29.99, 39.99, 49.99, 54.99 };
        if(Arrays.stream(possibleValues).anyMatch(x-> x == price)){
            return new SubscriptionPrice(price);
        }
        throw new InvalidInput("Invalid price value");

    }
}
