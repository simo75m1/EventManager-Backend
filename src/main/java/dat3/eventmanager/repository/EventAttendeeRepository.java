package dat3.eventmanager.repository;

import dat3.eventmanager.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Integer> {
    boolean existsByAttendee_UsernameAndEvent_Id(String username, int eventId);
}
