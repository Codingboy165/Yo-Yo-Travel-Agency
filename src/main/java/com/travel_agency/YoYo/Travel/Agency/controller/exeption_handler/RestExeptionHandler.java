package com.travel_agency.YoYo.Travel.Agency.controller.exeption_handler;


import com.travel_agency.YoYo.Travel.Agency.exception.DateIsTakenException;
import com.travel_agency.YoYo.Travel.Agency.exception.DestinationMissingException;
import com.travel_agency.YoYo.Travel.Agency.exception.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExeptionHandler {

    @ExceptionHandler(DateIsTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleRuntimeException(DateIsTakenException exception){
        return new Response(exception.getStatus(),exception.getStatusMsg());
    }

    @ExceptionHandler(DestinationMissingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleDestinationMissingException(DestinationMissingException exception){
        return new Response(exception.getStatus(),exception.getStatusMsg());
    }
}
