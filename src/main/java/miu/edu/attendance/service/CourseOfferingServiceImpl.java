package miu.edu.attendance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.dto.AvailableTimeSlotDto;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.LocationRepository;
import miu.edu.attendance.service.utils.DateUtils;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    TimeSlotService timeSlotService;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Autowired
    LocationRepository locationRepository;

    /**
     * Create course offering with defaults class sessions: AM Session and PM Session, Monday to Friday.
     * @param course
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate, Location location) {

        CourseOffering courseOffering = new CourseOffering(course, startDate, endDate);
        List<LocalDate> businessDays = DateUtils.getBusinessDaysBetween(startDate, endDate, Optional.empty());

        // create class sessions for morning
        AvailableTimeSlotDto availableTimeSlot = timeSlotService.getAllTimeSlots();
        
        businessDays.stream().forEach(date -> {
            ClassSession morningClassSession = new ClassSession(availableTimeSlot.getMorningSession(), date, location);
            ClassSession afternoonClassSession = new ClassSession(availableTimeSlot.getAfternoonSession(), date, location);
            courseOffering.addClassSession(morningClassSession);
            courseOffering.addClassSession(afternoonClassSession);
        });

        return courseOfferingRepository.save(courseOffering);
    }

    @Override
    public List<CourseOffering> getAllCourseOfferings() {
        return (List<CourseOffering>) courseOfferingRepository.findAll();
    }

    @Override
    public Optional<CourseOffering> getAllCourseOfferings(int id) {
        return courseOfferingRepository.findById(id);
    }


    // Faculty

    @Override
    public List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id) {
        return courseOfferingRepository.getAllCourseOfferingsByFaculty(faculty_id);
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id, int course_id) {
        List<CourseOffering> courseOfferings = courseOfferingRepository.getAllCourseOfferingsByFaculty(faculty_id);
        return courseOfferings.stream().filter(c->c.getCourse().getId()==course_id).collect(Collectors.toList());
    }



    //Student


    @Override
    public List<CourseOffering> getAllCourseOfferingspast() {
        return courseOfferingRepository.getAllCoursePast();
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsfuture() {
        return courseOfferingRepository.getAllCourseFuture();
    }


    @Override
    public List<CourseOffering> getAllCourseOfferingsByStudent(String student_id) {
        return  courseOfferingRepository.getAllCourseOfferingsByStudent( student_id);
    }

    @Override
    public CourseOffering getAllCourseOfferingsByStudentCurrent(String student_id) {
        return courseOfferingRepository.getAllCourseOfferingsByStudentCurrent( student_id);
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsByStudentpast(String student_id) {
        return courseOfferingRepository.getAllCourseOfferingsByStudentpast( student_id);
    }

    @Override
    public List<CourseOffering> getAllCourseOfferingsByStudentfuture(String student_id) {
        return courseOfferingRepository.getAllCourseOfferingsByStudentfuture( student_id);
    }


}
