package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.destination.DestinationWithReservation;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.ReservationWithOnlyDates;
import com.travel_agency.YoYo.Travel.Agency.repository.DestinationRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.DestinationWithReservartionRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.ReservationRepository;
import com.travel_agency.YoYo.Travel.Agency.repository.ReservationWithOnlyTheDatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private DestinationRepository destinationRepository;
    private ReservationWithOnlyTheDatesRepository reservationWithDatesRepository;
    private ReservationRepository reservationRepository;
    private DestinationWithReservartionRepository destinationWithReservartionRepository;

    public DestinationService(DestinationRepository destinationRepository, ReservationWithOnlyTheDatesRepository reservationWithDatesRepository,
                              ReservationRepository reservationRepository, DestinationWithReservartionRepository destinationWithReservartionRepository) {
        this.destinationRepository = destinationRepository;
        this.reservationWithDatesRepository = reservationWithDatesRepository;
        this.reservationRepository = reservationRepository;
        this.destinationWithReservartionRepository = destinationWithReservartionRepository;
    }



    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationById(long id){
        return destinationRepository.findAll().stream().
                filter(t->t.getDest_Id()==id).findFirst().orElseThrow(() ->
                        new RuntimeException("Destination missing"));
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }

    public List<ReservationWithOnlyDates> getAllReservationDates(){
        return reservationWithDatesRepository.findAll();
    }

    public List<DestinationWithReservation> getAllDestinationWithReservation(){
        return destinationWithReservartionRepository.findAll();
    }
    public Destination addADestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public ReservationWithOnlyDates addReservationWithOnlyDates(ReservationWithOnlyDates reservation){
        return reservationWithDatesRepository.save(reservation);
    }


    public Reservation addReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public Destination deleteDestinationById(int id) {
        Destination destination = getDestinationById(id);
        destinationRepository.deleteById((long) id);
        return destination;
    }



}
