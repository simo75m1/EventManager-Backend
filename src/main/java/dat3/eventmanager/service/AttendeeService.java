package dat3.eventmanager.service;

import dat3.eventmanager.dto.AttendeeResponse;
import dat3.eventmanager.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {
    AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<AttendeeResponse> getAllAttendees(){
        return attendeeRepository.findAll().stream().map(attendee -> new AttendeeResponse(attendee)).toList();
    }
}
