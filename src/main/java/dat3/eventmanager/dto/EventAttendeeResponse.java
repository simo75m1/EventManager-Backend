package dat3.eventmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.eventmanager.entity.EventAttendee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventAttendeeResponse {

    int id;
    String username;
    String email;

    String eventName;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    LocalDate eventDate;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    LocalDate registerDate;

    public EventAttendeeResponse(EventAttendee eventAttendee){
        this.id = eventAttendee.getId();
        this.username = eventAttendee.getAttendee().getUsername();
        this.email = eventAttendee.getAttendee().getEmail();
        this.eventName = eventAttendee.getEvent().getName();
        this.eventDate = eventAttendee.getEvent().getDate();
        this.registerDate = eventAttendee.getRegisterDate();
    }
}
