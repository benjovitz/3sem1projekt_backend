package dat3.voximovies.api;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
@CrossOrigin
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService){
    this.reservationService=reservationService;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("{id}")
  public ReservationResponse getReservation(@PathVariable int id){
    return reservationService.getReservation(id);
  }


  @PreAuthorize("hasAuthority('USER')")
  @GetMapping
  public List<ReservationResponse> getAllUserReservations(Principal p){
    return reservationService.getAllUserReservations(p.getName());
  }


  @PreAuthorize("hasAuthority('CINEMATICER')")
  @GetMapping("owner/{showingId}") //Untested
  public List<ReservationResponse> getAllShowReservations(Principal p, @PathVariable int showingId){
    return reservationService.getAllShowReservations(p.getName(),showingId);
  }

  @PreAuthorize("hasAuthority('USER')")
  @PostMapping
  public ReservationResponse addReservation(Principal p, @RequestBody ReservationRequest body){
    return reservationService.addReservation(p.getName(), body);
  }

  @PreAuthorize("hasAuthority('USER')")
  @PutMapping
  public ReservationResponse updateReservation(Principal p, @RequestBody ReservationRequest body){
    return reservationService.updateReservation(p.getName(), body);
  }

  @PreAuthorize("hasAuthority('USER')")
  @DeleteMapping("{id}")
  public void deleteReservation(Principal p, @PathVariable int id){
    reservationService.deleteReservation(p.getName(),id);
  }


}
