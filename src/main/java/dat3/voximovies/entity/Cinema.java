package dat3.voximovies.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    //list of reviews
    //list of shows
    //list of seats
    @Nonnull
    private String street;
    @Nonnull
    private String city;
    @Nonnull
    private String zip;
    @Nonnull
    private String user; //temp
    private double rating;
    private int numberOfRatings;
    //private User contactPerson;


    //For cinemaholders to create their cinema
    public Cinema(String description, String street, String city, String zip, String user) {
        this.description = description;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.user = user;
    }
}
