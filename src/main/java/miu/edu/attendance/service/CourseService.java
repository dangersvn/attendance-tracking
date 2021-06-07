package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;

public interface CourseService {
    Person registerPerson(RegisterUserDto registerUserDto);
    List<Course> getAllCourses();
}
