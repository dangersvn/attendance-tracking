package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Registration {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    CourseOffering courseOffering;

    public Registration(LocalDateTime date, CourseOffering courseOffering) {
        // Todo:  validate registration date
        // Todo: validate a student couldn't register multiple courses at a same block

        this.date = date;
        this.courseOffering = courseOffering;
    }
}
