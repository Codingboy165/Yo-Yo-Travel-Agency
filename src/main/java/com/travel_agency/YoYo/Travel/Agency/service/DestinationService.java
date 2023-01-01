package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.exception.DateIsTakenException;
import com.travel_agency.YoYo.Travel.Agency.exception.DestinationMissingException;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.destination.OccupiedDates;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.*;
import com.travel_agency.YoYo.Travel.Agency.validation.DateRangeValidator;
import com.travel_agency.YoYo.Travel.Agency.validation.RoomsValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class DestinationService {

    private static long index = 1;
    private final List<DateRangeValidator> dateRangeValidators = new ArrayList<>();
    private final List<Integer> uniqueDestination = new ArrayList<>();
    private boolean weNeedToCreateANewRoomsValidator = true;
    private final List<RoomsValidator> roomsValidators = new LinkedList<>();
    private boolean weNeedToCreateNewOccupiedDatesRange = false;
    private final DestinationRepository destinationRepository;
    private final ReservationRepository reservationRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final OccupiedDatesRepository occupiedDatesRepository;

    public DestinationService(DestinationRepository destinationRepository,
                              ReservationRepository reservationRepository,
                              CountryRepository countryRepository,
                              CityRepository cityRepository,
                              OccupiedDatesRepository occupiedDatesRepository) {

        this.destinationRepository = destinationRepository;
        this.reservationRepository = reservationRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.occupiedDatesRepository = occupiedDatesRepository;

    }

    //DESTINATION GET
    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationById(long id) {
        return destinationRepository.findAll().stream().
                filter(t -> t.getDestination_id() == id).findFirst().orElseThrow(() ->
                        new DestinationMissingException("ERROR", "Destination missing"));
    }

    //RESERVATION GET
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }


    //DESTINATION ADD
    public void addADestination(Destination destination) {
        destinationRepository.save(destination);
    }


    //RESERVATION ADD
    public void addReservationToADestination(int id, Reservation reservation) {

        Destination destination = getDestinationById(id);
        for (RoomsValidator validator : roomsValidators) {
            if ((validator.getId() == destination.getDestination_id()) &&
                    (reservation.getStartDate().compareTo(validator.getEndDate()) <= 0)&&
                    (validator.getStartDate().compareTo(reservation.getEndDate()) <= 0)&&
                    (validator.getHowManyRooms() - reservation.getHowManyRooms() < 0)) {

                throw new DateIsTakenException("ERROR", "No available rooms for this date range");

            } else
                if (reservation.getHowManyRooms() < 0) {

                throw new DateIsTakenException("ERROR", "You entered the rooms information incorrectly");

            } else
                if ((validator.getId() == destination.getDestination_id()) &&
                    (reservation.getStartDate().compareTo(validator.getEndDate()) <= 0) &&
                    (validator.getStartDate().compareTo(reservation.getEndDate()) <= 0)) {

                validator.setHowManyRooms(validator.getHowManyRooms() - reservation.getHowManyRooms());
                weNeedToCreateANewRoomsValidator = false;

                }
        }
        if (reservation.getStartDate().compareTo(reservation.getEndDate()) > 0) {

            throw new DateIsTakenException("ERROR", "The start date can't occurs after end date");

        }

        reservation.setDestination(destination);
        destination.getReservation().add(reservation);

        DateRangeValidator checker = new DateRangeValidator();
        checker.setStartDate(reservation.getStartDate());
        checker.setEndDate(reservation.getEndDate());

        if (weNeedToCreateANewRoomsValidator) {
            RoomsValidator roomsValidator = new RoomsValidator();
            roomsValidator.setId(destination.getDestination_id());
            roomsValidator.setStartDate(reservation.getStartDate());
            roomsValidator.setEndDate(reservation.getEndDate());
            if (destination.getAvailableRooms() - reservation.getHowManyRooms() < 0) {

                throw new DateIsTakenException("ERROR", "We don't have that many free rooms " +
                        "for this date range");

            } else if (reservation.getHowManyRooms() < 0) {

                throw new DateIsTakenException("ERROR", "You entered the rooms information " +
                        "incorrectly");

            } else {
                roomsValidator.setHowManyRooms(destination.getAvailableRooms() - reservation.getHowManyRooms());
                roomsValidators.add(roomsValidator);
            }
        }
        //To not add multiple occupied dates with the same value
        for (DateRangeValidator dateRangeValidator : dateRangeValidators) {
            if ((dateRangeValidator.getStartDate().equals(reservation.getStartDate())) &&
                    (dateRangeValidator.getEndDate().equals(reservation.getEndDate()))) {

                weNeedToCreateNewOccupiedDatesRange = true;
                break;

            }
        }
        if (!weNeedToCreateNewOccupiedDatesRange) {

            dateRangeValidators.add(checker);

            OccupiedDates occupiedDate = new OccupiedDates();
            occupiedDate.setStartDate(reservation.getStartDate().toString());
            occupiedDate.setEndDate(reservation.getEndDate().toString());
            occupiedDate.setDestination(destination);

            occupiedDatesRepository.save(occupiedDate);

            OccupiedDates occupiedDates = occupiedDatesRepository.findById(index).orElseThrow(() ->
                    new DateIsTakenException("FATAL_ERROR", "If you see this error message " +
                            "the business logic is trash"));

            destination.getOccupiedDates().add(occupiedDates);

            index++;
        }

        destinationRepository.save(destination);

        weNeedToCreateNewOccupiedDatesRange = false;

        weNeedToCreateANewRoomsValidator = true;
    }


    //DESTINATION DELETE
    public Destination deleteDestinationById(int id) {
        Destination destination = getDestinationById(id);
        destinationRepository.deleteById((long) id);
        return destination;
    }

    //ADD COUNTRY
    public Country countryAdd(Country country) {
        return countryRepository.save(country);
    }

    //ADD CITY
    public City cityAdd(City city) {
        return cityRepository.save(city);
    }

}
