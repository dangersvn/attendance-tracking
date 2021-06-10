package miu.edu.attendance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;

public interface CourseOfferingService {
    CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate, Location location);
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id);
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id, int course_id);
    List<CourseOffering> getAllCourseOfferingsByStudent(String student_id);
    CourseOffering getAllCourseOfferingsByStudentCurrent(String student_id);
    List<CourseOffering> getAllCourseOfferingsByStudentpast(String student_id);
    List<CourseOffering> getAllCourseOfferingsByStudentfuture(String student_id);
    Iterable<CourseOffering> getAllCourseOfferings();
    Optional<CourseOffering> getAllCourseOfferings(int id);
    Iterable<CourseOffering> getAllCourseOfferingspast();
    List<CourseOffering> getAllCourseOfferingsfuture();
    List<CourseOffering> getAllCourseOffering(Integer studentId);



}
