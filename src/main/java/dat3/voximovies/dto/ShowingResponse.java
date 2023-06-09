package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.voximovies.entity.Showing;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowingResponse {


  long id;

  long cinemaId;

  long movieId;

  String movieName;


  String cinemaName;


  double price;

  private List<String> resSeats = new ArrayList<>();

  String city;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime dateTime;

  public ShowingResponse(Showing s) {
    this.id=s.getId();
    this.cinemaId = s.getCinema().getId();
    this.movieId = s.getMovie().getId();
    this.movieName=s.getMovie().getName();
    this.cinemaName=s.getCinema().getName();
    this.price = s.getPrice();
    this.resSeats = s.getOccupiedSeats();
    this.city = s.getCinema().getCity();
    this.dateTime = s.getDateTime();
  }
}
