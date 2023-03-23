package dat3.voximovies.repository;

import dat3.voximovies.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ShowingRepository extends JpaRepository<Showing,Integer> {

  ArrayList<Showing> findAllByCinemaId(int id);

  ArrayList<Showing> findAllByMovieName(String movieName);

  Showing findShowingById(int id);

  boolean existsByIdAndCinemaUserUsername(int id, String username);

}
