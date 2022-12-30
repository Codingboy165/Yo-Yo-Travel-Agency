package com.travel_agency.YoYo.Travel.Agency.model.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class MostVisitatedPlaces {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;
    @Column
    private String name;
    @Column
    private int avarageVisitationPerYear;
    @ManyToOne
    private City city;

}
