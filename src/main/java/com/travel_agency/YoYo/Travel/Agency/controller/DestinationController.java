package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.DestinationRepository;
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
@RequestMapping("destinations")
@CrossOrigin
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<Destination> getAll() {
        return destinationService.getAllDestination();
    }

    @PostMapping("destination/reservation/add/{id}")
    public ResponseEntity<Response> addReservationToADestination(@PathVariable int id,
                                                                 @Valid @RequestBody Reservation reservation,
                                                                 Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Reservation not saved successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        destinationService.addReservationToADestination(id, reservation);

        response.setStatus("OK");
        response.setStatusMsg("Reservation saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("destination/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable int id) {

        Response response=new Response();
        response.setStatus("OK");
        response.setStatusMsg("Destination saved successfully");

        destinationService.deleteDestinationById(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
