package dat3.voximovies.repository;


import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Long> {

  boolean existsByIdAndOwnerUsername(long id, String username);
  Cinema findCinemaById(long id);

  List<Cinema> findAllByOwner(User owner);

  Cinema findByName(String name);

}
