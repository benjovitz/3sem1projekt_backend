package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.security.entity.Role;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Review;
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

  List<ReservationResponse> reservations;

  List<ReviewResponse> userReviews;

  List<ReviewResponse> reviews;

  List<String> cinemas;

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
    if (u.getReviews() != null) {
      this.reviews = u.getReviews().stream().map(r->new ReviewResponse(r)).toList();
    }
    if (u.getReservations() != null) {
      this.reservations = getReservations();
    }
    if (u.getUserReviews() != null) {
      this.userReviews = getUserReviews();
    }
    if(u.getCinemas()!=null){
      List<String> cinemaNames = u.getCinemas().stream().map(c->c.getName()).toList();
      this.cinemas=cinemaNames;
    }
    if(includeAll){
      this.created = u.getCreated();
      this.edited = u.getLastEdited();
    }
  }
}

