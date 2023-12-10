package dat3.eventmanager.configuration;

import dat3.eventmanager.dto.EventResponse;
import dat3.eventmanager.entity.Attendee;
import dat3.eventmanager.entity.Event;
import dat3.eventmanager.entity.Location;
import dat3.eventmanager.repository.AttendeeRepository;
import dat3.eventmanager.repository.EventRepository;
import dat3.eventmanager.repository.LocationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    AttendeeRepository attendeeRepository;
    EventRepository eventRepository;
    LocationRepository locationRepository;
    UserWithRolesRepository userWithRolesRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;

    public SetupDevUsers(AttendeeRepository attendeeRepository, EventRepository eventRepository, LocationRepository locationRepository, UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
        this.attendeeRepository = attendeeRepository;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        eventRepository.save(new Event("Roskilde Festival", LocalDate.now(), "Største Festival i danmark", 5000));
        eventRepository.save(new Event("Ringsted Festival", LocalDate.now().plusDays(2), "Næst største festival i danmark", 3000));


        Attendee a1 = new Attendee("attendee1", passwordUsedByAll, "simon@kea.dk", "+4592940392");
        a1.addRole(Role.USER);
        attendeeRepository.save(a1);

        setupUserWithRoleUsers();


    }

     /*****************************************************************************************
     IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO

     If you see the lines below in log-outputs on Azure, forget whatever had your attention on, AND FIX THIS PROBLEM

     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}
