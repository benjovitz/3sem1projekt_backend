package dat3.voximovies.service;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.ReservationRepository;
import dat3.voximovies.repository.ShowingRepository;
import dat3.voximovies.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

  private ReservationRepository reservationRepository;
  private UserRepository userRepository;
  private ShowingRepository showingRepository;

  public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, ShowingRepository showingRepository){
    this.reservationRepository=reservationRepository;
    this.userRepository=userRepository;
    this.showingRepository = showingRepository;
  }


  public ReservationResponse getReservation(long id){
    Reservation reservation = reservationRepository.findReservationById(id);
    return new ReservationResponse(reservation);
  }

  public List<ReservationResponse> getAllUserReservations(String username){
    List<Reservation> userReservations = reservationRepository.findAllByUserUsername(username);
    List<ReservationResponse> reservationResponses = userReservations.stream().map(r -> new ReservationResponse(r)).toList();
    return reservationResponses;
  }

  public List<ReservationResponse> getAllShowReservations(String username, long showId){
    if(!showingRepository.existsByIdAndCinemaOwnerUsername(showId, username)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You do not own a cinema with such Id");
    }
    List<Reservation> showReservations = reservationRepository.findAllByShowingId(showId);
    List<ReservationResponse> reservationResponses = showReservations.stream().map(r -> new ReservationResponse(r)).toList();
    return reservationResponses;
  }

  public ReservationResponse addReservation(String username, ReservationRequest rr){
    if(!userRepository.existsById(username)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not registered as class user");
    }
    if(reservationRepository.existsByUserUsernameAndShowingId(username, rr.getShowingId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You already have a reservation for this showing");
    }
    if(!areSeatsAvailable((ArrayList<String>) rr.getSeats(),rr.getShowingId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contains seats that are already reserved");
    }
    if(rr.getSeats().isEmpty()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You cannot make a reservation without seats");
    }
    User user = userRepository.findByUsername(username);
    Showing showing = showingRepository.findShowingById(rr.getShowingId());
    Reservation newReservation = ReservationRequest.getReservationEntity(rr,user, showing);
    reservationRepository.save(newReservation);

    return  new ReservationResponse(newReservation);
  }

  public ReservationResponse updateReservation(String username, ReservationRequest rr, long resId){
    if(rr.getSeats().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot make a reservation without seats");
    }

    Reservation updatedReservation = reservationRepository.findReservationById(resId);
    if(!updatedReservation.getShowing().getCinema().getOwner().getUsername().equalsIgnoreCase(username)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation is not from your cinema");
    }

    ArrayList<String> oldSeats = new ArrayList<>(updatedReservation.getSeats());
    ArrayList<String> newSeats = new ArrayList<>(rr.getSeats().stream().filter(r -> !(oldSeats.contains(r))).toList());
    System.out.println(oldSeats);
    System.out.println(newSeats);
    if(!areSeatsAvailable(newSeats,updatedReservation.getShowing().getId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contains seats that are already reserved");
    }

    updatedReservation.setSeats(rr.getSeats());
    reservationRepository.save(updatedReservation);
    return new ReservationResponse(updatedReservation);
  }

  public void deleteReservation(String username, long id){
    if(!reservationRepository.existsById(id)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No reservation with such id exists");
    }
    if(!reservationRepository.existsByShowingCinemaOwnerUsernameAndId(username, id)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation is not from your cinema");
    }
    reservationRepository.deleteById(id);
  }

  public boolean areSeatsAvailable(ArrayList<String> seats, long showingId){
    List<Reservation> showingReservations = reservationRepository.findAllByShowingId(showingId);
      for(Reservation res : showingReservations){
        for(String seat : seats){
          if(res.getSeats().contains(seat)){
            return false;
          }
        }
      }
      return true;
  }


}
