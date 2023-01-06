package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.*;
import com.travel_agency.YoYo.Travel.Agency.validation.DateRangeValidator;
import com.travel_agency.YoYo.Travel.Agency.validation.RoomsValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReservationService {
    private final List<DateRangeValidator> dateRangeValidators = new ArrayList<>();
    private final List<Integer> uniqueDestination = new ArrayList<>();
    private final List<RoomsValidator> roomsValidators = new LinkedList<>();
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
}
