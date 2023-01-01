package com.travel_agency.YoYo.Travel.Agency.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DateRangeValidator {

    private Date startDate;
    private Date endDate;

}