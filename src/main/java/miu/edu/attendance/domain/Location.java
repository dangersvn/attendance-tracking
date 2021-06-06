package miu.edu.attendance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data @NoArgsConstructor
@Entity
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String description;

    public Location(String description) {
        this.description = description;
    }
}
