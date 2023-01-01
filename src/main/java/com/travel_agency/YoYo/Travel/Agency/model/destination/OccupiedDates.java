package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OccupiedDates {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column
    @JsonProperty("start_date")
    private String startDate;
    @Column
    @JsonProperty("end_date")
    private String endDate;
    @ManyToOne
    @JsonIgnore
    private Destination destination;

}
