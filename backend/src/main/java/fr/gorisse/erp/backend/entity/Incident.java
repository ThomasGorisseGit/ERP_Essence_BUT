package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Incident {

    @Id
    @GeneratedValue
    private int id;

    private String description;
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @UpdateTimestamp
    private Date date;

    private String image;

    public Incident(String title, String description, Date date,String image){
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }


}
