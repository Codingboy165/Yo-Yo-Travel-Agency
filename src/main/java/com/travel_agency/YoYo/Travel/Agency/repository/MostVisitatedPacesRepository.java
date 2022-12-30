package com.travel_agency.YoYo.Travel.Agency.repository;

import com.travel_agency.YoYo.Travel.Agency.model.location.MostVisitatedPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostVisitatedPacesRepository extends JpaRepository<MostVisitatedPlaces,Long> {
}
