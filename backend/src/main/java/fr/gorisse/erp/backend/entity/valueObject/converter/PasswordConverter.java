package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<Password,String> {
    @Override
    public String convertToDatabaseColumn(Password password) {
        if(password == null) return null;
        return password.toString();
    }

    @Override
    public Password convertToEntityAttribute(String s) {
        if(s == null) return null;
        else return Password.create(s);
    }
}
