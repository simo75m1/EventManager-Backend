package dat3.eventmanager.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.eventmanager.entity.Attendee;
import dat3.eventmanager.entity.EventAttendee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendeeResponse {

    String username;
    String email;
    String phoneNumber;
    List<EventAttendeeResponse> eventAttendeeResponseList;

    public AttendeeResponse(Attendee attendee) {
        this.username = attendee.getUsername();
        this.email = attendee.getEmail();
        this.phoneNumber = attendee.getPhoneNumber();
        this.eventAttendeeResponseList = attendee.getEventAttendeeList().stream().map(eventAttendee -> new EventAttendeeResponse(eventAttendee)).toList();
    }
}
