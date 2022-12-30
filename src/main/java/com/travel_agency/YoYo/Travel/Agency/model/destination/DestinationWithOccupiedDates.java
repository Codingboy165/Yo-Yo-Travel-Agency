package com.travel_agency.YoYo.Travel.Agency.model.destination;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Ez is csak egy próbálkozás
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DestinationWithOccupiedDates {
    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "reservation_without_ocupied_dates_dest_id", nullable = false)
    private Destination reservationWithoutOcupiedDates;

    @OneToOne
    @JoinColumn(name = "occupied_dates_id")
    private OccupiedDatesForDestinations occupiedDates;
}
