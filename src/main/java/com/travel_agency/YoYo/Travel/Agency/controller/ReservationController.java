package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.DestinationRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.ReservationRepository;
import com.travel_agency.YoYo.Travel.Agency.service.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final DestinationService destinationService;

    private final ReservationRepository reservationRepository;

    private final DestinationRepository destinationRepository;

    public ReservationController(DestinationService destinationService, ReservationRepository reservationRepository, DestinationRepository destinationRepository) {
        this.destinationService = destinationService;
        this.reservationRepository = reservationRepository;
        this.destinationRepository = destinationRepository;
    }


    @GetMapping
    public List<Reservation> getAllReservation() {
        return destinationService.getAllReservation();
    }

//    @GetMapping
//    public Map<List<Reservation>,List<DestinationOverviewDTO>> getPreservation(){
    //        Map<List<Reservation>, List<DestinationOverviewDTO>> result= new HashMap<>();
//        result.put(destinationService.getAllReservation().stream().map(reservation -> ),destinationService.getAllDestination().stream().
//                map(destination -> new DestinationOverviewDTO ((long) destination.getId(),destination.getName())).toList());
//       return result;
//    }

}
