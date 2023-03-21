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
  @Column(name = "res_id", length = 50, nullable = false)
  private int id;

  @Column(name = "res_user", length = 50, nullable = false)
  @ManyToOne
  private User user;

  @Column(name = "res_show", length = 50, nullable = false)
  @ManyToOne
  private Show show;

  @Column(name = "res_seats", length = 50, nullable = false)
  @ElementCollection
  private List<String> seats = new ArrayList<>();

  public Reservation(User user, Show show, List<String> seats) {
    this.user = user;
    this.show = show;
    this.seats = seats;
  }
}

