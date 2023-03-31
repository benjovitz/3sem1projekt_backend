package dat3.voximovies.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MovieRequest {


    private String name;
    private double playTime;
    private  String description;
    private String genre;


    public static Movie getMovieEntity(MovieRequest movieRequest){
        return new Movie(movieRequest.name, movieRequest.playTime, movieRequest.description, movieRequest.genre);
    }


    public MovieRequest(Movie movie){
        this.name = movie.getName();
        this.playTime = movie.getPlayTime();
        this.description = movie.getDescription();
        this.genre = movie.getGenre();
    }


}
