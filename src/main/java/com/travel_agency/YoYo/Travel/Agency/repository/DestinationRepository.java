package com.travel_agency.YoYo.Travel.Agency.repository;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {
}
