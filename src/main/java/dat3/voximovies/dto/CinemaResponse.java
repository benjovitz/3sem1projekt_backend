package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Cinema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaResponse {
    long id;
     String description;
    //list of reviews
    //list of shows
     List<String> seats = new ArrayList<>();
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
        this.seats= c.getSeats();
        this.street=c.getStreet();
        this.city=c.getCity();
        this.zip=c.getZip();
        this.ownerName =c.getOwner().getUsername();
        this.rating=c.getRating();
        this.numberOfRatings=c.getNumberOfRatings();
    }
}
