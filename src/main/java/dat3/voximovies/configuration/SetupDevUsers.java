package dat3.jwtdemo.configuration;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.voximovies.entity.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.util.ArrayList;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
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
        User user1 = new User("Lasse", passwordUsedByAll, "u,mbjsak", "Lasse Dall", "1234", "Højgade 61", "København S", "2300");
        User user2 = new User("Jørgen", passwordUsedByAll, "uajknhk", "Jørgen Jørgensen", "56789098", "Bredgade 3, 2.th", "København K", "2100");
        User user3 = new User("Mathilde", passwordUsedByAll, "jhwdk", "Mathilde Rask", "67676767", "Dovregade 27, 1.tv", "København S", "2300");

        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);

        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
