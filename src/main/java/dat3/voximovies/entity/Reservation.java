package dat3.voximovies.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Nonnull
  private long id;


  @ManyToOne
  @Nonnull
  private User user;


  @Nonnull
  @ManyToOne
  private Showing showing;

  @Column(name = "res_seats")
  @ElementCollection
  private List<String> seats;

  public void addSeats(ArrayList<String> addedSeats) {
    if (seats == null) {
      seats = new ArrayList<>();
    }
    seats.addAll(addedSeats);
  }

  public Reservation(User user, Showing showing, List<String> seats) {
    this.user = user;
    this.showing = showing;
    this.seats = seats;
  }

  public Reservation(User user, Showing showing) {
    this.user = user;
    this.showing = showing;
  }
}

