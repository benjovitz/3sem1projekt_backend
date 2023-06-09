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
@RequestMapping("/api/movies/")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;

    }

    @GetMapping
    List<MovieResponse> getMovies(){
        return movieService.getMovies();
    }


    @GetMapping("{id}")
    MovieResponse getMovieById(@PathVariable long id) throws Exception {
        return movieService.findMovieById(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MovieResponse addMovie(@RequestBody MovieRequest body){
        return movieService.addMovie(body);
    }

    @PostMapping(path = "{movieKey}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MovieResponse addMovieWithKey(@PathVariable String movieKey) {
        System.out.println(movieKey);
        return movieService.addMovieFromAPI(movieKey);
    }


    @PutMapping("{id}")
    public ResponseEntity<Boolean> editMovie(@PathVariable long id, @RequestBody MovieRequest body) {
        movieService.editMovie(body,id);
        return ResponseEntity.ok(true);
    }


    @DeleteMapping("{id}")
    void deleteMovieById(@PathVariable long id) {
        movieService.deleteMovieById(id);
    }




}
