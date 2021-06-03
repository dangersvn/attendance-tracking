package miu.edu.attendance.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
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
}
