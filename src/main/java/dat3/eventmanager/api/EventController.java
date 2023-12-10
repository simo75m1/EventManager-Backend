package dat3.eventmanager.api;

import dat3.eventmanager.dto.EventRequest;
import dat3.eventmanager.dto.EventResponse;
import dat3.eventmanager.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventResponse> getAllEvents(){
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable int id){
        return service.getEventById(id);
    }

    @GetMapping("/name/{name}")
    public EventResponse getEventByName(@PathVariable String name){
        return service.getEventByName(name);
    }

    @PostMapping
    public EventResponse addEvent(@RequestBody EventRequest body){
        return service.addEvent(body);
    }

    @PatchMapping("/{id}")
    public EventResponse editEvent(@RequestBody EventRequest body, @PathVariable int id){
        return service.editEvent(body, id);
    }

    @DeleteMapping("/{id}")
    public EventResponse deleteEvent(@PathVariable int id){
        return service.deleteEvent(id);
    }
}
