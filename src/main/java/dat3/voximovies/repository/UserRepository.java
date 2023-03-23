package dat3.voximovies.repository;

import dat3.voximovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

  User findByUsername(String username);
  
  boolean existsByEmail(String email);
}
