package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

}
