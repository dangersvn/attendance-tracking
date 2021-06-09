package miu.edu.attendance.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor  @Setter @Getter
@Entity
public class Registration {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    CourseOffering courseOffering;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    public Registration(LocalDateTime date, CourseOffering courseOffering) {
        // Todo:  validate registration date
        // Todo: validate a student couldn't register multiple courses at a same block

        this.date = date;
        this.courseOffering = courseOffering;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", date=" + date +
                ", courseOffering=" + courseOffering +
                '}';
    }
}
