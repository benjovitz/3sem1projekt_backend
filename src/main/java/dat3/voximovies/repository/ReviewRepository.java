package dat3.voximovies.repository;

import dat3.voximovies.entity.Review;
import dat3.voximovies.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Transactional
    void deleteAllByUser(User user);

    @Transactional
    void deleteAllByReviewedUser(User user);
}
