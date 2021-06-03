package miu.edu.attendance.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;
import java.util.Set;

@Entity
public class Student extends PersonRole {

    String studentId;

    @OneToMany
    @JoinColumn(name = "student_id")
    Set<Registration> registrations;

    @OneToMany
    @JoinColumn(name = "student_id")
    @OrderColumn(name = "timestamp")
    List<BarcodeRecord> barcodeRecords;

}
