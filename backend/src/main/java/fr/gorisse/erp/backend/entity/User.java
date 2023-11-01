package fr.gorisse.erp.backend.entity;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Convert(converter = LoginConverter.class)
    private Login login;

    @Convert(converter = PasswordConverter.class)
    private Password password;


}
