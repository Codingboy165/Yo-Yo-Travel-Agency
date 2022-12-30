package com.travel_agency.YoYo.Travel.Agency.model.destination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.YoYo.Travel.Agency.model.location.City;
import com.travel_agency.YoYo.Travel.Agency.model.location.Country;
import com.travel_agency.YoYo.Travel.Agency.model.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

// Ez a Destination class az egyik fő class
// Ezt szeretnem a főoldalon látni egy api meghivásával
// hogy amikor meghivom, az összes házat és hotelt lehessen látni
// Ez összeköttetésben van a Cityvel, a Countryval, amelyekre ha rámegyünk akkor
// kiirat pár infot az adott Countryrol vagy Cityröl
// ITT egy JSON a get ALL destination
// {
//        "dest_Id": 1,
//        "name": "Somewhere",
//        "country": null,
//        "city": null,
//        "price": 5.9,
//        "typeOfAccommodation": "HOTEL",
//        "occupiedDates": [],                  <--Itt nem jelenik meg az hogy mely datumok foglaltak
//        "description": "Is a large house",
//        "reservation": []                     <--Itt nem jelennek meg  foglalások
//    },
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int dest_Id;
    //Megadjuk a ház,hotel,motel akármi is annak a nevét. Ahol szeretnénk egy foglalást lérehozni
    @Column
    private String name;

    //Itt az összeköttetés az Országgal amelyre ha rámegyünk különböző infokat kapunk meg
    //többek között azt is hogy az adott Országban milyen hotelekre lehet foglalást létrehozni stb
    @ManyToOne
    private Country country;

    //Itt az összeköttetés a Várossal amelyre ha rámegyünk különböző infokat kapunk meg
    //többek között azt is hogy az adott városban milyen hotelekre lehet foglalást létrehozni
    //és ezen kivül hogy melyek a leglátogatottabb helyek a városban
    @ManyToOne
    private City city;

    //Az összeget tároljuk itt el, hogy mennyibe kerül az adott helyiség egy napra
    @Column
    private double price;

    //A tipusát tároljuk el az adott lakosztálynak hogy egy Hotelröl beszélünk egy Villárol stb.
    //Ez egy Enum
    @Column
    private TypeOfAccommodation typeOfAccommodation;

    //Az OccupiedDatesForDestination azért hosztam létre hogy
    //amikor létrehozunk egy foglalást a reservation classban akkor itt ez a rész megteljen
    //azzal a dátummal amelyet a reservationban kiválasztottunk/beirtunk. Hogy aki csinál egy másik foglalást
    // lássa hogy az adott dátum el van foglalva . Egyenlöre sajnos nem müködik
    //nem tudom hogy lehetne összekötni hogy updateolodjon az érték... :(
    @OneToMany
    private List<OccupiedDatesForDestinations> occupiedDates;

    //Ez csak egy leirás az adott helynek hogy mit foglal magában
    @Column
    private String description;

    //Itt a reservation oszlopunk amely egy lista a foglalásokrol. Ugye egy hotelthez több foglalás is tartozik
    // de egy foglalás csak egy hotelhez tartozik nem többhöz. Amit elszeretnék érni hogy amikor nyomok egy get
    //all destination akkor az összes foglalás megjelenjen vele együtt. Pld. A Cristal Hotelhez 3 foglalás van és
    //megjelenik hogy ki foglalta le stb. Tehát az reservation amit csinalt a személy itt utolso sorként megjelenjen
    //csak sajnos nem jelenik meg. Egy üres arrayt kapok.
    //Ami még gondot okoz hogy ha ezt sikerül megcsinálni, mármint megjelenik az összes reservation egy destinationnál,
    //Amikor csinálok egy get All Reservation akkor a reservationoknál megjelenik az összes foglalás is az adott destinationnál
    //Azt nem lehetne kivenni marmint amikor csinalok egy Get all Reservation csak az adott hotelt lássam de azt ne hogy milyen
    //foglalásai vannak, és amikor Get All Destinationt csinálok akkor minden látszodjon mármint az összes foglalás
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Reservation> reservation;

    //Az alábbi dolgok csak próbálkozások hogy kiirasak par dolgot. Az elsö egy class ami rendelkezik az elfoglalt datumokkal
    //Olyan pontosan mint a List<OccupiedDatesForDestinations> csak mivel az nem müködik igy probálkoztam.
    //Az utloso pedig hogy kiirasam szépen a Destinationokat a reservationokkal együtt mert ugye ezzel a List<Reservation> al nem
    // sikerült

//    @OneToOne
//    @JsonIgnore
//    private DestinationWithOccupiedDates DestinationWithOccupiedDates;
//    @OneToOne(cascade = CascadeType.MERGE)
//    @JsonIgnore
//    private DestinationWithReservation destinationWithReservation;

}
