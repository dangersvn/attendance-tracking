package miu.edu.attendance.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean active = true;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    public String getName() {
        return this.getClass().getSimpleName().toUpperCase();
    }
}
