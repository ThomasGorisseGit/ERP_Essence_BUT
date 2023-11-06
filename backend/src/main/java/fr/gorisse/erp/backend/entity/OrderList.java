package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderList {
    @Id
    private int id;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private ClientOrder clientOrder;

    private int quantity;


}
