package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
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
    String user;
    //User user;
    //User reviewedUser;
    String cinemaName;

    public ReviewResponse(Review review){
        this.cinemaName=review.getCinema().getName();
        this.comment=review.getComment();
        this.score=review.getScore();
        this.user=review.getUser();
    }

}
