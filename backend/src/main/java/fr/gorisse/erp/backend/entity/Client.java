package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.EmailAddress;
import fr.gorisse.erp.backend.entity.valueObject.PhoneNumber;
import fr.gorisse.erp.backend.entity.valueObject.converter.EmailAddressConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.PhoneNumberConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;

    @Convert(converter = EmailAddressConverter.class)
    private EmailAddress emailAddress;

}