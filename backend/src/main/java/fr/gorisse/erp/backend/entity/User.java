package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.entity.valueObject.Password;
import fr.gorisse.erp.backend.entity.valueObject.converter.LoginConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.PasswordConverter;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = LoginConverter.class)
    private Login login;

    @Convert(converter = PasswordConverter.class)
    private Password password;

    public User (String prenom, String nom, String password){
        super(prenom,nom);
        this.login = Login.create(prenom+"@"+nom);
        this.password = Password.create(password);
    }

}
