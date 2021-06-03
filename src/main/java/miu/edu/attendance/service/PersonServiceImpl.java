package miu.edu.attendance.service;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.PersonRepository;
import miu.edu.attendance.repository.PersonRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository repository;

    @Autowired
    PersonRoleRepository personRoleRepository;

    @Override
    public Person registerPerson(RegisterUserDto registerUserDto) {
        Person person = new Person(registerUserDto);
        if(registerUserDto.getPersonRole().equalsIgnoreCase(Faculty.class.getSimpleName())){
            Faculty faculty= personRoleRepository.save(new Faculty());
            Personnel personnel= personRoleRepository.save(new Personnel());

            person.addRole(faculty);
            person.addRole(personnel);
        } else {
            Student student= personRoleRepository.save(new Student());
            person.addRole(student);
        }
        return repository.save(person);
    }
}
