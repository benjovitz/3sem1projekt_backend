package dat3.voximovies.service;

import dat3.security.entity.Role;
import dat3.voximovies.dto.UserRequest;
import dat3.voximovies.dto.UserResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  UserRepository userRepository;
  ReservationRepository reservationRepository;
  CinemaRepository cinemaRepository;
  ShowingRepository showingRepository;

  ReviewRepository reviewRepository;


  public UserService(UserRepository userRepository, ReservationRepository reservationRepository, CinemaRepository cinemaRepository, ShowingRepository showingRepository, ReviewRepository reviewRepository) {
    this.userRepository = userRepository;
    this.reservationRepository = reservationRepository;
    this.cinemaRepository = cinemaRepository;
    this.showingRepository = showingRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<UserResponse> getUsers(boolean includeAll) {
    List<User> users = userRepository.findAll();

    List<UserResponse> userResponses = users.stream().map(u -> new UserResponse(u,includeAll)).toList();
    return userResponses;
  }

  public UserResponse findUserByUsername(String username) {
    User user = userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new UserResponse(user, true);
  }

  public UserResponse addUser(UserRequest userRequest){

    User newUser = UserRequest.getUserEntity(userRequest);

    if(userRepository.existsById(userRequest.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with this ID already exist");
    }
    if(userRepository.existsByEmail(userRequest.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with this Email already exist");
    }

    newUser.addRole(Role.USER);

    userRepository.save(newUser);

    return new UserResponse(newUser, true);
  }

  public void deleteUser(String username) {
    User user = userRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    reservationRepository.deleteAllByUser(user);
    if(cinemaRepository.existsCinemaByOwner(user)){
      for (Cinema cinema : user.getCinemas()) {
        ArrayList<Showing> showings = showingRepository.findAllByCinemaId(cinema.getId());
        long showingID = showings.get(0).getId();
        reservationRepository.deleteAllByShowingId(showingID);
        showingRepository.deleteAllByCinema(cinema);
        cinemaRepository.delete(cinema);
      }
    }
    reviewRepository.deleteAllByUser(user);
    reviewRepository.deleteAllByReviewedUser(user);
    userRepository.delete(user);
  }

  public ResponseEntity<Boolean> updateUser(String username, UserRequest userRequest) {
    User updatedUser = userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    if (!userRequest.getEmail().equals("")) {
      updatedUser.setEmail(userRequest.getEmail());
    }
    if (!userRequest.getPassword().equals("")) {
      updatedUser.setPassword(userRequest.getPassword());
    }
    if (!userRequest.getCity().equals("")) {
      updatedUser.setCity(userRequest.getCity());
    }
    if (!userRequest.getFullName().equals("")) {
      updatedUser.setFullName(userRequest.getFullName());
    }
    if (!userRequest.getPhone().equals("")) {
      updatedUser.setPhone(userRequest.getPhone());
    }
    if (!userRequest.getAddress().equals("")) {
      updatedUser.setAddress(userRequest.getAddress());
    }
    if (!userRequest.getZip().equals("")) {
      updatedUser.setZip(userRequest.getZip());
    }

    userRepository.save(updatedUser);
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
  }

  public ResponseEntity<Boolean> addUserRole(String username, Role role) {
    User user = userRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    Boolean response = user.addRole(role);
    if (!response){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, username + " already has the authority " + role);
    }
    userRepository.save(user);
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }
}
