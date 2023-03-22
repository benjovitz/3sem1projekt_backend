package dat3.voximovies.service;

import dat3.voximovies.dto.ReviewRequest;
import dat3.voximovies.dto.ReviewResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.repository.CinemaRepository;
import dat3.voximovies.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    CinemaRepository cinemaRepository;



    public ReviewService(ReviewRepository reviewRepository, CinemaRepository cinemaRepository){
        this.reviewRepository = reviewRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public ReviewResponse createReview(ReviewRequest request) {
        Review review = ReviewRequest.getReviewEntity(request);
        //her skal der noget der laver noget rating halløj
        reviewRepository.save(review);
        if(review.getCinema()!=null){
            review.getCinema().addReview(review);
            addRating(request.getCinema(),request.getRating());
        } //else if review.getReviewedUser
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(ReviewResponse::new).toList();
    }

    public void addRating(Cinema cinema, double rating){
        int ratingCounter = cinema.getNumberOfRatings();
        double currentRating = cinema.getRating();
        double newRating = currentRating*ratingCounter;
        newRating = newRating+rating;
        ratingCounter = ratingCounter+1;
        newRating=newRating/ratingCounter;
        cinema.setRating(newRating);
        cinema.setNumberOfRatings(ratingCounter);
        cinemaRepository.save(cinema);
    }
    //Overload metode med user istedet
}
