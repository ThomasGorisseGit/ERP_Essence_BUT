package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

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
    @JsonIgnoreProperties(value = "stock",allowSetters = true)
    private Product product;

    private long quantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @UpdateTimestamp
    private Date date;

    @OneToMany(mappedBy = "stock")
    @JsonIgnoreProperties("stock")
    @JsonIgnore
    private List<Delivery> deliveryList;

    public Stock(int quantity){
        this.quantity = quantity;
    }

}
