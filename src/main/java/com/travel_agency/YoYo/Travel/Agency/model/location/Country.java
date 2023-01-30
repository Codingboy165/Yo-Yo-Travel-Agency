package com.travel_agency.YoYo.Travel.Agency.model.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "country_name", unique = true, columnDefinition = "varchar(2000)")
    private String name;
    @Column
    private String capital;
    @Column
    private long population;
    @Column
    private int area;
    @Column
    private String continent;
    @Column
    private int averageTouristInAYear;
    @Column
    private String imageSrc;
    @Column
    @Lob @Basic(fetch=LAZY)
    private String description;
    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<City> cities;
    public Country(long id, String name, String capital, long population,
                   int area, String continent, int averageTouristInAYear,
                   String imageSrc, String description) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.averageTouristInAYear = averageTouristInAYear;
        this.imageSrc = imageSrc;
        this.description = description;
    }

}
