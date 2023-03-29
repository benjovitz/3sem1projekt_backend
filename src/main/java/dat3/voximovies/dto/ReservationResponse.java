package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
  private Long id;
  private String username;

  private Long showingId;

  private Double priceSum;

  private String movieName;

  private String cinemaName;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateTime;

  private List<String> seats = new ArrayList<>();

public ReservationResponse(Reservation r){
  this.id=r.getId();
  this.username=r.getUser().getUsername();
  this.showingId=r.getShowing().getId();
  this.seats=r.getSeats();
  this.priceSum=r.getPriceSum();
  this.movieName=r.getShowing().getMovie().getName();
  this.cinemaName=r.getShowing().getCinema().getName();
  this.dateTime=r.getShowing().getDateTime();

}
}
