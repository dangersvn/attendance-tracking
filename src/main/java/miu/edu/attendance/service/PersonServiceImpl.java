package miu.edu.attendance.service;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.PersonRepository;
import miu.edu.attendance.repository.PersonRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonRoleRepository personRoleRepository;

    @Override
    public Person registerPerson(RegisterUserDto registerUserDto) {
        Person person = new Person(registerUserDto);

        if (registerUserDto.getPersonRole().equalsIgnoreCase(Faculty.class.getSimpleName())) {
            Faculty faculty = personRoleRepository.save(new Faculty());
            person.addRole(faculty);
        }else if (registerUserDto.getPersonRole().equalsIgnoreCase(Student.class.getSimpleName())) {
            Student student = personRoleRepository.save(new Student(registerUserDto.getStudentId()));
            person.addRole(student);
        } else if (registerUserDto.getPersonRole().equalsIgnoreCase(Personnel.class.getSimpleName())) {
            Personnel personnel = personRoleRepository.save(new Personnel());
            person.addRole(personnel);
        } else if(registerUserDto.getPersonRole().equalsIgnoreCase(Admin.class.getSimpleName())) {
            Admin admin = personRoleRepository.save(new Admin());
            person.addRole(admin);
        } else {
            throw new IllegalArgumentException("Invalid personal role");
        }
        return personRepository.save(person);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Person ID=%d does not exist", id)));
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }
}
