package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int destination_id;
    //Megadjuk a ház,hotel,motel akármi is annak a nevét. Ahol szeretnénk egy foglalást lérehozni
    @NotBlank(message="Name must not be blank")
    @Size(min=2, message="Name must be at least 2 characters long")
    private String name;

    //Itt az összeköttetés az Országgal amelyre ha rámegyünk különböző infokat kapunk meg
    //többek között azt is hogy az adott Országban milyen hotelekre lehet foglalást létrehozni stb
    @ManyToOne
    private Country country;

    //Itt az összeköttetés a Várossal amelyre ha rámegyünk különböző infokat kapunk meg
    //többek között azt is hogy az adott városban milyen hotelekre lehet foglalást létrehozni
    //és ezen kivül hogy melyek a leglátogatottabb helyek a városban
    @ManyToOne
    private City city;

    //Az összeget tároljuk itt el, hogy mennyibe kerül az adott helyiség egy napra
    @NotNull(message="Price must not be blank")
    private double price;

    //A tipusát tároljuk el az adott lakosztálynak hogy egy Hotelröl beszélünk egy Villárol stb.
    //Ez egy Enum
    @Column
    @JsonProperty("type_of_accommodation")
    private TypeOfAccommodation typeOfAccommodation;

    //Ez csak egy leirás az adott helynek hogy mit foglal magában
    @NotBlank(message="Description must not be blank")
    @Size(min=80, message="Description must be at least 80 characters long")
    @Column
    @Lob @Basic(fetch=LAZY)
    private String description;

    //Itt a reservation oszlopunk amely egy lista a foglalásokrol. Ugye egy hotelthez több foglalás is tartozik
    // de egy foglalás csak egy hotelhez tartozik nem többhöz.
    @OneToMany(mappedBy = "destination", cascade = {CascadeType.ALL})
    private List<Reservation> reservation;

    //Egy listát szeretnék látni az elfoglalt dátumokrol. Nem tudom hogy lehetne ezt a listát feltölteni azokkal az
    //adatokkal amelyek a reservationban vannak. A start date és end date kellene nekem csak hogy azokkal legyen fel
    //toltve a lista
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonProperty("occupied_dates")
    private List<OccupiedDates> occupiedDates;

    @Column
    private int availableRooms;


}
