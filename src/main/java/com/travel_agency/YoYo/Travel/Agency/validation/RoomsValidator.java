package com.travel_agency.YoYo.Travel.Agency.validation;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RoomsValidator {
    private Integer id;
    private int howManyRooms;
    private Date startDate;

    private Date endDate;
}
