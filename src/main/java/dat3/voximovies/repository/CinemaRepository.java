package dat3.voximovies.repository;


import dat3.voximovies.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Long> {

  boolean existsByIdAndOwnerUsername(long id, String username);
  Cinema findCinemaById(long id);

}
