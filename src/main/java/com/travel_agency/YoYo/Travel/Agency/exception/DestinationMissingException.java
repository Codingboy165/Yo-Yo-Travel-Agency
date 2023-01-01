package com.travel_agency.YoYo.Travel.Agency.exception;

import lombok.Getter;

@Getter
public class DestinationMissingException extends RuntimeException{

    private final String statusMsg;
    private final String status;
    public DestinationMissingException(String status, String statusMsg) {
        this.status=status;
        this.statusMsg=statusMsg;
    }
}
