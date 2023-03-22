package dat3.voximovies.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    @OneToMany(mappedBy = "cinema",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Review> reviews = new HashSet<>();

    public void addReview(Review r){
        if(reviews ==null){
            reviews =new HashSet<>();
        }
        reviews.add(r);
        r.setCinema(this);
    }
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
}
