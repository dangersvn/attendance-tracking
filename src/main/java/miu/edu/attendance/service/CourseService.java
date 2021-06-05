package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;

public interface CourseService {
    Person registerPerson(RegisterUserDto registerUserDto);
}
