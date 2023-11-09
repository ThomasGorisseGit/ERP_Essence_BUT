package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.Login;
import fr.gorisse.erp.backend.entity.valueObject.Password;
import fr.gorisse.erp.backend.entity.valueObject.Status;
import fr.gorisse.erp.backend.entity.valueObject.converter.LoginConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.PasswordConverter;
import fr.gorisse.erp.backend.entity.valueObject.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = LoginConverter.class)
    private Login login;

    @Convert(converter = PasswordConverter.class)
    private Password password;

    @Convert(converter = StatusConverter.class)
    private Status status;

    private Double salary;


    public User (String firstname, String lastname, String password){
        super(firstname,lastname);
        this.login = Login.create(firstname+"@"+lastname);
        this.password = Password.create(password);
        this.status = Status.create("Employee");
        this.salary = Status.getSalary("Employee");
    }
    public void setLogin(){
        this.login = Login.create(this.getFirstName() + "@" + this.getLastName());
    }
}
