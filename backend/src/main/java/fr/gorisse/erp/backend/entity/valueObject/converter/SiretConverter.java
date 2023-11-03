package fr.gorisse.erp.backend.entity.valueObject.converter;

import fr.gorisse.erp.backend.entity.valueObject.Siret;
import jakarta.persistence.AttributeConverter;

public class SiretConverter implements AttributeConverter<Siret,String> {
    @Override
    public String convertToDatabaseColumn(Siret siret) {
        if(siret == null) return null;
        return siret.toString();
    }

    @Override
    public Siret convertToEntityAttribute(String s) {
        if(s==null) return null;
        return Siret.create(s);
    }
}
