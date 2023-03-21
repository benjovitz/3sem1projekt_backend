package dat3.voximovies.api;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService){
    this.reservationService=reservationService;
  }

  //Admin
  @GetMapping("/{id}")
  public ReservationResponse getReservation(@PathVariable int id){
    return reservationService.getReservation(id);
  }


  //User
  @GetMapping("/{username}")
  public List<ReservationResponse> getAllUserReservations(@PathVariable String username){
    return reservationService.getAllUserReservations(username);
  }


  //Cinema-Owner
  @GetMapping("/owner/{showid}")
  public List<ReservationResponse> getAllShowReservations(@PathVariable int showid){
    return reservationService.getAllShowReservations(showid);
  }

  @PostMapping
  public ReservationResponse addReservation(@RequestBody ReservationRequest body){
    return reservationService.addReservation(body);
  }

  @PutMapping
  public ReservationResponse updateReservation(@RequestBody ReservationRequest body){
    return reservationService.updateReservation(body);
  }

  @DeleteMapping("/{id}")
  public void deleteReservation(@PathVariable int id){
    reservationService.deleteReservation(id);
  }


}
