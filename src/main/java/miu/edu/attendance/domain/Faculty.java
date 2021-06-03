package miu.edu.attendance.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Faculty extends PersonRole {

    @OneToMany
    @JoinColumn(name = "faculty_id")
    Set<CourseOffering> courseCourseOfferings = new HashSet<CourseOffering>();

}
