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
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    private User reviewedUser;
    private Double score;
    private String comment;

    private String username;

    @ManyToOne
    private Cinema reviewedCinema;

    public Review(String username, Double score, String comment, Cinema reviewedCinema) {
        this.username = username;
        this.score = score;
        this.comment = comment;
        this.reviewedCinema = reviewedCinema;
    }

    public Review(String username, Double score, String comment, User reviewedUser) {
        this.username = username;
        this.score = score;
        this.comment = comment;
        this.reviewedUser = reviewedUser;
    }

    public Review(User user, Double score, String comment, User reviewedUser) {
        this.user = user;
        this.score = score;
        this.comment = comment;
        this.reviewedUser = reviewedUser;
    }

    public Review(User user, Double score, String comment, Cinema reviewedCinema) {
        this.user = user;
        this.score = score;
        this.comment = comment;
        this.reviewedCinema = reviewedCinema;
    }

}
