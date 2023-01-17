package com.travel_agency.YoYo.Travel.Agency.service;

import com.travel_agency.YoYo.Travel.Agency.exception.DateException;
import com.travel_agency.YoYo.Travel.Agency.exception.DestinationException;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import com.travel_agency.YoYo.Travel.Agency.model.destination.OccupiedDates;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import com.travel_agency.YoYo.Travel.Agency.repository.*;
import com.travel_agency.YoYo.Travel.Agency.validation.DateRangeValidator;
import com.travel_agency.YoYo.Travel.Agency.validation.RoomsValidator;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DestinationService {

    private static long index = 1;
    private final List<DateRangeValidator> dateRangeValidators = new LinkedList<>();
    private boolean weNeedToCreateANewRoomsValidator = true;
    private final List<RoomsValidator> roomsValidators = new LinkedList<>();
    private boolean weNeedToCreateNewOccupiedDatesRange = false;
    private final DestinationRepository destinationRepository;
    private final OccupiedDatesRepository occupiedDatesRepository;

    public DestinationService(DestinationRepository destinationRepository,
                              OccupiedDatesRepository occupiedDatesRepository) {

        this.destinationRepository = destinationRepository;
        this.occupiedDatesRepository = occupiedDatesRepository;

    }

    //DESTINATION GET
    public List<Destination> getAllDestinationByCityId(long id) {
        return destinationRepository.findAll().stream().
                filter(t -> t.getCity_id() == id).collect(Collectors.toList());
    }

    public Destination getDestinationById(long id) {
        return destinationRepository.findAll().stream().
                filter(t -> t.getDestination_id() == id).findFirst().orElseThrow(() ->
                        new DestinationException("ERROR", "Destination missing"));
    }


    //DESTINATION DELETE
    public void deleteDestinationById(int id) {
        Destination destination = getDestinationById(id);
        destinationRepository.deleteById((long) id);
    }

    //UPDATE A DESTINATION
    public void updateDestination(int id, Destination destination){
            Destination destinationToBeUpdated = getDestinationById(id);
            destinationToBeUpdated.setName(destination.getName());
            destinationToBeUpdated.setPrice(destination.getPrice());
            destinationToBeUpdated.setTypeOfAccommodation(destination.getTypeOfAccommodation());
            destinationToBeUpdated.setAvailableRooms(destination.getAvailableRooms());
            destinationToBeUpdated.setDescription(destination.getDescription());
            destinationToBeUpdated.setAddress(destination.getAddress());
        destinationRepository.save(destinationToBeUpdated);
    }


    //DESTINATION UPDATE WITH A RESERVATION
    public void addReservationToADestination(int id, Reservation reservation) {

        Destination destination = getDestinationById(id);
        for (RoomsValidator validator : roomsValidators) {
            if ((validator.getId() == destination.getDestination_id()) &&
                    (reservation.getStartDate().compareTo(validator.getEndDate()) <= 0)&&
                    (validator.getStartDate().compareTo(reservation.getEndDate()) <= 0)&&
                    (validator.getHowManyRooms() - reservation.getHowManyRooms() < 0)) {

                throw new DateException("ERROR", "No available rooms for this date range");

            } else
                if (reservation.getHowManyRooms() < 0) {

                throw new DateException("ERROR", "You entered the rooms information incorrectly");

            } else
                if ((validator.getId() == destination.getDestination_id()) &&
                    (reservation.getStartDate().compareTo(validator.getEndDate()) <= 0) &&
                    (validator.getStartDate().compareTo(reservation.getEndDate()) <= 0)) {

                validator.setHowManyRooms(validator.getHowManyRooms() - reservation.getHowManyRooms());
                weNeedToCreateANewRoomsValidator = false;

                }
        }
        if (reservation.getStartDate().compareTo(reservation.getEndDate()) > 0) {

            throw new DateException("ERROR", "The start date can't occurs after end date");

        }

        reservation.setDestination(destination);
        destination.getReservation().add(reservation);

        if (weNeedToCreateANewRoomsValidator) {
            RoomsValidator roomsValidator = new RoomsValidator();
            roomsValidator.setId(destination.getDestination_id());
            roomsValidator.setStartDate(reservation.getStartDate());
            roomsValidator.setEndDate(reservation.getEndDate());
            if (destination.getAvailableRooms() - reservation.getHowManyRooms() < 0) {

                throw new DateException("ERROR", "We don't have that many free rooms " +
                        "for this date range");

            } else if (reservation.getHowManyRooms() < 0) {

                throw new DateException("ERROR", "You entered the rooms information " +
                        "incorrectly");

            } else {
                roomsValidator.setHowManyRooms(destination.getAvailableRooms() - reservation.getHowManyRooms());
                roomsValidators.add(roomsValidator);
            }
        }
        //To not add multiple occupied dates with the same value
        for (DateRangeValidator dateRangeValidator : dateRangeValidators) {
            if ((dateRangeValidator.getStartDate().equals(reservation.getStartDate())) &&
                    (dateRangeValidator.getEndDate().equals(reservation.getEndDate()))&&
                    (dateRangeValidator.getD_id()==destination.getDestination_id())
            ) {

                weNeedToCreateNewOccupiedDatesRange = true;
                break;

            }
        }
        if (!weNeedToCreateNewOccupiedDatesRange) {

            DateRangeValidator checker = new DateRangeValidator();
            checker.setStartDate(reservation.getStartDate());
            checker.setEndDate(reservation.getEndDate());
            checker.setD_id(destination.getDestination_id());

            dateRangeValidators.add(checker);

            OccupiedDates occupiedDate = new OccupiedDates();
            occupiedDate.setStartDate(reservation.getStartDate().toString());
            occupiedDate.setEndDate(reservation.getEndDate().toString());
            occupiedDate.setDestination(destination);

            occupiedDatesRepository.save(occupiedDate);

            OccupiedDates occupiedDates = occupiedDatesRepository.findById(index).orElseThrow(() ->
                    new DateException("FATAL_ERROR", "If you see this error message " +
                            "the business logic is trash"));

            destination.getOccupiedDates().add(occupiedDates);

            index++;
        }

        destinationRepository.save(destination);

        weNeedToCreateNewOccupiedDatesRange = false;

        weNeedToCreateANewRoomsValidator = true;
    }
}
