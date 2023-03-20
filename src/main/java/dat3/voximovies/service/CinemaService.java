package dat3.voximovies.service;

import dat3.voximovies.dto.CinemaRequest;
import dat3.voximovies.dto.CinemaResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.repository.CinemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository){
        this.cinemaRepository=cinemaRepository;
    }

    public List<CinemaResponse> getCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaResponse> cRes = cinemas.stream().map(cinema -> new CinemaResponse(cinema)).toList();
        return cRes;
    }

    public CinemaResponse findCinema(Long id) {
        cinemaRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cinema with this ID doesnt exist"));
        Optional<Cinema> c = cinemaRepository.findById(String.valueOf(id));
        Cinema cinema = c.orElseThrow();
        return new CinemaResponse(cinema);
    }

    public ResponseEntity<Boolean> addRating(Long id, Double rating) {
        cinemaRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cinema with this ID doesnt exist"));
        Optional<Cinema> c = cinemaRepository.findById(String.valueOf(id));
        Cinema cinema = c.orElseThrow();
        int ratingCounter = cinema.getNumberOfRatings();
        double currentRating = cinema.getRating();
        double newRating = currentRating*ratingCounter;
        newRating = newRating+rating;
        ratingCounter = ratingCounter+1;
        newRating=newRating/ratingCounter;
        cinema.setRating(newRating);
        cinema.setNumberOfRatings(ratingCounter);
        cinemaRepository.save(cinema);
        return null;
    }

    public CinemaResponse addCinema(CinemaRequest request) {
        Cinema cinema = CinemaRequest.getCinemaEntity(request);
        cinemaRepository.save(cinema);
        return new CinemaResponse(cinema);
    }
}
