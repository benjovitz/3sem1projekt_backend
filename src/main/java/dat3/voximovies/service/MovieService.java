package dat3.voximovies.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dat3.voximovies.dto.MovieRequest;
import dat3.voximovies.dto.MovieResponse;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    String apiKey = System.getenv("API_KEY");

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }

    public List<MovieResponse> getMovies() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> movieResponses = movies.stream().map(movie -> new MovieResponse(movie)).toList();

        return movieResponses;

    }

    public Movie findMovie(long id) {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with this ID doesnt exist"));

        return movie;
    }


    public MovieResponse findMovieById(long id) {

        Movie movie = findMovie(id);
        MovieResponse movieResponse = new MovieResponse(movie);

        return movieResponse;
    }


    public MovieResponse addMovie(MovieRequest movieRequest) {

        Movie newMovie = MovieRequest.getMovieEntity(movieRequest);
        newMovie = movieRepository.save(newMovie);

        return new MovieResponse(newMovie);
    }

    public MovieResponse addMovieFromAPI(String movieKey) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://imdb8.p.rapidapi.com/title/get-overview-details?tconst=" + movieKey + "&currentCountry=US"))
            .header("X-RapidAPI-Key", apiKey)
            .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String jsonBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonBody);

            System.out.println(jsonNode);

            String title = jsonNode.get("title").get("title").asText();
            int playTime = jsonNode.get("title").get("runningTimeInMinutes").asInt();
            String description = jsonNode.get("plotOutline").get("text").asText();
            String genre = jsonNode.get("genres").get(0).asText();

            double playTimeAsDouble = (double) playTime;

            MovieRequest movieRequest = new MovieRequest(title, playTimeAsDouble, description, genre);
            Movie movie = MovieRequest.getMovieEntity(movieRequest);

            movieRepository.save(movie);

            return new MovieResponse(movie);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    public ResponseEntity<Boolean> editMovie(MovieRequest body, long id) {

        Movie movieToEdit =  findMovie(id);
        Optional.ofNullable(body.getName()).ifPresent(movieToEdit::setName);
        Optional.ofNullable(body.getDescription()).ifPresent(movieToEdit::setDescription);
        Optional.ofNullable(body.getGenre()).ifPresent(movieToEdit::setGenre);
        movieRepository.save(movieToEdit);

        return new ResponseEntity(true, HttpStatus.OK);
    }



    public void deleteMovieById(long id) {

        Movie movie = findMovie(id);
        movieRepository.delete(movie);
    }

}
