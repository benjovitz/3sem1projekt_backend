package dat3.voximovies.dto;

import dat3.security.entity.Role;
import dat3.voximovies.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
  String username;
  String email;
  String password;
  String fullName;

  String phone;
  String address;
  String city;
  String zip;


  public static User getUserEntity(UserRequest u){
    return new User(u.username,u.getPassword(),u.getEmail(), u.fullName,u.getPhone(),u.getAddress(), u.getCity(), u.getZip());
  }

  public UserRequest(User u){
    this.username = u.getUsername();
    this.password = u.getPassword();
    this.email = u.getEmail();
    this.fullName = u.getFullName();
    this.phone = u.getPhone();
    this.address = u.getAddress();
    this.city = u.getCity();
    this.zip = u.getZip();
    }
  }


