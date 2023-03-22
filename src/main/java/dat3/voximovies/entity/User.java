package dat3.voximovies.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends UserWithRoles {

  private String fullName;

  private String phone;
  private String address;
  private String city;
  private String zip;

  private String picture;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime lastEdited;

  /*
  @OneToMany(mappedBy = "user")
  List<Review> reviews = new ArrayList<>();

  public void addReview(Review review) {
    if (reviews == null) {
      reviews = new ArrayList<>();
    }
    reviews.add(review);
  }

  @OneToMany(mappedBy = "user")
  private List<Review> userReviews;

  public void addUserReview(Review userReview) {
    if (userReviews == null) {
      userReviews = new ArrayList<>();
    }
    userReviews.add(userReview);
  }

   */

  private double ranking;


  /*
  @OneToMany(mappedBy = "user")
  private List<Reservation> reservations;

  public void addReservation(Reservation reservation) {
    if (reservations == null) {
      reservations = new ArrayList<>();
    }
    reservations.add(reservation);
  }

   */

  public User(String user, String password, String email,
                String fullName, String phone, String address, String city, String zip) {
    super(user,password,email);
    this.fullName = fullName;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.zip = zip;
  }
}
