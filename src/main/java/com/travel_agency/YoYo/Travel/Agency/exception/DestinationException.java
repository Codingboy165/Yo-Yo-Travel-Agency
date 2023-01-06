package com.travel_agency.YoYo.Travel.Agency.exception;

import lombok.Getter;

@Getter
public class DestinationException extends RuntimeException{

    private String statusMsg;
    private String status;
    public DestinationException(String status, String statusMsg) {
        this.status=status;
        this.statusMsg=statusMsg;
    }
}
