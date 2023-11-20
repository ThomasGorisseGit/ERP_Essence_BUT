package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "provider")
@Entity
@JsonIgnoreProperties(value = {"orderLists","provider"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double price;
    private String description;
    private String name;

    @ManyToOne(targetEntity = Provider.class)
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id",referencedColumnName = "id")
    private Stock stock;

    @OneToMany(mappedBy = "product")
    private List<OrderList> orderLists;

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
