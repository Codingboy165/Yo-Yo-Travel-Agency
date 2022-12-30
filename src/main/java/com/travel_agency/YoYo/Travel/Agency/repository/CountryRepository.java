package com.travel_agency.YoYo.Travel.Agency.repository;

import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
