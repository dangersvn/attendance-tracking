package miu.edu.attendance.service;


import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.StudentInfoDTO;

import java.util.List;

public interface StudentService {
    List<StudentInfoDTO> getStudentByKeyWord(String keyword);
    Student getStudentById(Integer studentId);
    List<Student> getStudentsByCourseOffering(int courseOffering_id);
}
