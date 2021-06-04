package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
@NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    LocalDate startDate;
    LocalDate endDate;

    public CourseOffering(Course course, LocalDate startDate, LocalDate endDate) {
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
