package dat3.voximovies.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Nonnull
    String user;
    Double score;
    String comment;

    //@ManyToOne
    //User reviewedUser

    @ManyToOne
    Cinema cinema;

    public Review(String user, Double score, String comment, Cinema cinemaReview) {
        this.user = user;
        this.score = score;
        this.comment = comment;
        this.cinema = cinemaReview;
    }
    /*
    public Review(String user, Double score, String comment,User reviewedUser) {
        this.user = user;
        this.score = score;
        this.comment = comment;
        this.reviewedUser = reviewedUser;
    }
     */
}
