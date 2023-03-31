package dat3.voximovies.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.*;
import dat3.voximovies.repository.*;



import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.repository.CinemaRepository;

import dat3.voximovies.entity.User;

import dat3.voximovies.repository.ReviewRepository;

import dat3.voximovies.service.ChatService;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;


import java.time.LocalDateTime;

import java.util.*;


@Controller
public class SetupDevUsers implements ApplicationRunner {

    MovieRepository movieRepository;
    UserWithRolesRepository userWithRolesRepository;
    CinemaRepository cinemaRepository;
    ShowingRepository showingRepository;
    ReservationRepository reservationRepository;
    ReviewRepository reviewRepository;
    final String passwordUsedByAll;



    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, CinemaRepository cinemaRepository, ReviewRepository reviewRepository, MovieRepository movieRepository, ShowingRepository showingRepository, ReservationRepository reservationRepository) {
        passwordUsedByAll = "test12";
        this.userWithRolesRepository = userWithRolesRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository=cinemaRepository;
        this.reviewRepository = reviewRepository;
        this.showingRepository=showingRepository;
        this.reservationRepository=reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws JsonProcessingException {
        setupCinemas();
    }

    private void setupCinemas() {


        User user1 = new User("Lasse", passwordUsedByAll, "u,mbjsak", "Lasse Dall", "1234", "Højgade 61", "København S", "2300");
        User user2 = new User("Jørgen", passwordUsedByAll, "uajknhk", "Jørgen Jørgensen", "56789098", "Bredgade 3, 2.th", "København K", "2100");
        User user3 = new User("Mathilde", passwordUsedByAll, "jhwdk", "Mathilde Rask", "67676767", "Dovregade 27, 1.tv", "København S", "2300");

        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user1.addRole(Role.CINEMATICER);
        user2.addRole(Role.USER);
        user3.addRole(Role.CINEMATICER);
        Cinema c1 = Cinema.builder().owner(user1).name("Daniels Bio").description("God hjemmebio").zip("2000").street("Aurikelvej 6 1 tv").city("Frederiksberg").build();
        Review review2 = new Review(user3, 5.0, "Super sød type", user1);
        user1.addCinema(c1);
        user1.addUserReview(review2);
      userWithRolesRepository.save(user1);
      userWithRolesRepository.save(user2);
      userWithRolesRepository.save(user3);

      Cinema c2 = Cinema.builder().owner(user3).name("TestNavn1").description("Cozy and comfortable").zip("2100").street("Østerbrogade 12 3 th").city("København Ø").build();
      Cinema c3 = Cinema.builder().owner(user3).name("TestNavn2").description("Modern and spacious").zip("2300").street("Amagerbrogade 45 2 mf").city("København S").build();
      Cinema c4 = Cinema.builder().owner(user3).name("TestNavn3").description("Small but charming").zip("2200").street("Nørrebrogade 33 4 tv").city("København N").build();
      List<String> seats = new ArrayList<>(Arrays.asList("d1","d2","a1", "a2", "a3", "a4", "b1", "b2", "b3", "c1", "c2"));
      c1.setSeats(seats);
      c2.setSeats(seats);

      cinemaRepository.save(c1);
      cinemaRepository.save(c2);
      cinemaRepository.save(c3);
      cinemaRepository.save(c4);



      Review review1 = new Review(user2, 1.0, "Meget lille skærm", c1);


        reviewRepository.save(review1);
        reviewRepository.save(review2);


      Movie movie1 = new Movie("Up", 1.20, "feel good family movie", "family");
      Movie movie2 = new Movie("Zoro", 1.45, "action and romance packed", "spanish western");
      Movie movie3 = new Movie("Saw", 1.38, "gorry horrific and bloody", "horror");

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);



      Showing s4 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(51));
      Showing s5 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(55));
      Showing s6 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(56));
      Showing s7 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(57));
      Showing s8 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(58));
      Showing s9 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(59));
      Showing s10 = new Showing(movie2,c1,400, LocalDateTime.now().plusHours(60));
      

        showingRepository.save(s1);
        showingRepository.save(s2);
        showingRepository.save(s3);
        showingRepository.save(s4);
        showingRepository.save(s5);
        showingRepository.save(s6);
        showingRepository.save(s7);
        showingRepository.save(s8);
        showingRepository.save(s9);
        showingRepository.save(s10);

      Reservation r1 = new Reservation(user1,s1, new ArrayList<>(Arrays.asList("a1","a2")));
      Reservation r2 = new Reservation(user1,s3, new ArrayList<>(Arrays.asList("c3","c4")));
      Reservation r3 = new Reservation(user2,s1, new ArrayList<>(Arrays.asList("a3","a4")));

        reservationRepository.save(r1);
        reservationRepository.save(r2);
        reservationRepository.save(r3);
    }
}
