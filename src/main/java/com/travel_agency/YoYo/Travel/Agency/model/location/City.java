package com.travel_agency.YoYo.Travel.Agency.model.location;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "city")
    private List<MostVisitatedPlaces> mostVisitedPlaces;
    @OneToMany(mappedBy = "city")
    private List<Destination> destination;
    @ManyToOne
    private Country country;
}