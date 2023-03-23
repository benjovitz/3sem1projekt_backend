package dat3.voximovies.dto;

import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Showing;
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

  private Integer showingId;

  private List<String> seats = new ArrayList<>();

  public static Reservation getReservationEntity(ReservationRequest rr, User u, Showing s){
    return new Reservation(u ,s , rr.getSeats());
  }

  public ReservationRequest(Integer showingId, List<String> seats) {
    this.showingId = showingId;
    this.seats = seats;
  }
}
