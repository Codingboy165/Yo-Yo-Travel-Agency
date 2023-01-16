package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.exception.DestinationException;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.*;
import com.travel_agency.YoYo.Travel.Agency.validation.DateRangeValidator;
import com.travel_agency.YoYo.Travel.Agency.validation.RoomsValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public Reservation getReservationById(long id) {
        return reservationRepository.findAll().stream().
                filter(t -> t.getReservation_id() == id).findFirst().orElseThrow(() ->
                        new DestinationException("ERROR", "Reservation missing"));
    }
}
//    public void deleteAReservationById(int id) {
//        Reservation reservation = getReservationById(id);
//        reservationRepository.deleteById((long) id);
//    }

