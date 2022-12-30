package com.travel_agency.YoYo.Travel.Agency.model.location;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;
    @Column
    private String name;
    @OneToMany
    private List<MostVisitatedPlaces> mostVisitedPlaces;
    @OneToMany
    private List<Destination> destinationReservationWithoutOcupiedDates;
    @ManyToOne
    private Country country;
}