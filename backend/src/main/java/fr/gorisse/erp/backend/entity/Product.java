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
    private double price; // Long -> unsigned int
    private String description;
    private String name;

    @ManyToOne(targetEntity = Provider.class)
    private Provider provider;

    public Product (double price, String description,String name){
        this.price = price;
        this.description = description;
        this.name = name;
    }
    public Product (double price,String name){
        this.price = price;
        this.name = name;
        this.description = "";
    }


}
