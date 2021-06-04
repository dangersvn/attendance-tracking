package miu.edu.attendance.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Entity
public class Student extends PersonRole {

    String studentId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    Set<Registration> registrations = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    @OrderColumn(name = "timestamp")
    List<BarcodeRecord> barcodeRecords;

    public void registering(Registration registration) {
        registrations.add(registration);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
