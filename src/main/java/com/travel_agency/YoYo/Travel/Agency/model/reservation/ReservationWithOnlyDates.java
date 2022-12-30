package com.travel_agency.YoYo.Travel.Agency.model.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.YoYo.Travel.Agency.model.destination.OccupiedDatesForDestinations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.List;


//Ez egy class ahol Dátumokat tudok létrehozni egy Reservationhoz
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReservationWithOnlyDates {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;
    @Column
    private List<Date> date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private OccupiedDatesForDestinations occupiedDates;
    @OneToOne
    @JsonIgnore
    private Reservation reservation;
}
