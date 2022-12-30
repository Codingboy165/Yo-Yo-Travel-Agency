package com.travel_agency.YoYo.Travel.Agency.repository;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.ReservationWithOnlyDates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationWithOnlyTheDatesRepository extends JpaRepository<ReservationWithOnlyDates,Long> {
}
