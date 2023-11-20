package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.Siret;
import fr.gorisse.erp.backend.entity.valueObject.converter.SiretConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "productList")
@Entity
public class Provider extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "provider")
    @JsonIgnoreProperties(value = {"provider","orderList","clientOrder"})
    @JsonIgnore
    private List<Product> productList;

    @OneToMany(mappedBy = "provider")
    @JsonIgnoreProperties(value = {"provider","productList","orderList","clientOrder"})
    @JsonIgnore
    private List<Fuel> fuelList;

    @Convert(converter = SiretConverter.class)
    private Siret siret;

    public Provider(String siret,String firstname,String lastname){
        super(firstname,lastname);
        this.siret = Siret.create(siret);
    }

}
