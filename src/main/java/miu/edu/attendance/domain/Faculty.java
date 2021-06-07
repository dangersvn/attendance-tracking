package miu.edu.attendance.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @ToString
public class Faculty extends PersonRole {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    Set<CourseOffering> courseOfferings = new HashSet<CourseOffering>();

    public void addCourseOffering(CourseOffering courseOffering) {
        courseOfferings.add(courseOffering);
    }
}
