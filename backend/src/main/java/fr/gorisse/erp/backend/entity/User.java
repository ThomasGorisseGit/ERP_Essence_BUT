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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = LoginConverter.class)
    private Login login;

    @Convert(converter = PasswordConverter.class)
    @Getter(AccessLevel.NONE)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.getLogin().toString();
    }
    @Override
    public String getPassword(){
        return this.password.getPassword();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
