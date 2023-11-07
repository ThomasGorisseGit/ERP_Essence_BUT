package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.SubscriptionPrice;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SubscriptionPriceConverter implements AttributeConverter<SubscriptionPrice,Double> {
    @Override
    public Double convertToDatabaseColumn(SubscriptionPrice subscriptionPrice) {
        if(subscriptionPrice == null) return null;
        return subscriptionPrice.getPrice();
    }

    @Override
    public SubscriptionPrice convertToEntityAttribute(Double aDouble) {
        return SubscriptionPrice.create(aDouble);
    }
}
