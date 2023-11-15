package fr.gorisse.erp.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    private String image;

    public Incident(String title, String description, Date date,String image){
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }


}
