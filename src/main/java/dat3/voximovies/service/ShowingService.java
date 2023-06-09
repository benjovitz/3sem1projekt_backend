package dat3.voximovies.service;

import dat3.voximovies.dto.ShowingRequest;
import dat3.voximovies.dto.ShowingResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.repository.CinemaRepository;
import dat3.voximovies.repository.MovieRepository;
import dat3.voximovies.repository.ReservationRepository;
import dat3.voximovies.repository.ShowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ShowingService {

  ShowingRepository showingRepository;
  MovieRepository movieRepository;
  CinemaRepository cinemaRepository;

  ReservationRepository reservationRepository;

  public ShowingService(ShowingRepository showingRepository,  MovieRepository movieRepository, CinemaRepository cinemaRepository, ReservationRepository reservationRepository){
    this.showingRepository=showingRepository;
    this.movieRepository=movieRepository;
    this.cinemaRepository=cinemaRepository;
    this.reservationRepository=reservationRepository;
  }




  public ShowingResponse getShowingById(long showId){
    if(!showingRepository.existsById(showId)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Show with this ID doesn't exist");
    }
    Showing showing = showingRepository.findShowingById(showId);
    return new ShowingResponse(showing);
  }


  public List<ShowingResponse> getAllShowings(){
    List<Showing> movieList = showingRepository.findAll();
    List<Showing> movieListOpen = movieList.stream().filter(n-> n.getDateTime().isAfter(LocalDateTime.now().plusHours(1))).toList();
    List<ShowingResponse> showingResponses = movieListOpen.stream().map( s -> new ShowingResponse(s)).toList();
    return showingResponses;
  }

  public List<ShowingResponse> getAllShowingsOfOwner(String username){
    List<Showing> movieList = showingRepository.findAllByCinemaOwnerUsername(username);
    List<Showing> movieListOpen = movieList.stream().filter(n-> n.getDateTime().isAfter(LocalDateTime.now().minusHours(5))).toList();
    List<ShowingResponse> showingResponses = movieListOpen.stream().map( s -> new ShowingResponse(s)).toList();
    return showingResponses;
  }

  public List<ShowingResponse> getAllShowingsWithMovieId(long movieId){
    if(!movieRepository.existsById(movieId)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No shows feature specified movie");
    }
    List<Showing> movieList = showingRepository.findAllByMovieId(movieId);
    List<ShowingResponse> showingResponses = movieList.stream().map( s -> new ShowingResponse(s)).toList();
    return showingResponses;
  }

  public List<ShowingResponse> getAllShowingsForCinema(long cinemaId){
    if(!cinemaRepository.existsById(cinemaId)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No cinema found");
    }
     List<Showing> showings = showingRepository.findAllByCinemaId(cinemaId);
     List<ShowingResponse> showingResponses = showings.stream().map( s -> new ShowingResponse(s)).toList();
     return showingResponses;
  }


  public ShowingResponse createShowing(String username, ShowingRequest request){
    if(!cinemaRepository.existsByIdAndOwnerUsername(request.getCinemaId(), username)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Owner to cinema does not match");
    }
    if(!movieRepository.existsById(request.getMovieId())){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with this ID doesnt exist");
    }
    if(request.getDateTime()==null || request.getPrice()==null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Empty parameter are not allowed");
    }
    if(request.getDateTime().isBefore(LocalDateTime.now())){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot set date in past");
    }
    Movie movie = movieRepository.findMovieById(request.getMovieId());
    Cinema cinema = cinemaRepository.findCinemaById(request.getCinemaId());
    Showing newShowing = new Showing(movie,cinema,request.getPrice(),request.getDateTime());

    showingRepository.save(newShowing);

    return new ShowingResponse(newShowing);
  }

  public ShowingResponse updateShowing(String username, ShowingRequest request,long showId){
    Showing updatedShowing = showingRepository.findShowingById(showId);
    if(!cinemaRepository.existsByIdAndOwnerUsername(updatedShowing.getCinema().getId(), username)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Owner to cinema does not match");
    }
    if(!movieRepository.existsById(request.getMovieId())){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with this ID doesnt exist");
    }
    if(request.getMovieId()!=null){
      Movie movie = movieRepository.findMovieById(request.getMovieId());
      updatedShowing.setMovie(movie);
    }
    if(request.getPrice()!=null){
      updatedShowing.setPrice(request.getPrice());
    }
    if(request.getDateTime()!=null){
      updatedShowing.setDateTime(request.getDateTime());
    }

    showingRepository.save(updatedShowing);

    return new ShowingResponse(updatedShowing);

  }

  public void deleteShowing(String username, long showId){
    if(!showingRepository.existsByIdAndCinemaOwnerUsername(showId, username)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No such show id found for user");
    }
    reservationRepository.deleteAllByShowingId(showId);
    showingRepository.deleteById(showId);
  }

}
