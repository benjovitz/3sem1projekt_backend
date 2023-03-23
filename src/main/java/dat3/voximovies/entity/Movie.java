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
    private  String descripion;
    private String genre;

    public Movie(String name, double playTime, String descripion, String genre){
        this.name = name;
        this.playTime = playTime;
        this.descripion = descripion;
        this.genre = genre;

    }






}
