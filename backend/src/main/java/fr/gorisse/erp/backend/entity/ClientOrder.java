package fr.gorisse.erp.backend.entity;

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
    private int id;

    @OneToMany(mappedBy = "clientOrder")
    private List<OrderList> orderLists;

    @ManyToOne(targetEntity = Client.class )
    private Client client;

    private long total;

    private boolean inProgress;


}
