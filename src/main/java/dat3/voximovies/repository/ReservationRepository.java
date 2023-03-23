package dat3.voximovies.repository;

import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

  Reservation findReservationById(int id);
  ArrayList<Reservation> findAllByUserUsername(String username);

  ArrayList<Reservation> findAllByShowingId(int showId);

  Reservation findByUserUsernameAndShowingId(String username, int showId);

  boolean existsByUserUsernameAndShowingId(String username, int showId);

  boolean existsByUserUsernameAndId(String username, int id);
}
