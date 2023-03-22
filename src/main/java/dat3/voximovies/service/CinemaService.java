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
    public Cinema findCinemaByID(Long id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cinema with this ID doesnt exist"));
        return cinema;
    }

    public List<CinemaResponse> getCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaResponse> cRes = cinemas.stream().map(cinema -> new CinemaResponse(cinema)).toList();
        return cRes;
    }

    public CinemaResponse findCinema(Long id) {
        Cinema cinema = findCinemaByID(id);
        return new CinemaResponse(cinema);
    }

    public CinemaResponse addCinema(CinemaRequest request) {
        Cinema cinema = CinemaRequest.getCinemaEntity(request);
        cinemaRepository.save(cinema);
        return new CinemaResponse(cinema);
    }

    public void deleteCinema(Long id) {
        Cinema cinema = findCinemaByID(id);
        cinemaRepository.delete(cinema);
    }

    public CinemaResponse editCinema(Long id, CinemaRequest request) {
        Cinema cinema = findCinemaByID(id);
        Optional.ofNullable(request.getCity()).ifPresent(cinema::setCity);
        Optional.ofNullable(request.getZip()).ifPresent(cinema::setZip);
        Optional.ofNullable(request.getStreet()).ifPresent(cinema::setStreet);
        Optional.ofNullable(request.getDescription()).ifPresent(cinema::setDescription);
        Optional.ofNullable(request.getUser()).ifPresent(cinema::setUser);
        cinemaRepository.save(cinema);
        return new CinemaResponse(cinema);
    }
}
