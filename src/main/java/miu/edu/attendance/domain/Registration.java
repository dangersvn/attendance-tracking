package miu.edu.attendance.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Registration {

    @Id
    int id;
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    CourseOffering courseOffering;
}
