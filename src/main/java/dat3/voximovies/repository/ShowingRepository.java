package dat3.voximovies.repository;

import dat3.voximovies.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ShowingRepository extends JpaRepository<Showing,Long> {

  ArrayList<Showing> findAllByCinemaId(long id);

  ArrayList<Showing> findAllByMovieName(String movieName);

  Showing findShowingById(long id);

  boolean existsByIdAndCinemaOwnerUsername(long id, String username);

}
