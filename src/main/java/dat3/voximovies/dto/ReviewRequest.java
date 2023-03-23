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
public class ReviewRequest {

   String username;
   Double rating;
   String comment;

   User reviewedUser;
   Cinema cinema;

    public static Review getReviewEntity(ReviewRequest request){
        if(request.getCinema()!=null){
        return new Review(request.getUsername(), request.getRating(),request.getComment(),request.getCinema());
        } else {
            return new Review(request.getUsername(), request.getRating(),request.getComment(),request.getReviewedUser());
        }
    }
}
