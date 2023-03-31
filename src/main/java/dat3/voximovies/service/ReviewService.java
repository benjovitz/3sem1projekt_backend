package dat3.voximovies.service;

import dat3.voximovies.dto.ReviewRequest;
import dat3.voximovies.dto.ReviewResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.CinemaRepository;
import dat3.voximovies.repository.ReviewRepository;
import dat3.voximovies.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    CinemaRepository cinemaRepository;

    UserRepository userRepository;



    public ReviewService(ReviewRepository reviewRepository, CinemaRepository cinemaRepository, UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.cinemaRepository = cinemaRepository;
        this.userRepository = userRepository;
    }

    public ReviewResponse createCinemaReview(ReviewRequest request, Long id, String username) {
        Review review = ReviewRequest.getReviewEntity(request);
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cinema with this ID doesnt exist"));
        review.setReviewedCinema(cinema);
        User user = userRepository.findById(username).orElseThrow();
        review.setUser(user);
        reviewRepository.save(review);
        addRating(cinema,request.getRating());
        return new ReviewResponse(review);
    }

    public ReviewResponse createUserReview(ReviewRequest request, String username, String reviewerUsername) {
        Review review = ReviewRequest.getReviewEntity(request);
        User reviewedUser = userRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with this ID doesnt exist"));
        review.setReviewedUser(reviewedUser);
        User user = userRepository.findById(reviewerUsername).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with this ID doesnt exist"));
        review.setUser(user);
        reviewRepository.save(review);
        updateRanking(reviewedUser, review.getScore());
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(r -> new ReviewResponse(r)).toList();
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


  public void updateRanking(User user, double rankValue) {
    int numberOfReviews = user.getReviews().size();
    if (numberOfReviews > 1) {
      double newRanking = (user.getRanking() * (numberOfReviews - 1) + rankValue) / numberOfReviews;
      user.setRanking(newRanking);
    } else {
      user.setRanking(rankValue);
    }
    userRepository.save(user);
  }

  public List<ReviewResponse> getCinemaReviews(long id) {
    Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cinema with this ID doesnt exist"));
      List<Review> reviews = reviewRepository.findReviewsByReviewedCinema(cinema);
      List<ReviewResponse> reviewResponses = reviews.stream().map(r -> new ReviewResponse(r)).toList();
      return reviewResponses;
  }

  public List<ReviewResponse> getUserReviews(String username) {
      User user = userRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with this ID doesnt exist"));
      List<Review> reviews = reviewRepository.findReviewsByReviewedUser(user);
      List<ReviewResponse> reviewResponses = reviews.stream().map(r -> new ReviewResponse(r)).toList();
      return reviewResponses;
  }
}
