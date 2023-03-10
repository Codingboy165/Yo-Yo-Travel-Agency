package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.exception.DestinationException;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.repository.CityRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryCityService {
    private final CountryRepository countryRepository;

    private final CityRepository cityRepository;
    public CountryCityService(CountryReader countryReader, CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        if(countryRepository.findAll().isEmpty()) {
            countryRepository.saveAll(countryReader.getCountries());
            System.out.println("Finished reading countries");
        }
        this.cityRepository = cityRepository;
    }
    //COUNTRIES GET
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }
    //CITIES GET
    public List<City> getAllCity(){
        return cityRepository.findAll();
    }

    //CITIES GET BY A COUNTRY ID
    public List<City> getAllCityByACountry(int id){
        return cityRepository.findAll().stream().filter(city -> city.getCountry_id()==id).collect(Collectors.toList());
    }

    //COUNTRY GET BY ID
    public Country getCountryById(int id){
        return countryRepository.findAll().stream().
                filter(t->t.getId()==id).findFirst().orElseThrow(()->
                        new DestinationException("ERROR", "Country missing"));
    }
    //DESTINATION ADD TO CITY
    public void addADestinationToACity(int id, Destination destination) {
        City city=getCityById(id);
        destination.setCity(city);
        city.getDestination().add(destination);
        cityRepository.save(city);
    }

    //CITY GET BY ID
    private City getCityById(int id) {
        return cityRepository.findAll().stream().
                filter(t->t.getId()==id).findFirst().orElseThrow(()->
                        new DestinationException("ERROR", "Country missing"));
    }
}
