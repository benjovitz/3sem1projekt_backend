package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.entity.Showing;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowingRequest {


  Integer MovieId;


  Integer CinemaId;


  Double price;


  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime dateTime;


  public static Showing getShowingEntity(ShowingRequest sr, Movie m, Cinema c){
    return new Showing(m,c, sr.getPrice(), sr.getDateTime());
  }

  public ShowingRequest(Showing s) {
    MovieId = s.getMovie().getId();
    CinemaId = s.getCinema().getId();
    this.price = s.getPrice();
    this.dateTime = s.getDateTime();
  }
}
