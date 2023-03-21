package dat3.voximovies.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Nonnull
    private String name;
    private String description;
    //list of reviews
    //list of shows
    //list of seats
    @ElementCollection
    @Column(name = "seat")
    List<String> seats = new ArrayList<>();
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
