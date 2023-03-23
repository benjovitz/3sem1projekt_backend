package dat3.voximovies.entity;


import jakarta.annotation.Nonnull;
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

  @Nonnull
  @ManyToOne
  Movie movie;

  @Nonnull
  @ManyToOne
  Cinema cinema;


  //@OneToMany(mappedBy = "showing")
  //List<Reservation> reservations = new ArrayList<>();

  @Nonnull
  double price;

  @Nonnull
  LocalDateTime dateTime;

  public Showing(Movie movie, Cinema cinema, double price, LocalDateTime date) {
    this.movie = movie;
    this.cinema=cinema;
    this.price = price;
    this.dateTime = date;
  }
}
