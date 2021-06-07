package miu.edu.attendance.config;

import lombok.extern.log4j.Log4j2;
import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.*;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//@Configuration
@Log4j2
public class InitializeData {
    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository, PersonService personService, PersonRoleRepository personRoleRepository,
                                      CourseRepository courseRepository, CourseOfferingRepository courseOfferingRepository, CourseOfferingService courseOfferingSerivce,
                                      RegistrationRepository registrationRepository, TimeSlotRepository timeSlotRepository, StudentRepo studentRepo, LocationRepository locationRepository) {
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

            // fetch all student
            log.info("Students found with findAll():");
            log.info("--------------------------------------------------------------");
            for (Student person : studentRepo.findAll()) {
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

            // create Timeslot
            TimeSlot amTimeSlot = new TimeSlot("AM", LocalTime.of(10,0), LocalTime.of(12,15), "Morning time slot");
            TimeSlot pmTimeSlot = new TimeSlot("PM", LocalTime.of(13,30), LocalTime.of(15,15), "Afternoon time slot");
            timeSlotRepository.save(amTimeSlot);
            timeSlotRepository.save(pmTimeSlot);
            // fetch all time slots
            log.info("TimeSlots found with findAll():");
            log.info("--------------------------------------------------------------");
            for (TimeSlot timeSlot : timeSlotRepository.findAll()) {
                log.info(timeSlot.toString());
            }

            // create CourseOfferings
            LocalDate startDate =  LocalDate.now().plusDays(1);
            CourseOffering eaThisMonth = courseOfferingSerivce.createCourseOffering(ea, startDate, startDate.plusDays(21));
            CourseOffering eaNextMonth = courseOfferingSerivce.createCourseOffering(ea, startDate.plusMonths(1), startDate.plusMonths(1).plusDays(22));
            CourseOffering waaThisMonth = courseOfferingSerivce.createCourseOffering(waa, startDate, startDate.plusDays(21));
            CourseOffering waaNextMonth = courseOfferingSerivce.createCourseOffering(waa, startDate.plusMonths(1), startDate.plusMonths(1).plusDays(22));

            // fetch all course offering
            log.info("CourseOffering found with findAll():");
            log.info("--------------------------------------------------------------");
            for (CourseOffering cf : courseOfferingRepository.findAll()) {
                log.info(cf.toString());
            }

            // assign course offering to faculty
            Faculty f = facultyPerson.asFaculty();
            if(f != null) {
                f.addCourseOffering(eaThisMonth);
                f.addCourseOffering(eaNextMonth);
                f.addCourseOffering(waaThisMonth);
                f.addCourseOffering(waaNextMonth);
            }
            personRoleRepository.save(f);

            // fetch all course offering
            log.info("CourseOffering by faculty:");
            log.info("--------------------------------------------------------------");
            Person fPerson = personRepository.findById(facultyPerson.getId()).orElseThrow();
            for (CourseOffering cf : fPerson.asFaculty().getCourseOfferings()) {
                log.info(cf);
            }

            // student register course offerings
            Student s = student.asStudent();
            if(s != null) {
                Registration eaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), eaThisMonth);
                s.registering(eaRegistration);
                Registration waaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), waaNextMonth);
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

            // create Location
            Location location = new Location("Room 45C - Verill Hall building");
            locationRepository.save(location);
            location = new Location("Room 46C - Verill Hall building");
            locationRepository.save(location);
            location = new Location("Room 47C - Verill Hall building");
            locationRepository.save(location);
            location = new Location("Room 26 - Mc Laughlin building");
            locationRepository.save(location);
            location = new Location("Room 27 - Mc Laughlin building");
            locationRepository.save(location);
            location = new Location("Room 28 - Mc Laughlin building");
            locationRepository.save(location);
            // fetch all registrations from a student
            log.info("Location found by findAll():");
            log.info("--------------------------------------------------------------");
            for (Location loc : locationRepository.findAll()) {
                log.info(loc.toString());
            }


        };
    }
}