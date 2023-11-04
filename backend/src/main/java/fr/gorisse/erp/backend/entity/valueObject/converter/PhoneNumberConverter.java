package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.PhoneNumber;
import jakarta.persistence.AttributeConverter;

public class PhoneNumberConverter implements AttributeConverter<PhoneNumber,String> {
    @Override
    public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
        if(phoneNumber==null)return null;
        return phoneNumber.toString();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String s) {
        if(s==null)return null;
        return PhoneNumber.create(s);
    }
}
