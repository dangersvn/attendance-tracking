package miu.edu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor @Data
@Entity
public class ClassSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    CourseOffering courseOffering;

    LocalDate date;

    public ClassSession(TimeSlot timeSlot, LocalDate date) {
        if(timeSlot == null) {
            throw new IllegalArgumentException("Required timeslot to create a ClassSession");
        }
        this.timeSlot = timeSlot;
        this.date = date;
    }
}
