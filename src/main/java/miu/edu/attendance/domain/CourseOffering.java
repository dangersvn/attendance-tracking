package miu.edu.attendance.domain;

import javax.persistence.*;
import java.time.LocalDate;

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

}
