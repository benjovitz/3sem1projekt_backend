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
public class ReviewRequest {

   String user;
   //User user;
   Double score;
   String comment;
   //User reviewedUser;
    Cinema cinema;

    public static Review getReviewEntity(ReviewRequest request){
        if(request.getCinema()!=null){
        return new Review(request.getUser(),request.getScore(),request.getComment(),request.getCinema());
        } else /*if(request.getReviewedUser!=null)*/{
            //return new Review(request.getUser(),request.getScore(),request.getComment(),request.getReviewedUser())
        }
        return null;
    }
}
