package miu.edu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class BarcodeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    long timestamp;

    @ManyToOne
    @JoinColumn(name = "class_session_id")
    ClassSession classSession;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    public BarcodeRecord(ClassSession classSession, Location location) {
        this.classSession = classSession;
        this.location = location;
    }
}
