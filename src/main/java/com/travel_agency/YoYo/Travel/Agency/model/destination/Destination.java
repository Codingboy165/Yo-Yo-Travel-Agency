package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotBlank(message="Name must not be blank")
    @Size(min=2, message="Name must be at least 2 characters long")
    private String name;
    @JoinColumn(name = "city_id")
    @ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private City city;
    @Column(name = "city_id",insertable=false, updatable=false)
    private long city_id;
    @NotNull(message="Price must not be blank")
    private double price;
    @Column
    @JsonProperty("type_of_accommodation")
    private TypeOfAccommodation typeOfAccommodation;
    @NotBlank(message="Description must not be blank")
    @Size(min=80, message="Description must be at least 80 characters long")
    @Column
    @Lob @Basic(fetch=LAZY)
    private String description;
    @OneToMany(mappedBy = "destination", cascade = {CascadeType.ALL})
    private List<Reservation> reservation;
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @JsonProperty("occupied_dates")
    private List<OccupiedDates> occupiedDates;
    @Column
    @JsonProperty("available_rooms")
    private int availableRooms;


}
