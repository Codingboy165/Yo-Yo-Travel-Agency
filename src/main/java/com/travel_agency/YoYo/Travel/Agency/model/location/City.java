package com.travel_agency.YoYo.Travel.Agency.model.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.YoYo.Travel.Agency.model.destination.Destination;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    private long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<Destination> destination;
    @JoinColumn(name = "country_id")
    @ManyToOne(targetEntity = Country.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Country country;
    @Column(name = "country_id",insertable = false, updatable = false)
    private long country_id;

    public City(long id, String name,Country country) {
        this.id=id;
        this.name = name;
        this.country = country;
    }

}
