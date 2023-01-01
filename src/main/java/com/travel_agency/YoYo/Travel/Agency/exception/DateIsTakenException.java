package com.travel_agency.YoYo.Travel.Agency.exception;

import lombok.Getter;

@Getter
public class DateIsTakenException extends RuntimeException{
    private String statusMsg;
    private String status;
    public DateIsTakenException(String status, String statusMsg) {
        this.status=status;
        this.statusMsg=statusMsg;
    }
}
