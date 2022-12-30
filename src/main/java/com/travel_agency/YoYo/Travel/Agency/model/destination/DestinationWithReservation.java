package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

//Egy próbálkozás hogy kiirassam a Destinationokat a Reservationokkal együtt de nem sikerült
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DestinationWithReservation {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int Id;

    @OneToOne
    private Destination destination;

    @OneToOne
    private Reservation reservation;

}
