package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.AvailableTimeSlotDto;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.service.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    TimeSlotService timeSlotService;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;
    
    /**
     * Create course offering with defaults class sessions: AM Session and PM Session, Monday to Friday.
     * @param course
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public CourseOffering createCourseOffering(Course course, LocalDate startDate, LocalDate endDate) {

        CourseOffering courseOffering = new CourseOffering(course, startDate, endDate);
        List<LocalDate> businessDays = DateUtils.getBusinessDaysBetween(startDate, endDate, Optional.empty());

        // create class sessions for morning
        AvailableTimeSlotDto availableTimeSlot = timeSlotService.getAllTimeSlots();
        
        businessDays.stream().forEach(date -> {
            ClassSession morningClassSession = new ClassSession(availableTimeSlot.getMorningSession(), date);
            ClassSession afternoonClassSession = new ClassSession(availableTimeSlot.getAfternoonSession(), date);
            courseOffering.addClassSession(morningClassSession);
            courseOffering.addClassSession(afternoonClassSession);
        });

        return courseOfferingRepository.save(courseOffering);
    }

	@Override
	public List<CourseOffering> getAllCourseOfferings() {
		return (List<CourseOffering>) courseOfferingRepository.findAll();
	}
}
