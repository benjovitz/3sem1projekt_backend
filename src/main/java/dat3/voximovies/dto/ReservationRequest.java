package dat3.voximovies.dto;

import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Show;
import dat3.voximovies.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
  private String username;

  private Integer showId;

  private List<String> seats = new ArrayList<>();

  public static Reservation getReservationEntity(ReservationRequest rr, User u, Show s){
    return new Reservation(u ,s , rr.getSeats());
  }

  public ReservationRequest(String username, Integer showId, List<String> seats) {
    this.username = username;
    this.showId = showId;
    this.seats = seats;
  }
}
