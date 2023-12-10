package dat3.eventmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate date;
    private String description;
    private int capacity;

    @ManyToOne
    Location location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    List<EventAttendee> eventAttendeeList = new ArrayList<>();

    public void addEventAttendee(EventAttendee ea){
        if(eventAttendeeList == null){
            eventAttendeeList = new ArrayList<>();
        }
        eventAttendeeList.add(ea);
    }

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public Event(String name, LocalDate date, String description, int capacity) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.capacity = capacity;
        //Add location to constructor later maybe
    }
}
