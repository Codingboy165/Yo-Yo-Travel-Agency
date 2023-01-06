package com.travel_agency.YoYo.Travel.Agency.controller.exeption_handler;


import com.travel_agency.YoYo.Travel.Agency.exception.CountryException;
import com.travel_agency.YoYo.Travel.Agency.exception.DateException;
import com.travel_agency.YoYo.Travel.Agency.exception.DestinationException;
import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExeptionHandler {

    @ExceptionHandler(DateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleRuntimeException(DateException exception){
        return new Response(exception.getStatus(),exception.getStatusMsg());
    }

    @ExceptionHandler(DestinationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleDestinationMissingException(DestinationException exception){
        return new Response(exception.getStatus(),exception.getStatusMsg());
    }

    @ExceptionHandler(CountryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleCountryException(CountryException exception){
        return new Response(exception.getStatus(),exception.getStatusMsg());
    }

}
