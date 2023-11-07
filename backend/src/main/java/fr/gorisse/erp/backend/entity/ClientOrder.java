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
@ToString
@Entity
public class ClientOrder {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "clientOrder")
    @JsonIgnoreProperties("clientOrder")
    private List<OrderList> orderList;

    @ManyToOne(targetEntity = Client.class )
    private Client client;

    private long total;

    private boolean inProgress;


}
