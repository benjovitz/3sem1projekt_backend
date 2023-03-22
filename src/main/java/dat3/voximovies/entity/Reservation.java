package dat3.voximovies.entity;

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
  @Column(name = "res_id", nullable = false)
  private int id;


  @ManyToOne
  private User user;


  @ManyToOne
  private Showing showing;

  @Column(name = "res_seats", length = 50, nullable = false)
  @ElementCollection
  private List<String> seats = new ArrayList<>();

  public Reservation(User user, Showing showing, List<String> seats) {
    this.user = user;
    this.showing = showing;
    this.seats = seats;
  }
}

