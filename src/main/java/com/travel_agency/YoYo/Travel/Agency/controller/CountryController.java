package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.service.CountryCityService;
import com.travel_agency.YoYo.Travel.Agency.service.DestinationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("countries")
@Slf4j
public class CountryController {

    private final CountryCityService countryCityService;

    private final DestinationService destinationService;
    public CountryController(CountryCityService countryCityService, DestinationService destinationService) {
        this.countryCityService = countryCityService;
        this.destinationService = destinationService;
    }

    @GetMapping
    @CrossOrigin
    public List<Country> getAllCountry(){
        return countryCityService.getAllCountry();
    }

    @GetMapping("/country/city")
    @CrossOrigin
    public List<City> getAllCity(){
        return countryCityService.getAllCity();
    }

    @GetMapping("/country/cities/city/{id}/destinations")
    public List<Destination> getAllDestinationByCityId(@PathVariable long id) {
        return destinationService.getAllDestinationByCityId(id);
    }
    @GetMapping("{id}")
    public Country getCountryById(@PathVariable int id){
        return countryCityService.getCountryById(id);
    }
    @GetMapping("country={id}/cities")
    public List<City> getAllCityByACountry(@PathVariable int id){
        return countryCityService.getAllCityByACountry(id);
    }


    @PostMapping("/country/city={id}/destination/add")
    public ResponseEntity<Response> saveDestination(@PathVariable int id,
            @Valid @RequestBody Destination destination,
            Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Destination not saved successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        countryCityService.addADestinationToACity(id,destination);

        response.setStatus("OK");
        response.setStatusMsg("Destination saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/country/city/destination/{id}/update")
    public ResponseEntity<Response> update(@PathVariable int id, @Valid @RequestBody Destination destination, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Destination not updated successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        destinationService.updateDestination(id, destination);

        response.setStatus("OK");
        response.setStatusMsg("Destination updated successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

