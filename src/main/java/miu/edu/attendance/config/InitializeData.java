package miu.edu.attendance.config;

import lombok.extern.log4j.Log4j2;
import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.*;
import miu.edu.attendance.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Log4j2
public class InitializeData {
    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository, PersonService personService, PersonRoleRepository personRoleRepository,
                                      CourseRepository courseRepository, CourseOfferingRepository courseOfferingRepository, RegistrationRepository registrationRepository) {
        return (args) -> {

            // register persons
            RegisterUserDto registerUserDto = new RegisterUserDto("dang", "123", "Dang", "Nguyen", "STUDENT");
            Person student = personService.registerPerson(registerUserDto);

            registerUserDto = new RegisterUserDto("stellavera", "123", "Stellavera ", "Kilcher", "FACULTY");
            Person facultyPerson = personService.registerPerson(registerUserDto);

            registerUserDto = new RegisterUserDto("john", "123", "John", "Smith", "PERSONNEL");
            personService.registerPerson(registerUserDto);

            registerUserDto = new RegisterUserDto("admin", "123", "Miller", "Smith", "ADMIN");
            personService.registerPerson(registerUserDto);

            // fetch all persons
            log.info("Persons found with findAll():");
            log.info("--------------------------------------------------------------");
            for (Person person : personRepository.findAll()) {
                log.info(person.toString());
            }

            // create Courses
            Course ea = new Course("EA", "Enterprise Architecture");
            courseRepository.save(ea);
            Course waa = new Course("WAA", "Web Application Architecture");
            courseRepository.save(waa);
            Course mwa = new Course("MWA", "Modern Web Application");
            courseRepository.save(mwa);

            // fetch all courses
            log.info("Courses found with findAll():");
            log.info("--------------------------------------------------------------");
            for (Course c : courseRepository.findAll()) {
                log.info(c.toString());
            }

            // create CourseOfferings
            CourseOffering eaMay2021 = new CourseOffering(ea, LocalDate.of(2021, 5, 01), LocalDate.of(2021, 5, 22));
            CourseOffering eaJune2021 = new CourseOffering(ea, LocalDate.of(2021, 6, 01), LocalDate.of(2021, 6, 22));
            CourseOffering waaMay2021 = new CourseOffering(waa, LocalDate.of(2021, 5, 01), LocalDate.of(2021, 5, 22));
            CourseOffering waaJune2021 = new CourseOffering(waa, LocalDate.of(2021, 6, 01), LocalDate.of(2021, 6, 22));
            courseOfferingRepository.save(eaMay2021);
            courseOfferingRepository.save(eaJune2021);
            courseOfferingRepository.save(waaMay2021);
            courseOfferingRepository.save(waaJune2021);

            // fetch all course offering
            log.info("CourseOffering found with findAll():");
            log.info("--------------------------------------------------------------");
            for (CourseOffering cf : courseOfferingRepository.findAll()) {
                log.info(cf.toString());
            }

            // assign course offering to faculty
            Faculty f = facultyPerson.asFaculty();
            if(f != null) {
                f.addCourseOffering(eaMay2021);
                f.addCourseOffering(eaJune2021);
                f.addCourseOffering(waaMay2021);
                f.addCourseOffering(waaJune2021);
            }
            personRoleRepository.save(f);

            // fetch all course offering
            log.info("CourseOffering by faculty:");
            log.info("--------------------------------------------------------------");
            Person fPerson = personRepository.findById(facultyPerson.getId()).orElseThrow();
            for (CourseOffering cf : fPerson.asFaculty().getCourseCourseOfferings()) {
                log.info(cf);
            }

            // student register course offerings
            Student s = student.asStudent();
            if(s != null) {
                Registration eaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), eaMay2021);
                s.registering(eaRegistration);
                Registration waaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), waaJune2021);
                s.registering(waaRegistration);
            }
            personRoleRepository.save(s);

            // fetch all registrations from a student
            log.info("Registration of a student:");
            log.info("--------------------------------------------------------------");
            Person studentPerson = personRepository.findById(student.getId()).orElseThrow();
            for (Registration registration : studentPerson.asStudent().getRegistrations()) {
                log.info(registration.toString());
            }

            // create Timeslot

            // create ClassSession

            // create Location




        };
    }
}