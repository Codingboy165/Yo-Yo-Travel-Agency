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

    private LinkedList<String> uniqueNames = new LinkedList<>();

    private final CityRepository cityRepository;
    public CountryCityService(CountryReader countryReader,CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        countryRepository.saveAll(countryReader.getCountries());
        System.out.println("Finished reading countries");
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
    //ADD COUNTRY
    public void countryAdd(Country country) {
        if(uniqueNames.contains(country.getName())){
            throw new CountryException("ERROR","The Country name is alredy taken");
        }
        countryRepository.save(country);
    }
    //ADD CITY
    public Country addCityToCountry(int id, City city) {
        Country country=getCountryById(id);
        city.setCountry(country);
        country.getCities().add(city);
        return countryRepository.save(country);
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
