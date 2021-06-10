package miu.edu.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Registration> registrations = new HashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @OrderColumn(name = "timestamp")
    List<BarcodeRecord> barcodeRecords = new ArrayList<>();

    public void registering(Registration registration) {
        registration.setStudent(this);
        registrations.add(registration);    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public void addBarcodeRecord(BarcodeRecord barcodeRecord) {
        barcodeRecord.setStudent(this);
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
