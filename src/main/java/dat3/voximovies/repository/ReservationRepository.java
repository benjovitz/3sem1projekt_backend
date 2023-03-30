package dat3.voximovies.repository;

import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

  Reservation findReservationById(long id);
  ArrayList<Reservation> findAllByUserUsername(String username);

  ArrayList<Reservation> findAllByShowingId(long showId);

  boolean existsByUserUsernameAndShowingId(String username, long showId);

  boolean existsByShowingCinemaOwnerUsernameAndId(String username, long id);


  @Transactional
  void deleteAllByShowingId(long id);
  @Transactional
  void deleteAllByUser(User user);
}
