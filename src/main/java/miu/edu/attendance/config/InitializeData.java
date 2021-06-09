package miu.edu.attendance.config;

import lombok.extern.log4j.Log4j2;
import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
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

@Configuration
@Log4j2
public class InitializeData {
    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository, PersonService personService, PersonRoleRepository personRoleRepository,
                                      CourseRepository courseRepository, CourseOfferingRepository courseOfferingRepository, CourseOfferingService courseOfferingSerivce,
                                      RegistrationRepository registrationRepository, TimeSlotRepository timeSlotRepository, StudentRepo studentRepo, LocationRepository locationRepository) {
        return (args) -> {

            // create admin user
            RegisterUserDto registerUserDto = new RegisterUserDto("admin", "123", "ADMIN", "Mr.", "ADMIN", null);
            Person admin = personService.registerPerson(registerUserDto);

            // register persons
            registerUserDto = new RegisterUserDto("dang", "123", "Dang", "Nguyen", "STUDENT", "612345");
            Person studentPerson = personService.registerPerson(registerUserDto);

            registerUserDto = new RegisterUserDto("stellavera", "123", "Stellavera ", "Kilcher", "FACULTY", null);
            Person facultyPerson = personService.registerPerson(registerUserDto);

            registerUserDto = new RegisterUserDto("john", "123", "John", "Smith", "PERSONNEL", null);
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

            // create Location
            Location location = new Location("Room 45C - Verill Hall building");
            locationRepository.save(location);
            Location location2 = new Location("Room 46C - Verill Hall building");
            locationRepository.save(location2);
            Location location3 = new Location("Room 47C - Verill Hall building");
            locationRepository.save(location3);
            Location location4 = new Location("Room 26 - Mc Laughlin building");
            locationRepository.save(location4);
            Location location5 = new Location("Room 27 - Mc Laughlin building");
            locationRepository.save(location5);
            Location location6 = new Location("Room 28 - Mc Laughlin building");
            locationRepository.save(location6);
            // fetch all registrations from a student
            log.info("Location found by findAll():");
            log.info("--------------------------------------------------------------");
            for (Location loc : locationRepository.findAll()) {
                log.info(loc.toString());
            }


            // create CourseOfferings
            LocalDate startDate =  LocalDate.now().minusDays(5);
            CourseOffering eaThisMonth = courseOfferingSerivce.createCourseOffering(ea, startDate, startDate.plusDays(21), location);
            CourseOffering eaNextMonth = courseOfferingSerivce.createCourseOffering(ea, startDate.plusMonths(1), startDate.plusMonths(1).plusDays(22), location2);
            CourseOffering waaThisMonth = courseOfferingSerivce.createCourseOffering(waa, startDate, startDate.plusDays(21), location3);
            CourseOffering waaNextMonth = courseOfferingSerivce.createCourseOffering(waa, startDate.plusMonths(1), startDate.plusMonths(1).plusDays(22), location4);

            // fetch all course offering
            log.info("CourseOffering found with findAll():");
            log.info("--------------------------------------------------------------");
            for (CourseOffering cf : courseOfferingRepository.findAll()) {
                log.info(cf.toString());
            }

            // assign course offering to faculty
            Faculty f = facultyPerson.asFaculty().orElseThrow(() -> new IllegalStateException(String.format("The person with ID=%d is not a Faculty.", facultyPerson.getId())));;
            f.addCourseOffering(eaThisMonth);
            f.addCourseOffering(eaNextMonth);
            f.addCourseOffering(waaThisMonth);
            f.addCourseOffering(waaNextMonth);
            personRoleRepository.save(f);

            // fetch all course offering
            log.info("CourseOffering by faculty:");
            log.info("--------------------------------------------------------------");
            Person fPerson = personRepository.findById(facultyPerson.getId()).orElseThrow();
            Faculty faculty = fPerson.asFaculty().orElseThrow(() -> new IllegalStateException(String.format("The person with ID=%d is not a Faculty.", facultyPerson.getId())));;
            for (CourseOffering cf : faculty.getCourseOfferings()) {
                log.info(cf);
            }

            // student register course offerings
            Student s = studentPerson.asStudent().orElseThrow(() -> new IllegalStateException(String.format("The person with ID=%d is not a Student.", studentPerson.getId())));
            Registration eaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), eaThisMonth);
            s.registering(eaRegistration);
            Registration waaRegistration = new Registration(LocalDateTime.of(2021, 3, 1, 12, 0), waaNextMonth);
            s.registering(waaRegistration);
            personRoleRepository.save(s);

            // fetch all registrations from a student
            log.info("Registration of a student:");
            log.info("--------------------------------------------------------------");
            Person stuPerson = personRepository.findById(studentPerson.getId()).orElseThrow();
            Student student = stuPerson.asStudent().orElseThrow(() -> new IllegalStateException(String.format("The person with ID=%d is not a Student.", studentPerson.getId())));
            for (Registration registration : student.getRegistrations()) {
                log.info(registration.toString());
            }


            // create barcode record for a student
            // given: Student_ID, Location ID

//            BarcodeRecordDTO barcodeRecord = new BarcodeRecordDTO();
//            barcodeRecord.setBarcode("dang");
//            barcodeRecord.setLocationId(bar);
//
//            BarcodeRecord barcodeRecord = new BarcodeRecord();
//            barcodeRecord.setClassSession(1);
        };
    }
}