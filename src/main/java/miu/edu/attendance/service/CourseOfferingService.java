package miu.edu.attendance.service;

import java.time.LocalDate;
import java.util.List;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;

public interface CourseOfferingService {
    CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate);
    List<CourseOffering> getAllCourseOfferings();
}
