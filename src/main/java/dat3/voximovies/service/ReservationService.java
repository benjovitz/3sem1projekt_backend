package dat3.voximovies.service;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Show;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

  private ReservationRepository reservationRepository;
  //private UserRepository userRepository;
  //private ShowRepository showRepository;

  public ReservationService(ReservationRepository reservationRepository){
    this.reservationRepository=reservationRepository;
    //this.userRepository=userRepository;
    //this.showRepository=showRepository;
  }


  public ReservationResponse getReservation(int id){
    Reservation reservation = reservationRepository.findReservationById(id);
    return new ReservationResponse(reservation);
  }

  public List<ReservationResponse> getAllUserReservations(String username){
    List<Reservation> userReservations = reservationRepository.findAllByUserUsername(username);
    List<ReservationResponse> reservationResponses = userReservations.stream().map(r -> new ReservationResponse(r)).toList();
    return reservationResponses;
  }

  public List<ReservationResponse> getAllShowReservations(int showId){
    List<Reservation> showReservations = reservationRepository.findAllByShowId(showId);
    List<ReservationResponse> reservationResponses = showReservations.stream().map(r -> new ReservationResponse(r)).toList();
    return reservationResponses;
  }

  public ReservationResponse addReservation(ReservationRequest rr){
    if(reservationRepository.existsByUserUsernameAndShowId(rr.getUsername(),rr.getShowId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You already have a reservation for this showing");
    }
    if(reservationRepository.existsBySeatsContains(rr.getSeats())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contains seats that are already reserved");
    }
    User user = userRepository.findByUsername(rr.getUsername());
    Show show = showRepository.findById(rr.getShowId());
    Reservation newReservation = rr.getReservationEntity(rr,user, show);
    reservationRepository.save(newReservation);

    return  new ReservationResponse(newReservation);
  }

  public ReservationResponse updateReservation(ReservationRequest rr){
    Reservation updatedReservation = reservationRepository.findByUserUsernameAndShowId(rr.getUsername(),rr.getShowId());
    ArrayList<String> oldSeats = new ArrayList<>(updatedReservation.getSeats());
    ArrayList<String> newSeats = new ArrayList<>(rr.getSeats().stream().filter(r -> oldSeats.contains(r)).toList());
    if(reservationRepository.existsBySeatsContains(newSeats)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contains seats that are already reserved");
    }
    updatedReservation.setSeats(rr.getSeats());
    reservationRepository.save(updatedReservation);
    return new ReservationResponse(updatedReservation);
  }

  public void deleteReservation(int id){
    if(!reservationRepository.existsById(id)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No reservation with such id exists");
    }
    reservationRepository.deleteById(id);
  }


}
