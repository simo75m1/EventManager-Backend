package dat3.eventmanager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EventAttendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDate registerDate;

    @ManyToOne
    private Attendee attendee;

    @ManyToOne
    private Event event;

    public EventAttendee(Attendee attendee, Event event) {
        this.attendee = attendee;
        this.event = event;
        attendee.addEventAttendee(this);
        event.addEventAttendee(this);
    }
}
