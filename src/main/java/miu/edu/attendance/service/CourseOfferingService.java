package miu.edu.attendance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;

public interface CourseOfferingService {
    CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate, Location location);
    List<CourseOffering> getAllCourseOfferings();
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id);
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id, int course_id);
    Optional<CourseOffering> getAllCourseOfferings(int id);
    CourseOffering getAllCourseCurrent();
    List<CourseOffering> getAllCoursePast();
    List<CourseOffering> getAllCourseFuture();
//    Optional<CourseOffering> getAllCourseOfferingsByFaculty(int course_id, int faculty_id);
}
