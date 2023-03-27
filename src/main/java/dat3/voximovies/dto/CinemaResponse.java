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
public class CinemaResponse {
    long id;
     String description;
    //list of reviews
    //list of shows
    //list of seats
     String street;
     String city;
     String zip;
     String ownerName; //temp
    Double rating;
    //User contactPerson;
    Integer numberOfRatings;
    public CinemaResponse(Cinema c){
        this.id=c.getId();
        this.description=c.getDescription();
        this.street=c.getStreet();
        this.city=c.getCity();
        this.zip=c.getZip();
        this.ownerName =c.getOwner().getUsername();
        this.rating=c.getRating();
        this.numberOfRatings=c.getNumberOfRatings();
    }
}
