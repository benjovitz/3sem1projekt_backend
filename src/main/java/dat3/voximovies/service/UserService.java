package dat3.voximovies.service;

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

    userRepository.save(newUser);

    return new UserResponse(newUser, true);
  }

  public void deleteUser(String username) {
    User user = userRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    userRepository.delete(user);
  }

  public ResponseEntity<Boolean> updateUser(String username, UserRequest userRequest) {
    User updatedUser = userRepository.findById(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    updatedUser.setEmail(userRequest.getEmail());
    updatedUser.setPassword(userRequest.getPassword());
    updatedUser.setCity(userRequest.getCity());
    updatedUser.setFullName(userRequest.getFullName());
    updatedUser.setPhone(userRequest.getPhone());
    updatedUser.setAddress(userRequest.getAddress());
    updatedUser.setZip(userRequest.getZip());
    userRepository.save(updatedUser);
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
  }
}
