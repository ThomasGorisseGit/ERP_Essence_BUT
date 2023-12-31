package fr.gorisse.erp.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.gorisse.erp.backend.entity.valueObject.Discount;
import fr.gorisse.erp.backend.entity.valueObject.SubscriptionPrice;
import fr.gorisse.erp.backend.entity.valueObject.converter.DiscountConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.SubscriptionPriceConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Subscription {
    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = DiscountConverter.class)
    private Discount discount;

    @Convert(converter = SubscriptionPriceConverter.class)
    private SubscriptionPrice price;

    private String name;

    @OneToMany(targetEntity = Client.class,mappedBy = "subscription")
    @JsonIgnore
    @JsonIgnoreProperties("subscription")
    private List<Client> clients;

    public Subscription(String name, SubscriptionPrice price, Discount discount){
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}
