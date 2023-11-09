package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status,String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(status == null) return null;
        return status.toString();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        if(s==null) return null;
        return Status.create(s);
    }
}
