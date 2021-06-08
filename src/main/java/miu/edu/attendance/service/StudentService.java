package miu.edu.attendance.service;


import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;

import java.util.List;

public interface StudentService {
    List<Person> getStudentByKeyWord(String keyword);
    Student getStudentById(Integer studentId);
}
