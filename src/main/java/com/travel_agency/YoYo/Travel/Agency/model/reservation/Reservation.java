package com.travel_agency.YoYo.Travel.Agency.model.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.destination.DestinationWithReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

// Egy Json az add Reservationrol
//        "id": 1,
//        "firstName": "ASD",
//        "lastName": "ASD",
//        "telephoneNumber": "1234567890",
//        "email": "something@gmail.com",
//        "reservation_dates": {
//        "id": 1,
//        "date": [
//        "2002-12-21",         ITT amit ide beirok az szeretném hogy a nyillal megjelolt helyen is megjelenjen
//        "2002-12-01"
//        ]
//        },
//          "destination": {
//        "dest_Id": 2                      Itt lehetne valahogy hogy ne kelljen külön meghivnom az objektet hanem csak
//          }                               dest_id - t hivom meg?
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;
    @NotBlank(message="Name must not be blank")
    @Size(min=2, message="First name must be at least 2 characters long")
    private String firstName;
    @NotBlank(message="Last Name must not be blank")
    @Size(min=2, message="Name must be at least 2 characters long")
    private String lastName;
    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String telephoneNumber;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;
    @OneToOne(cascade = {CascadeType.ALL})
    private ReservationWithOnlyDates reservation_dates;
    @ManyToOne
    private Destination destination;

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JsonIgnore
//    private DestinationWithReservation destinationWithReservation;

}
