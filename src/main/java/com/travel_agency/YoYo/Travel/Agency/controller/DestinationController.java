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
public class DestinationController {

    private final DestinationService destinationService;

    private final DestinationRepository destinationRepository;


    public DestinationController(DestinationService destinationService, DestinationRepository destinationRepository) {
        this.destinationService = destinationService;
        this.destinationRepository = destinationRepository;
    }
//    @GetMapping("/home")
//    public Map<Destination,List<ReservationOverviewDTO>> getAll(){
//        Map<Destination, List<ReservationOverviewDTO>> result= new HashMap<>();
//        result.put(destinationService.getAllDestination(),destinationService.getAllDestination().stream().)
//        return destinationService.getAllDestination();
//    }

    @GetMapping("/home")
    @CrossOrigin
    public List<Destination> getAll(){
        return destinationService.getAllDestination();
    }
    @PostMapping("destination/{id}/reservation/add")
    // @ResponseBody
    public ResponseEntity<Response> saveReservation(@PathVariable int id,
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
        destinationService.addReservationToADestination(id,reservation);
        response.setStatus("OK");
        response.setStatusMsg("Reservation saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
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
        destinationService.addADestination(destination);
        response.setStatus("OK");
        response.setStatusMsg("Destination saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("{id}")
    public Destination delete(@PathVariable int id){
        return destinationService.deleteDestinationById(id);
        }

}
