package dat3.voximovies.api;

import dat3.security.entity.Role;
import dat3.voximovies.dto.UserRequest;
import dat3.voximovies.dto.UserResponse;
import dat3.voximovies.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/users/")
public class UserController {

  UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  List<UserResponse> getUsers(){ return userService.getUsers(true);}

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("{username}")
  UserResponse getUserById(@PathVariable String username) throws Exception {return userService.findUserByUsername(username);}


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  UserResponse addUser(@RequestBody UserRequest body){
    return userService.addUser(body);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("{username}")
  void deleteUserByUsername(@PathVariable String username) {
    userService.deleteUser(username);
  }

  @PreAuthorize("hasAuthority('USER')")
  @PutMapping(path = "{username}")
  ResponseEntity<Boolean> editUser(@RequestBody UserRequest body, @PathVariable String username){
    return userService.updateUser(username, body);
  }

  /*
  @PatchMapping("ranking/{username}/{value}")
  void updateRankingForUser(@PathVariable String username, @PathVariable int value) {
    userService.updateRanking(username, value);
  }

   */

  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("admin/{username}/{role}")
  ResponseEntity<Boolean> addUserRole(@PathVariable String username, @PathVariable Role role) {
    return userService.addUserRole(username, role);
  }
}
