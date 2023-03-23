package dat3.voximovies.repository;

import dat3.voximovies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  Movie findMovieById(long id);

  boolean existsByName(String name);

}
