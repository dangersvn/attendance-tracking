package miu.edu.attendance.dto;

import lombok.Data;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Student;

@Data
public class ClassSessionDTO {
    ClassSession classSession;
    Student student;


}
