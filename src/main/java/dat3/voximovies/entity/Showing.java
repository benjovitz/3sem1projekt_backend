package dat3.voximovies.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Showing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Nonnull
  @ManyToOne
  Movie movie;

  @Nonnull
  @ManyToOne
  Cinema cinema;


  @OneToMany(mappedBy = "showing")
  List<Reservation> reservations = new ArrayList<>();

  public List<String> getOccupiedSeats(){
    ArrayList<String> occupiedSeats = new ArrayList<>();
    reservations.forEach(r -> occupiedSeats.addAll(r.getSeats()));
    return occupiedSeats;
  }

  @Nonnull
  double price;

  @Nonnull
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime dateTime;

  public Showing(Movie movie, Cinema cinema, double price, LocalDateTime date) {
    this.movie = movie;
    this.cinema=cinema;
    this.price = price;
    this.dateTime = date;
  }
}
