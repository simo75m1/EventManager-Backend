package dat3.eventmanager.repository;

import dat3.eventmanager.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    boolean existsByNameAndDate(String name, LocalDate date);
    Event getEventByName(String name);
}
