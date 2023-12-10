package dat3.eventmanager.service;

import dat3.eventmanager.dto.EventAttendeeRequest;
import dat3.eventmanager.dto.EventAttendeeResponse;
import dat3.eventmanager.entity.Attendee;
import dat3.eventmanager.entity.Event;
import dat3.eventmanager.entity.EventAttendee;
import dat3.eventmanager.repository.AttendeeRepository;
import dat3.eventmanager.repository.EventAttendeeRepository;
import dat3.eventmanager.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventAttendeeService {
    EventAttendeeRepository eventAttendeeRepository;
    EventRepository eventRepository;
    AttendeeRepository attendeeRepository;

    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, EventRepository eventRepository, AttendeeRepository attendeeRepository) {
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public EventAttendeeResponse addEventAttendee(EventAttendeeRequest body){
        if(eventAttendeeRepository.existsByAttendee_UsernameAndEvent_Id(body.getUsername(), body.getEventId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already signed up for that event");

        }
        Attendee attendee = attendeeRepository.findById(body.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Attendee with this username found"));
        Event event = eventRepository.findById(body.getEventId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with this id found"));

        return new EventAttendeeResponse(eventAttendeeRepository.save(new EventAttendee(attendee, event)));
    }

    public void deleteEventAttendee(int id){
        if(eventAttendeeRepository.existsById(id)){
            eventAttendeeRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No event signup with this id found to delete");
        }
    }


}
