package dat3.voximovies.service;


import dat3.voximovies.dto.MovieRequest;
import dat3.voximovies.dto.MovieResponse;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;

    }

    public List<MovieResponse> getMovies() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse>movieResponses = movies.stream().map(movie->new MovieResponse(movie)).toList();

        return movieResponses;

    }

    public Movie findMovie(int id){

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with this ID doesnt exist"));

        return movie;
    }


    public MovieResponse findMovieById(int id) {

        Movie movie = findMovie(id);
        MovieResponse movieResponse = new MovieResponse(movie);

        return movieResponse;
    }


    public MovieResponse addMovie(MovieRequest movieRequest) {

        Movie newMovie = MovieRequest.getMovieEntity(movieRequest);
        newMovie = movieRepository.save(newMovie);

        return new MovieResponse(newMovie);
    }


    public ResponseEntity<Boolean> editMovie(MovieRequest body, int id) {

        Movie movieToEdit =  findMovie(id);
        Optional.ofNullable(body.getName()).ifPresent(movieToEdit::setName);
        Optional.ofNullable(body.getDescripion()).ifPresent(movieToEdit::setDescripion);
        Optional.ofNullable(body.getGenre()).ifPresent(movieToEdit::setGenre);
        movieRepository.save(movieToEdit);

        return new ResponseEntity(true, HttpStatus.OK);
    }



    public void deleteMovieById(int id) {

        Movie movie = findMovie(id);
        movieRepository.delete(movie);
    }

}
