package dat3.voximovies.repository;

import dat3.voximovies.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ShowingRepository extends JpaRepository<Showing,Long> {

  ArrayList<Showing> findAllShowing();
  ArrayList<Showing> findAllByCinemaId(long id);

  ArrayList<Showing> findAllByCinemaOwnerUsername(String username);

  ArrayList<Showing> findAllByMovieId(long id);

  Showing findShowingById(long id);

  boolean existsByIdAndCinemaOwnerUsername(long id, String username);

}
