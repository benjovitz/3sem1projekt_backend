package dat3.voximovies.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.ShowingRepository;
import dat3.voximovies.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    UserRepository userRepository;

    ShowingRepository showingRepository;



    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, UserRepository userRepository, ShowingRepository showingRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.userRepository=userRepository;
        this.showingRepository = showingRepository;
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

        User u1 = new User("member1","test12", "dooo", "Benjamin Buttom","22222","kik 12","Smirum","3333");
        Showing s1 = new Showing(200,new ArrayList<>(),150, LocalDateTime.now());
        userRepository.save(u1);
        showingRepository.save(s1);
    }
}
