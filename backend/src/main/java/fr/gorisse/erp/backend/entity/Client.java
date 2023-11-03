package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.model.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



}
