package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.exception.CountryException;
import com.travel_agency.YoYo.Travel.Agency.exception.DestinationException;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.repository.CityRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }
    public List<City> getAllCity(){
        return cityRepository.findAll();
    }

    public List<City> getAllCityByACountry(int id){
        return cityRepository.findAll().stream().filter(city -> city.getCountry_id()==id).collect(Collectors.toList());
    }
    public Country getCountryById(int id){
        return countryRepository.findAll().stream().
                filter(t->t.getId()==id).findFirst().orElseThrow(()->
                        new DestinationException("ERROR", "Country missing"));
    }
    //DESTINATION ADD
    public void addADestinationToACity(int id, Destination destination) {
        City city=getCityById(id);
        destination.setCity(city);
        city.getDestination().add(destination);
        cityRepository.save(city);
    }

    private City getCityById(int id) {
        return cityRepository.findAll().stream().
                filter(t->t.getId()==id).findFirst().orElseThrow(()->
                        new DestinationException("ERROR", "Country missing"));
    }
}
