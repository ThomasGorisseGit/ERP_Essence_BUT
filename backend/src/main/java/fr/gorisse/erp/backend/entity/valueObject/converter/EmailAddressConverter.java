package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.EmailAddress;
import jakarta.persistence.AttributeConverter;

public class EmailAddressConverter implements AttributeConverter<EmailAddress,String> {
    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        if(emailAddress == null)return null;
        return emailAddress.toString();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String s) {
        if(s==null) return null;
        return EmailAddress.create(s);
    }
}
