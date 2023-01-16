package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryReader {
    @Value("${file.countries}")
    private String fileCountriesPath;

    private static long countryId = 1;

    private static long cityId=1;
    public List<Country> getCountries() {
        try {
            return Files.lines(Path.of(fileCountriesPath))
                    .map(this::lineToCountry)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Country lineToCountry(String line) {
        String[] countryParts = line.split("\\|");
            List<City> cities= new ArrayList<>();
            Country country=null;
        if(countryParts.length>8) { //if the country has more than 8 parts that one has cities associated with
            List<String> nameOfTheCity=parseCity(countryParts[8]);

            country = new Country(countryId++ //increase the id and put it in the Country
                    ,countryParts[0] // name
                    ,countryParts[1] // capital
                    ,Long.parseLong(countryParts[2])  // population
                    ,Integer.parseInt(countryParts[3]) // area
                    ,countryParts[4] // continent
                    ,Integer.parseInt(countryParts[5])  //average tourist in Year
                    ,countryParts[6] //image src
                    ,countryParts[7] //description
                    ,cities); // all the popular cities in that country

            for(String name :nameOfTheCity){
                City city = new City(cityId++,name,country);
                country.getCities().add(city);
            }
            return country;

        } else{
            country = new Country(countryId++, countryParts[0], countryParts[1], Long.parseLong(countryParts[2]),
                    Integer.parseInt(countryParts[3]), countryParts[4], Integer.parseInt(countryParts[5]),countryParts[6],countryParts[7]);
            }
                return country;
        }

    private List<String> parseCity(String neighboursString) {
        return Arrays.stream(neighboursString.split("~")).toList();
    }
}

