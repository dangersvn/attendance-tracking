package miu.edu.attendance.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ClassSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    CourseOffering courseOffering;

    LocalDate date;

}
