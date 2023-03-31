package dat3.voximovies.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double playTime;
    private  String description;
    private String genre;

    private String poster;

    public Movie(String name, double playTime, String description, String genre){
        this.name = name;
        this.playTime = playTime;
        this.description = description;
        this.genre = genre;
    }

    public Movie(String name, double playTime, String description, String genre, String poster){
        this.name = name;
        this.playTime = playTime;
        this.description = description;
        this.genre = genre;
        this.poster = poster;
    }






}
