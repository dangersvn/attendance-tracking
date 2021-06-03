package miu.edu.attendance.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean isActive;

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
