package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.ReservationWithOnlyDates;
import com.travel_agency.YoYo.Travel.Agency.model.Response;
import com.travel_agency.YoYo.Travel.Agency.repository.DestinationRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.ReservationRepository;
import com.travel_agency.YoYo.Travel.Agency.service.DestinationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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


    @PostMapping("/add")
    // @ResponseBody
    public ResponseEntity<Response> saveReservation(
            @Valid @RequestBody Reservation reservation,
            Errors errors){
        Response response = new Response();
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Reservation not saved successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        reservationRepository.save(reservation);

        response.setStatus("OK");
        response.setStatusMsg("Reservation saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<Reservation> getPreservation(){
        return destinationService.getAllReservation();
    }

    @GetMapping("/dates")
    public List<ReservationWithOnlyDates> getPreservationDates(){
        return destinationService.getAllReservationDates();
    }

//    @PostMapping("/add")
//    public Reservation addReservation(@RequestBody Reservation reservation){
//        return destinationService.addReservation(reservation);
//    }
}
