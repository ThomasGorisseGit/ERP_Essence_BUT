package fr.gorisse.erp.backend.entity;

import fr.gorisse.erp.backend.entity.model.Person;
import fr.gorisse.erp.backend.entity.valueObject.Siret;
import fr.gorisse.erp.backend.entity.valueObject.converter.SiretConverter;
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
public class Provider extends Person {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "provider")
    List<Product> productList;
    @Convert(converter = SiretConverter.class)
    private Siret siret;
    public Provider(String siren,String nic){
        this.siret = Siret.create(siren,nic);
    }
    public Provider(String siret){
        this.siret = Siret.create(siret);
    }

}