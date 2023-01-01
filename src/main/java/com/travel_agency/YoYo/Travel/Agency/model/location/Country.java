package com.travel_agency.YoYo.Travel.Agency.model.location;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;
    @Column(name = "country_name", unique = true, columnDefinition = "varchar(2000)")
    private String name;
    @Column
    private long population;
    @Column
    private int area;
    @Column
    private String continent;
    @Column
    private int averageTouristInAYear;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities;
    @OneToMany(mappedBy = "country")
    private List<Destination> destination;
}
