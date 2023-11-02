package fr.gorisse.erp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private long price; // Long -> unsigned int
    private String description;

    @ManyToOne(targetEntity = Provider.class)
    private Provider provider;

    public Product (long price, String description){
        this.price = price;
        this.description = description;
    }
    public Product (long price){
        this.price = price;
        this.description = "";
    }


}
