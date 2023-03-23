package dat3.voximovies.service;

import dat3.voximovies.entity.Showing;
import dat3.voximovies.repository.CinemaRepository;
import dat3.voximovies.repository.MovieRepository;
import dat3.voximovies.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShowingService {

  ShowingRepository showingRepository;
  //MovieRepository movieRepository;
  //CinemaRepository cinemaRepository;
  //ReservationRepository reservationRepository

  public ShowingService(ShowingRepository showingRepository){
    this.showingRepository=showingRepository;
  }


  public Showing getShowingById(int showId){

  }

  public List<Showing> getAllShowingsForCinema(){}


  public Showing createShowing(){}

  public Showing updateShowing(){}

  public void deleteShowing(){}

}
