package com.fernando.zssn.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "survivors")
@AllArgsConstructor
@NoArgsConstructor
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Float latitude;
    private Float longitude;
    @OneToMany(mappedBy = "survivor", cascade = CascadeType.ALL)
    private List<Item> items;
    private Integer infectedReports;
    private Boolean isInfected;

    public void addInfectedReport()
    {
        this.setInfectedReports(this.getInfectedReports()+1);
    }
}
