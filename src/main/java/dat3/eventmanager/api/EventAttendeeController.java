package dat3.eventmanager.api;

import dat3.eventmanager.dto.EventAttendeeRequest;
import dat3.eventmanager.dto.EventAttendeeResponse;
import dat3.eventmanager.dto.EventRequest;
import dat3.eventmanager.service.EventAttendeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attend-event")
public class EventAttendeeController {

    EventAttendeeService eventAttendeeService;

    public EventAttendeeController(EventAttendeeService eventAttendeeService) {
        this.eventAttendeeService = eventAttendeeService;
    }

    @PostMapping
    public EventAttendeeResponse addEventAttendee(@RequestBody EventAttendeeRequest body){
        return eventAttendeeService.addEventAttendee(body);
    }

    @DeleteMapping("/{id}")
    public void deleteEventAttendee(@PathVariable int id){
        eventAttendeeService.deleteEventAttendee(id);
    }
}
