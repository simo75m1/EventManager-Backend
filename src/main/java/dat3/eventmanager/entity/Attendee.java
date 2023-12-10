package dat3.eventmanager.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Attendee extends UserWithRoles {

    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @OneToMany(mappedBy = "attendee", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    List<EventAttendee> eventAttendeeList = new ArrayList<>();

    public void addEventAttendee(EventAttendee ea){
        if(eventAttendeeList == null){
            eventAttendeeList = new ArrayList<>();
        }
        eventAttendeeList.add(ea);
    }

    public Attendee(String user, String password, String email, String phoneNumber) {
        super(user, password, email);
        this.phoneNumber = phoneNumber;
    }
}
