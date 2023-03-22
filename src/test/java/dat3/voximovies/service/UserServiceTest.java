package dat3.voximovies.service;

import dat3.voximovies.dto.UserRequest;
import dat3.voximovies.dto.UserResponse;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  UserRepository userRepository;

  UserService userService;


  @BeforeEach
  void setUp() {
    userService = new UserService(userRepository);
  }

  @Test
  void getUsers() {
    User u1 = new User("m1", "m1@a.dk", "test12", "bb", "565652", "xx vej 34", "Lyngby", "2800");
    User u2 = new User("m2", "m2@a.dk", "test12", "aa", "2187972", "xx vej 34", "Lyngby", "2800");
    u1.setCreated(LocalDateTime.now());
    u2.setCreated(LocalDateTime.now());
    Mockito.when(userRepository.findAll()).thenReturn(List.of(u1,u2));
    List<UserResponse> users = userService.getUsers(true);
    assertEquals(2,users.size());
    assertNotNull(users.get(0).getCreated());
  }

  @Test
  void findUserByUsername() {
    User u1 = new User("m1", "test12", "m1@a.dk", "bb", "1235626", "xx vej 34", "Lyngby", "2800");
    u1.setCreated(LocalDateTime.now());
    Mockito.when(userRepository.findById("m1")).thenReturn(java.util.Optional.of(u1));

    UserResponse response = userService.findUserByUsername("m1");
    assertEquals("m1@a.dk",response.getEmail());
  }

  @Test
  void addUser() {
    User u1 = new User("u1", "test12", "m1@a.dk", "bb", "24445678", "xx vej 34", "Lyngby", "2800");

    Mockito.when(userRepository.save(any(User.class))).thenReturn(u1);


    UserRequest request = new UserRequest(u1);
    UserResponse response = userService.addUser(request);
    assertEquals("m1@a.dk",response.getEmail());
  }

  @Test
  void updateUser() {
    User u1 = new User("u1", "test12", "m1@a.dk", "bb", "24445678", "xx vej 34", "Lyngby", "2800");

    Mockito.when(userRepository.findById("u1")).thenReturn(Optional.of(u1));
    User u2 = new User("u1", "test12", "jgdgwjy", "Hans", "677677", "vej 5", "Holte", "3400");
    UserRequest userRequest = new UserRequest(u1);
    ResponseEntity<Boolean> response = userService.updateUser("u1", userRequest);
    ResponseEntity<Boolean> testResponse = new ResponseEntity<Boolean>(true, HttpStatus.OK);
    assertEquals(testResponse, response);
  }
}