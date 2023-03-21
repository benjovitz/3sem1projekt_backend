package dat3.voximovies.service;

import dat3.voximovies.dto.ReviewRequest;
import dat3.voximovies.dto.ReviewResponse;
import dat3.voximovies.entity.Review;
import dat3.voximovies.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;



    public ReviewService(ReviewRepository reviewRepository, CinemaService cinemaService){
        this.reviewRepository = reviewRepository;
    }

    public ReviewResponse createReview(ReviewRequest request) {
        Review review = ReviewRequest.getReviewEntity(request);
        reviewRepository.save(review);
        if(review.getCinema()!=null){
            review.getCinema().addReservation(review);
        } //else if review.getReviewedUser
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(ReviewResponse::new).toList();
    }
}
