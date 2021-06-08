package miu.edu.attendance.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
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

    @ManyToOne
    Location location;

    public ClassSession(TimeSlot timeSlot, LocalDate date, Location location) {
        if(timeSlot == null) {
            throw new IllegalArgumentException("Required timeslot to create a ClassSession");
        }
        this.timeSlot = timeSlot;
        this.location = location;
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "ClassSession{" +
                "id=" + id +
                ", timeSlot=" + timeSlot +
                ", date=" + date +
                '}';
    }
}
