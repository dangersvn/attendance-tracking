package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.PersonRole;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.PersonRepository;
import miu.edu.attendance.repository.PersonRoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PersonRoleRepository personRoleRepository;


    @InjectMocks
    PersonServiceImpl personService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_register_person_it_should_return_person() {
        RegisterUserDto registerUserDto = new RegisterUserDto("dang", "123", "dang", "nguyen", "STUDENT", "612345");
        Student student = new Student(registerUserDto.getStudentId());
        Person studentPerson = new Person(registerUserDto);
        studentPerson.addRole(student);



        when(personRepository.save(any(Person.class))).thenReturn(studentPerson);
        when(passwordEncoder.encode(any(String.class))).thenReturn(any(String.class));
        when(personRoleRepository.save(any(PersonRole.class))).thenReturn(student);


        // actual test
        Person created = personService.registerPerson(registerUserDto);




        Assertions.assertThat(created.getFirstName()).isSameAs(registerUserDto.getFirstName());
    }

    @Test
    void when_find_person_by_id_should_return_person() {

        RegisterUserDto registerUserDto = new RegisterUserDto("dang", "123", "dang", "nguyen", "STUDENT", "612345");
        Person studentPerson = new Person(registerUserDto);

        //mock
        when(personRepository.findById(any(Integer.class))).thenReturn(Optional.of(studentPerson));

        //actual test
        Person retrieved = personService.findById(324);
        Assertions.assertThat(retrieved.getFirstName()).isSameAs(registerUserDto.getFirstName());

    }

    @Test
    void update() {
    }

    @Test
    void findByUsername() {
    }
}