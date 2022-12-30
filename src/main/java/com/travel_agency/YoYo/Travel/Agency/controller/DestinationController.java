package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.destination.DestinationWithReservation;
import com.travel_agency.YoYo.Travel.Agency.model.Response;
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
public class DestinationController {

    private final DestinationService destinationService;

    private final DestinationRepository destinationRepository;

    public DestinationController(DestinationService destinationService, DestinationRepository destinationRepository) {
        this.destinationService = destinationService;
        this.destinationRepository = destinationRepository;
    }
    @GetMapping("/home")
    public List<Destination> getAll(){
        return destinationService.getAllDestination();
    }

    @GetMapping("/home/reservations")
    public List<DestinationWithReservation> getAllWithReservation(){
        return destinationService.getAllDestinationWithReservation();
    }
    @PostMapping("/destination")
    public ResponseEntity<Response> saveDestination(
            @Valid @RequestBody Destination destination,
            Errors errors){
        Response response = new Response();
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Destination not saved successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        destinationRepository.save(destination);
        response.setStatus("OK");
        response.setStatusMsg("Destination saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("{id}")
    public Destination delete(@PathVariable int id){
        return destinationService.deleteDestinationById((int) id);
        }

}
