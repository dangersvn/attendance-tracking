package miu.edu.attendance.service;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;

import java.time.LocalDate;

public interface CourseOfferingSerivce {
    CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate);
}
