package com.travel_agency.YoYo.Travel.Agency.controller;

import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.service.CountryCityService;
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

    public CountryController(CountryCityService countryCityService) {
        this.countryCityService = countryCityService;
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

    @GetMapping("{id}")
    public Country getCountryById(@PathVariable int id){
        return countryCityService.getCountryById(id);
    }
    @GetMapping("country={id}/cities")
    public List<City> getAllCityByAContinent(@PathVariable int id){
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

    @PostMapping("/country/city/add/{id}")
    @CrossOrigin
    public Country addCityToACountry(@PathVariable int id, @RequestBody City city){
        return countryCityService.addCityToCountry(id,city);
    }

    @PostMapping("/country/add")
    public ResponseEntity<Response> addACountry( @Valid @RequestBody Country country,
                                                 Errors errors){
        Response response = new Response();
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            response.setStatus("BAD");
            response.setStatusMsg("Country not saved successfully");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        countryCityService.countryAdd(country);

        response.setStatus("OK");
        response.setStatusMsg("Country saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    }

