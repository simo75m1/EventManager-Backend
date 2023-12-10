package dat3.eventmanager.service;

import dat3.eventmanager.dto.EventRequest;
import dat3.eventmanager.dto.EventResponse;
import dat3.eventmanager.entity.Event;
import dat3.eventmanager.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventResponse> getAllEvents(){
        return eventRepository.findAll().stream().map(event -> new EventResponse(event)).toList();
    }

    public EventResponse getEventById(int id){
        return new EventResponse(eventRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with this id found")));
    }

    public EventResponse getEventByName(String name){
        return new EventResponse(eventRepository.getEventByName(name));
    }

    public EventResponse addEvent(EventRequest body){
        if(eventRepository.existsByNameAndDate(body.getName(), body.getDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event with that name and date already exists");
        }
        Event newEvent = new Event(body.getName(), body.getDate(), body.getDescription(), body.getCapacity());
        return new EventResponse(eventRepository.save(newEvent));
    }

    public EventResponse editEvent(EventRequest body, int id){
        Event editEvent = eventRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with this id found"));
        editEvent.setName(body.getName());
        editEvent.setDate(body.getDate());
        editEvent.setDescription(body.getDescription());
        editEvent.setCapacity(body.getCapacity());

        return new EventResponse(eventRepository.save(editEvent));
    }

    public EventResponse deleteEvent(int id){
        if(eventRepository.existsById(id)){
            Event event = eventRepository.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with this id found"));
            eventRepository.delete(event);
           return new EventResponse(event);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No event with this id found to delete");
        }
    }

}
