package miu.edu.attendance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;

public interface CourseOfferingService {
    CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate);
    List<CourseOffering> getAllCourseOfferings();
    Optional<CourseOffering> getAllCourseOfferings(int id);
    CourseOffering getAllCourseCurrent();
    List<CourseOffering> getAllCoursePast();
    List<CourseOffering> getAllCourseFuture();

}
