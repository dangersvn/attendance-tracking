package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"abbreviation", "begin_time", "end_time"})
})
public class TimeSlot {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String abbreviation;
    @Column(name = "begin_time", updatable = false)
    LocalTime beginTime;
    @Column(name = "end_time", updatable = false)
    LocalTime endTime;

    String description;

    public TimeSlot(String abbreviation, LocalTime beginTime, LocalTime endTime, String description) {
        this.abbreviation = abbreviation;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.description = description;
    }
}
