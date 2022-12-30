package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.ReservationWithOnlyDates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

//Itt szeretném hogy frissüljön hogy amikor csinál valaki egy foglalást
//és kitölti az adott dátummal akkor megjelenjen a Destinationban hogy más valaki ugyan
//arra a dátumra ne tudjon csinálni foglalást.
//Itt egy Get All Reservation JSON
//{
//        "id": 1,
//        "firstName": "ASD",
//        "lastName": "ASD",
//        "telephoneNumber": "1234567890",
//        "email": "solyomjonathan@gmail.com",
//        "reservation_dates": {
//        "id": 1,
//        "date": [
//        "2002-12-21",         ITT amit ide beirok az szeretném hogy a nyillal megjelolt helyen is megjelenjen
//        "2002-12-01"
//        ]
//        },
//        "destination": {
//        "dest_Id": 2,
//        "name": "Somewhere",
//        "country": null,
//        "city": null,
//        "price": 5.9,
//        "typeOfAccommodation": "HOTEL",
//        "occupiedDates": [],                  <-- Itt ezen a helyen szeretném hogy megjelenjen de üres halmazt kapok
//        "description": "Is a large house",
//        "reservation": []                         ITT pedig ezt nem szeretném látni amikor csak a Reservationokra nyomok egy Getet
//
//        }
//        },
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OccupiedDatesForDestinations {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int Id;
    @OneToMany
    private List<ReservationWithOnlyDates> occupiedDates;
    @ManyToOne
    private Destination destination;
    @OneToOne
    @JsonIgnore
    private DestinationWithOccupiedDates DestinationWithOccupiedDates;
}
