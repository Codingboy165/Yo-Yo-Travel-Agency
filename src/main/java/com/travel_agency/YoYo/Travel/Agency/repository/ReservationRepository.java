package com.travel_agency.YoYo.Travel.Agency.repository;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
