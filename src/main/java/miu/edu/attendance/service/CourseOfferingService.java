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
    Optional<CourseOffering> getAllCourseOfferings(int id);
}
