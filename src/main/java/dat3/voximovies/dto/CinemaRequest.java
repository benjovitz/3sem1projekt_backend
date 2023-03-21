package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Cinema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaRequest {
    private String description;
    //list of reviews
    //list of shows
    //list of seats
    private String street;
    private String city;
    private String zip;
    private String user; //temp
    private double rating;
    private int numberOfRatings;
    //private User contactPerson;

    public static Cinema getCinemaEntity(CinemaRequest c){
        Cinema cinema =  Cinema.builder().description(c.getDescription()).street(c.getStreet()).city(c.getCity()).zip(c.getZip()).user(c.getUser()).build();
        return cinema;
    }
}