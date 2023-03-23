package dat3.voximovies.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.repository.MovieRepository;


import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.repository.CinemaRepository;

import dat3.voximovies.entity.User;

import dat3.voximovies.repository.ReviewRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class SetupDevUsers implements ApplicationRunner {

    MovieRepository movieRepository;
    UserWithRolesRepository userWithRolesRepository;
    CinemaRepository cinemaRepository;
    String passwordUsedByAll;
    ReviewRepository reviewRepository;


    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, CinemaRepository cinemaRepository, ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.movieRepository = movieRepository;
        passwordUsedByAll = "test12";
        this.cinemaRepository=cinemaRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupCinemas();
    }

    private void setupCinemas() {


        User user1 = new User("Lasse", passwordUsedByAll, "u,mbjsak", "Lasse Dall", "1234", "Højgade 61", "København S", "2300");
        User user2 = new User("Jørgen", passwordUsedByAll, "uajknhk", "Jørgen Jørgensen", "56789098", "Bredgade 3, 2.th", "København K", "2100");
        User user3 = new User("Mathilde", passwordUsedByAll, "jhwdk", "Mathilde Rask", "67676767", "Dovregade 27, 1.tv", "København S", "2300");

        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.CINEMATICER);
      userWithRolesRepository.save(user1);
      userWithRolesRepository.save(user2);
      userWithRolesRepository.save(user3);
      Cinema c1 = Cinema.builder().owner(user1).name("Daniels Bio").description("God hjemmebio").zip("2000").street("Aurikelvej 6 1 tv").city("Frederiksberg").build();
      Cinema c2 = Cinema.builder().owner(user3).description("Cozy and comfortable").zip("2100").street("Østerbrogade 12 3 th").city("København Ø").build();
      Cinema c3 = Cinema.builder().owner(user3).description("Modern and spacious").zip("2300").street("Amagerbrogade 45 2 mf").city("København S").build();
      Cinema c4 = Cinema.builder().owner(user3).description("Small but charming").zip("2200").street("Nørrebrogade 33 4 tv").city("København N").build();
      List<String> seats = new ArrayList<>();
      seats.add("a1");
      seats.add("a2");
      seats.add("a3");
      c1.setSeats(seats);
      c2.setSeats(seats);
      cinemaRepository.save(c1);
      cinemaRepository.save(c2);
      cinemaRepository.save(c3);
      cinemaRepository.save(c4);




      Review review2 = new Review(user3, 5.0, "Super sød type", user1);
      reviewRepository.save(review2);
      Review review1 = new Review(user2, 1.0, "Meget lille skærm", c1);
      reviewRepository.save(review1);



      Movie movie1 = new Movie("Up", 1.20, "feel good family movie", "family");
        Movie movie2 = new Movie("Zoro", 1.45, "action and romance packed", "spanish western");
        Movie movie3 = new Movie("saw", 1.38, "gorry horrific and bloody", "horror");

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);






    }
}
