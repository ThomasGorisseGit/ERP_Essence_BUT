package fr.gorisse.erp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(mappedBy = "stock")
    private Product product;

    private long quantity;

}
