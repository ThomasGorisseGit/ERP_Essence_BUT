package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restock {
    @Id
    @GeneratedValue
    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date restockDate;

    private int quantity;

    @OneToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;



}
