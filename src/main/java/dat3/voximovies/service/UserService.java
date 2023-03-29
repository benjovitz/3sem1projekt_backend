package dat3.voximovies.service;

import dat3.security.entity.Role;
import dat3.voximovies.dto.UserRequest;
import dat3.voximovies.dto.UserResponse;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
    userRepository.delete(user);
  }

  public ResponseEntity<Boolean> updateUser(String username, UserRequest userRequest) {
    User updatedUser = userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    Optional.ofNullable(userRequest.getEmail()).ifPresent(updatedUser::setEmail);
    Optional.ofNullable(userRequest.getPassword()).ifPresent(updatedUser::setPassword);
    Optional.ofNullable(userRequest.getCity()).ifPresent(updatedUser::setCity);
    Optional.ofNullable(userRequest.getFullName()).ifPresent(updatedUser::setFullName);
    Optional.ofNullable(userRequest.getPhone()).ifPresent(updatedUser::setPhone);
    Optional.ofNullable(userRequest.getAddress()).ifPresent(updatedUser::setAddress);
    Optional.ofNullable(userRequest.getZip()).ifPresent(updatedUser::setZip);

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
