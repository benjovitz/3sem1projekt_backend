package dat3.voximovies.api;

import dat3.voximovies.dto.CinemaRequest;
import dat3.voximovies.dto.ReviewRequest;
import dat3.voximovies.dto.ReviewResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.service.CinemaService;
import dat3.voximovies.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/review/")
public class ReviewController {
    ReviewService reviewService;
    CinemaService cinemaService;

    public ReviewController(ReviewService reviewService, CinemaService cinemaService){
        this.reviewService = reviewService;
        this.cinemaService = cinemaService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<ReviewResponse> getAllReviews(){
      return reviewService.getAllReviews();
    }
    @PreAuthorize("hasAuthority('CINEMATICER')")
    @PostMapping("user/{username}")
    ReviewResponse createReviewForUser(@RequestBody ReviewRequest request,@PathVariable String username){
        return reviewService.createUserReview(request, username);
    }

    @PostMapping("cinema/{cinemaID}")
    ReviewResponse createReviewForCinema(@RequestBody ReviewRequest request,@PathVariable String cinemaID){
        return reviewService.createCinemaReview(request, Long.valueOf(cinemaID));
    }
}
