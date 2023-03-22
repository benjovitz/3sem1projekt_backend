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

  //Movie movie

  //Cinema cinema

  double runtimeInMinutes;

  @OneToMany(mappedBy = "showing")
  List<Reservation> reservations = new ArrayList<>();

  double price;

  LocalDateTime date;

  public Showing(double runtimeInMinutes, List<Reservation> reservations, double price, LocalDateTime date) {
    this.runtimeInMinutes = runtimeInMinutes;
    //this.reservations = reservations;
    this.price = price;
    this.date = date;
  }
}
