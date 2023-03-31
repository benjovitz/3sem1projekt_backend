package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.voximovies.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private long id;
    private String name;
    private double playTime;
    private  String description;
    private String genre;

    public MovieResponse(Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.playTime = movie.getPlayTime();
        this.description = movie.getDescription();
        this.genre = movie.getGenre();

    }



}
