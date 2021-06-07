package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.PersonRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonRoleServiceImpl implements PersonRoleService {
    @Autowired
    PersonService personService;

    @Override
    public Person updateRole(int personId, PersonRoleDto personRoleDto) {
        Person person = personService.findById(personId);

        person.updateRole(personRoleDto);

        return personService.update(person);
    }
}