package fr.gorisse.erp.backend.entity.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class Person {
    private String firstName;
    private String lastName;


}
