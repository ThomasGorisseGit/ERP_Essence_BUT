package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.mapping.Constraint;

import java.sql.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fuel {
    @Id
    @GeneratedValue
    private int id;

    private String typeOfFuel;

    private double quantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @UpdateTimestamp
    private Date lastUpdateDate;

    @ManyToOne(targetEntity = Provider.class)
    @JsonIgnoreProperties("fuelList")
    private Provider provider;

}
