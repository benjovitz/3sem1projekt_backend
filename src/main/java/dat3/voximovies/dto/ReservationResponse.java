package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
  private Integer id;
  private String username;

  private Integer showingId;

  private List<String> seats = new ArrayList<>();

public ReservationResponse(Reservation r){
  this.id=r.getId();
  this.username=r.getUser().getUsername();
  this.showingId=r.getShowing().getId();
  this.seats=r.getSeats();

}
}
