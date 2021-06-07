package miu.edu.attendance.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Entity @Data
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
    @JsonIgnore
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
