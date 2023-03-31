package dat3.voximovies.service;

import dat3.voximovies.dto.MovieRequest;
import dat3.voximovies.dto.MovieResponse;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ResponseActions;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;
    MovieService movieService;

    @BeforeEach
    void setup(){

        movieService = new MovieService(movieRepository);
    }

    @Test
    void getMovies(){
        Movie m1 = new Movie();
        Movie m2 = new Movie();

        Mockito.when(movieRepository.findAll()).thenReturn(List.of(m1,m2));
        List<MovieResponse> mr = movieService.getMovies();
        assertEquals(2,mr.size());
    }
    @Test
    void addMovies(){

        Movie m1 = new Movie("name1", 1.20, "very funny", "comedy");
        Movie m2 = new Movie("name2", 1.26, "not funny", "not a comedy");

        Mockito.when(movieRepository.save(any(Movie.class))).thenReturn(m1);

        MovieRequest mr = new MovieRequest(m1);


        MovieResponse mr1 = movieService.addMovie(mr);

        assertEquals("name1", mr1.getName());

    }

    @Test
    void editMovies(){

        Movie movie1 = new Movie("up",1.8, "god film", "feel good family movie", "family");

        Mockito.when(movieRepository.findById(movie1.getId())).thenReturn(Optional.of(movie1));
        MovieRequest mr = new MovieRequest(movie1);

        ResponseEntity<Boolean> responseEntity = movieService.editMovie(mr,movie1.getId());

        ResponseEntity<Boolean> responseEntity1 = new ResponseEntity(true, HttpStatus.OK);

        assertEquals(responseEntity,responseEntity1);


    }

}