package fr.gorisse.erp.backend.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Person {
    private String prenom;
    private String nom;


}
