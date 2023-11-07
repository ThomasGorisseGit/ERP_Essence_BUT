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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("orderLists")
    private Product product;

    @ManyToOne(targetEntity = ClientOrder.class)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderList")
    private ClientOrder clientOrder;

    private int quantity;

    public OrderList(Product product, ClientOrder clientOrder, int quantity){
        this.product = product;
        this.clientOrder = clientOrder;
        this.quantity = quantity;
    }

}
