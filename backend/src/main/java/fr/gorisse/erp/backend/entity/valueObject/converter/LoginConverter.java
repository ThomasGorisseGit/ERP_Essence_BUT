package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.Login;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class LoginConverter implements AttributeConverter<Login, String> {

    @Override
    public String convertToDatabaseColumn(Login login) {
        return login == null ? null : login.toString();

    }

    @Override

    public Login convertToEntityAttribute(String potentialLogin) {
        return potentialLogin == null ? null : Login.create(potentialLogin);

    }
}
