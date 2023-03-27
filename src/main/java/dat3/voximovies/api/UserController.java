package dat3.voximovies.api;

import dat3.security.entity.Role;
import dat3.voximovies.dto.UserRequest;
import dat3.voximovies.dto.UserResponse;
import dat3.voximovies.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/users/")
public class UserController {

  UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  List<UserResponse> getMembers(){ return userService.getUsers(true);}

  @GetMapping("{username}")
  UserResponse getUserById(@PathVariable String username) throws Exception {return userService.findUserByUsername(username);}


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  UserResponse addUser(@RequestBody UserRequest body){
    return userService.addUser(body);
  }

  @PreAuthorize("hasAuthority('USER')")
  @DeleteMapping("{username}")
  void deleteUserByUsername(@PathVariable String username) {
    userService.deleteUser(username);
  }

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
