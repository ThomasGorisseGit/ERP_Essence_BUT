package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import jakarta.persistence.Converter;
import lombok.Value;

import java.util.Arrays;

@Value
public class SubscriptionPrice {
    double price;

    private SubscriptionPrice(double price){
        this.price = price;
    }
    public static SubscriptionPrice create(double price){
        double[] possibleValues = {29.99, 39.99, 49.99, 54.99 };
        if(Arrays.stream(possibleValues).anyMatch(x-> x == price)){
            return new SubscriptionPrice(price);
        }
        throw new InvalidInput("Invalid price value");

    }
}
