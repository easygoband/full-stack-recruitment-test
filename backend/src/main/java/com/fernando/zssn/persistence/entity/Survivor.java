package com.fernando.zssn.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "survivors")
@AllArgsConstructor
@NoArgsConstructor
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Float latitude;
    private Float longitude;
    private Integer points;
    @OneToMany(mappedBy = "survivor", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> items;
    private Integer infectedReports;
    private Boolean isInfected;

    @JsonManagedReference
    public List<Item> getItems(){
        return items;
    }

    public void addInfectedReport()
    {
        this.setInfectedReports(this.getInfectedReports()+1);
    }
}
