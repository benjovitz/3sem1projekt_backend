package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaRequest {

    private String name;
    private String description;
    //list of reviews
    //list of shows
    //list of seats
    private String street;
    private String city;
    private String zip;
    private User owner; //temp
    private double rating;
    private int numberOfRatings;
    //private User contactPerson;

    public static Cinema getCinemaEntity(CinemaRequest c){
        Cinema cinema =  Cinema.builder().name(c.getName()).description(c.getDescription()).street(c.getStreet()).city(c.getCity()).zip(c.getZip()).owner(c.getOwner()).build();
        return cinema;
    }
    public CinemaRequest(Cinema c){
        this.name=c.getName();
        this.description=c.getDescription();
        this.street=c.getStreet();
        this.city=c.getCity();
        this.zip=c.getZip();
        this.owner=c.getOwner();
        this.rating=c.getRating();
        this.numberOfRatings=c.getNumberOfRatings();
    }
}
