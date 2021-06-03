package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;
import org.springframework.stereotype.Service;

public interface PersonService {
    Person registerPerson(RegisterUserDto registerUserDto);
}
