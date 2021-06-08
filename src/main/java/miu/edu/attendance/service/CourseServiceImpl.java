package miu.edu.attendance.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    
    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Override
    public List<Course> getAllCourses(){
        return (List<Course>) courseRepository.findAll();

    }

	@Override
	public Person registerPerson(RegisterUserDto registerUserDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getAllCoursesByFacultyId(int faculty_id) {
		List<CourseOffering> courseOfferings = courseOfferingRepository.getAllCourseOfferingsByFaculty(faculty_id);
		if(courseOfferings == null) return null;
		return courseOfferings.stream().map(c -> c.getCourse()).distinct().collect(Collectors.toList());
	}

}
