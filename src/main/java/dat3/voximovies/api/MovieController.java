package dat3.voximovies.api;

import dat3.voximovies.dto.MovieRequest;
import dat3.voximovies.dto.MovieResponse;
import dat3.voximovies.repository.MovieRepository;
import dat3.voximovies.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;

    }

    @GetMapping
    List<MovieResponse> getCars(){
        return movieService.getMovies(true);
    }


    @GetMapping(path="/{id}")
    MovieResponse getMovieById(@PathVariable int id) throws Exception {
        return movieService.findMovieById(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MovieResponse addMovie(@RequestBody MovieRequest body){
        return movieService.addMovie(body);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editMovie(@PathVariable int id, @RequestBody MovieRequest body) {
        movieService.editMovie(body,id);
        return ResponseEntity.ok(true);
    }


    @DeleteMapping("/{id}")
    void deleteMovieById(@PathVariable int id) {
        movieService.deleteMovieById(id);
    }




}
