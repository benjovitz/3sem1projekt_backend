package dat3.voximovies.repository;

import dat3.voximovies.entity.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingRepository extends JpaRepository<Showing,Integer> {

  Showing findShowingById(int id);
}
