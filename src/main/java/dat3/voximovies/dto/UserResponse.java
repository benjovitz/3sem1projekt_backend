package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.security.entity.Role;
import dat3.voximovies.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
  String username;
  String email;
  String fullName;

  String phone;
  String address;
  private User u;
  String city;
  String zip;
  Double ranking;

  List<Role> roles;

  String picture;

  //List<Reservation> reservations;

  //List<Review> userReviews;

  //List<Review> reviews;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime created;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime edited;

  public UserResponse(User u, boolean includeAll) {
    this.username = u.getUsername();
    this.email = u.getEmail();
    this.address = u.getAddress();
    this.fullName = u.getFullName();
    this.phone = u.getPhone();
    this.city = u.getCity();
    this.zip = u.getZip();
    this.ranking = u.getRanking();
    this.picture = u.getPicture();
    this.roles = u.getRoles();
    /*if (reviews != null) {
      this.reviews = u.getReviews();
    }
    if (u.getReservations() != null) {
      this.reservations = getReservations();
    }
    if (userReviews != null) {
      this.userReviews = getUserReviews();
    }*/
    if(includeAll){
      this.created = u.getCreated();
      this.edited = u.getLastEdited();
    }
  }
}

