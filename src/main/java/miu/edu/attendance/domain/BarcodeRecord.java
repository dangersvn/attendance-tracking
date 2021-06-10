package miu.edu.attendance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    public BarcodeRecord(ClassSession classSession, Location location) {
        this.classSession = classSession;
        this.location = location;
    }
}
