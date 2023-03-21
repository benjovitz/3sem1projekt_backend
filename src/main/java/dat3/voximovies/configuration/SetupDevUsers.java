package dat3.voximovies.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.Movie;
import dat3.voximovies.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    MovieRepository movieRepository;
    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MovieRepository movieRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.movieRepository = movieRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
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

        Movie movie1 = new Movie("Up", 1.20, "feel good family movie", "family");
        Movie movie2 = new Movie("Zoro", 1.45, "action and romance packed", "spanish western");
        Movie movie3 = new Movie("saw", 1.38, "gorry horrific and bloody", "horror");

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);





    }



}
