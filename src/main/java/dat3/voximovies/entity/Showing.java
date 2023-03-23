package dat3.voximovies.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Showing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @ManyToOne
  Movie movie;

  @ManyToOne
  Cinema cinema;

  double time;

  @OneToMany(mappedBy = "showing")
  List<Reservation> reservations = new ArrayList<>();

  double price;

  LocalDateTime dateTime;

  public Showing(Movie movie, Cinema cinema, double price, LocalDateTime date) {
    this.movie = movie;
    this.cinema=cinema;
    this.price = price;
    this.dateTime = date;
  }
}
