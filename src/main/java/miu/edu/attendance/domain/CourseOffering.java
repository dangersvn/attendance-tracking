package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
    
    @Future
    LocalDate startDate;
    @Future
    LocalDate endDate;

    @OneToMany(mappedBy = "courseOffering", cascade ={ CascadeType.PERSIST, CascadeType.REMOVE})
    Set<ClassSession> classSessionSet = new HashSet<>();

    public CourseOffering(Course course, LocalDate startDate, LocalDate endDate) {
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;        
    }
    
    public void addClassSession(ClassSession classSession) {
        classSession.setCourseOffering(this);
        classSessionSet.add(classSession);
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", course=" + course +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
