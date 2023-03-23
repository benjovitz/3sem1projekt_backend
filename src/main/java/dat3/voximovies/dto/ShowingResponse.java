package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.voximovies.entity.Showing;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowingResponse {


  String movieName;


  String cinemaName;


  double price;


  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime localDateTime;

  public ShowingResponse(Showing s) {
    this.movieName=s.getMovie().getName();
    this.cinemaName=s.getCinema().getName();
    this.price = s.getPrice();
    this.localDateTime = s.getDateTime();
  }
}
