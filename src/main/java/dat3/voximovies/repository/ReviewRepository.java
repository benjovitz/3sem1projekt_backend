package dat3.voximovies.repository;

import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

  public List<Review> findReviewsByReviewedCinema(Cinema cinema);
  public List<Review> findReviewsByReviewedUser(User user);
}
