package com.travel_agency.YoYo.Travel.Agency.model.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

// Egy Json az add Reservationrol
//      {
//    "firstName": "ASD",
//    "lastName": "ASD",
//    "telephoneNumber": "1234567890",
//    "email": "solyomjonathan@gmail.com",
//        "startDate":"2002-12-21",
//        "endDate":"2002-12-01"
//
//}
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int reservation_id;
    @NotBlank(message="Name must not be blank")
    @Size(min=2, message="First name must be at least 2 characters long")
    @JsonProperty("first_name")
    private String firstName;
    @NotBlank(message="Last Name must not be blank")
    @Size(min=2, message="Name must be at least 2 characters long")
    @JsonProperty("last_name")
    private String lastName;
    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @JsonProperty("telephone_number")
    private String telephoneNumber;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;
    @Column
    @JsonProperty("start_date")
    private Date startDate;
    @Column
    @JsonProperty("end_date")
    private Date endDate;
    @JoinColumn(name = "destination_id")
    @ManyToOne(targetEntity = Destination.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Destination destination;
    @Column
    @JsonProperty("reservation_creation")
    private LocalDate reservationCreation= LocalDate.now();

    @Column(name = "destination_id",insertable = false, updatable = false)
    private int destination_id;

    @JsonProperty("how_many_rooms")
    private int howManyRooms;
}