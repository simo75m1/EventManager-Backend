package dat3.eventmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.eventmanager.entity.Event;
import lombok.*;

import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {

    private int id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private String description;
    private int remainingSpots;
    private List<EventAttendeeResponse> eventAttendeeResponseList;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.date = event.getDate();
        this.description = event.getDescription();
        this.eventAttendeeResponseList = event.getEventAttendeeList().stream().map(eventAttendee -> new EventAttendeeResponse(eventAttendee)).toList();
        this.remainingSpots = calculateRemainingSpots(event);
    }

    public int calculateRemainingSpots(Event event){
        int capacity = event.getCapacity();
        int occupiedSeats = eventAttendeeResponseList.size();
        return capacity - occupiedSeats;
    }
}
