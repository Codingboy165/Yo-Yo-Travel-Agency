package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.service.DestinationService;
import com.travel_agency.YoYo.Travel.Agency.service.ReservationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("reservations")
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;
    private final DestinationService destinationService;


    public ReservationController(ReservationService reservationService, DestinationService destinationService) {
        this.reservationService = reservationService;
        this.destinationService = destinationService;
    }

}
