package miu.edu.attendance.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter @NoArgsConstructor
@Entity
public class Student extends PersonRole {

    @Column(unique = true)
    String studentId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    Set<Registration> registrations = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    @OrderColumn(name = "timestamp")
    List<BarcodeRecord> barcodeRecords = new ArrayList<>();

    public void registering(Registration registration) {
        registrations.add(registration);
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public void addBarcodeRecord(BarcodeRecord barcodeRecord) {
        barcodeRecords.add(barcodeRecord);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
