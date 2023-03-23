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
public class CinemaResponse {
    long id;
    String name;
     String description;
    //list of reviews
    //list of shows
    //list of seats
     String street;
     String city;
     String zip;
     User owner; //temp
    Double rating;
    //User contactPerson;
    Integer numberOfRatings;
    public CinemaResponse(Cinema c){
        this.name=c.getName();
        this.id=c.getId();
        this.description=c.getDescription();
        this.street=c.getStreet();
        this.city=c.getCity();
        this.zip=c.getZip();
        this.owner=c.getOwner();
        this.rating=c.getRating();
        this.numberOfRatings=c.getNumberOfRatings();
    }
}
