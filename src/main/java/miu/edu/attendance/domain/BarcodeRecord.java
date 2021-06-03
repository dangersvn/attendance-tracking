package miu.edu.attendance.domain;

import javax.persistence.*;

@Entity
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
}
