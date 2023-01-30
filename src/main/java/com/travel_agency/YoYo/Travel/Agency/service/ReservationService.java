package com.travel_agency.YoYo.Travel.Agency.service;


import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservationByDestinationId(long id) {
        return reservationRepository.findAll().stream().
                filter(t -> t.getDestination_id() == id).collect(Collectors.toList());
    }
}

