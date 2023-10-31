package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.valueObject.Login;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    private String login;


}
