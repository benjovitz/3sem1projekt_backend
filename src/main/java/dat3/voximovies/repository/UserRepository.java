package dat3.voximovies.repository;

import dat3.voximovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

  boolean existsByEmail(String email);
}
