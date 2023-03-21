package dat3.voximovies.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.Review;
import dat3.voximovies.repository.CinemaRepository;
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

    UserWithRolesRepository userWithRolesRepository;
    CinemaRepository cinemaRepository;
    String passwordUsedByAll;
    ReviewRepository reviewRepository;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, CinemaRepository cinemaRepository, ReviewRepository reviewRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        passwordUsedByAll = "test12";
        this.cinemaRepository=cinemaRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
        setupCinemas();
    }

    private void setupCinemas() {
        // Create some mock data for cinemas
        Cinema c1 = Cinema.builder().user("Daniel").name("Daniels Bio").description("God hjemmebio").zip("2000").street("Aurikelvej 6 1 tv").city("Frederiksberg").build();
        Cinema c2 = Cinema.builder().user("Anna").description("Cozy and comfortable").zip("2100").street("Østerbrogade 12 3 th").city("København Ø").build();
        Cinema c3 = Cinema.builder().user("Lars").description("Modern and spacious").zip("2300").street("Amagerbrogade 45 2 mf").city("København S").build();
        Cinema c4 = Cinema.builder().user("Maria").description("Small but charming").zip("2200").street("Nørrebrogade 33 4 tv").city("København N").build();
       List<String> seats = new ArrayList<>();
       seats.add("a1");
       seats.add("a2");
       seats.add("a3");
       c1.setSeats(seats);
       c2.setSeats(seats);
        Review review = new Review("User1",2.0,"Virkelig lort",c1);
        c1.addReservation(review);
        cinemaRepository.save(c1);
        cinemaRepository.save(c2);
        cinemaRepository.save(c3);
        cinemaRepository.save(c4);
        reviewRepository.save(review);
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}
