package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.Discount;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DiscountConverter implements AttributeConverter<Discount,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Discount discount) {
        if(discount==null)return null;
        return discount.getValue();
    }

    @Override
    public Discount convertToEntityAttribute(Integer integer) {
        if(integer==null)return null;
        return Discount.create(integer);
    }
}
