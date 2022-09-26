package com.fernando.zssn.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fernando.zssn.persistence.entity.type.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private Integer quantity;
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "survivor_id", referencedColumnName = "id", insertable = true)
    private Survivor survivor;

    @JsonBackReference
    public Survivor getSurvivor(){
        return survivor;
    }

    public Integer calculatePoints()
    {
        return switch (this.getType()) {
            case Water -> this.quantity * 4;
            case Food -> this.quantity * 3;
            case Medication -> this.quantity * 2;
            case Ammunition -> this.quantity;
        };
    }
}
