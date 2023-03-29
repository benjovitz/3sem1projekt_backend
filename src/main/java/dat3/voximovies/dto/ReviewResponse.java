package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse {

    String comment;
    Double score;
    String username;
    String reviewedUser;
    String cinemaName;

    public ReviewResponse(Review review){
        this.comment=review.getComment();
        this.score=review.getScore();
        this.username=review.getUser().getUsername();
        if (review.getReviewedCinema() != null) {
            this.cinemaName=review.getReviewedCinema().getName();
        }
        if (review.getReviewedUser() != null) {
            this.reviewedUser=review.getReviewedUser().getUsername();
        }
    }

}
