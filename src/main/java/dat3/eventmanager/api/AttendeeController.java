package dat3.eventmanager.api;

import dat3.eventmanager.dto.AttendeeResponse;
import dat3.eventmanager.service.AttendeeService;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attendee")
public class AttendeeController {
    AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public List<AttendeeResponse> getAllAttendees(){
        return attendeeService.getAllAttendees();
    }
}
