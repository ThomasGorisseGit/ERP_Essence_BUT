package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.EmailAddress;
import fr.gorisse.erp.backend.entity.valueObject.PhoneNumber;
import fr.gorisse.erp.backend.entity.valueObject.converter.EmailAddressConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.PhoneNumberConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;

    @Convert(converter = EmailAddressConverter.class)
    private EmailAddress emailAddress;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    @JsonIgnore
    private List<ClientOrder> clientOrders;

    @ManyToOne(targetEntity = Subscription.class)
    private Subscription subscription;
}
