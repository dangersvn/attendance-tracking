package miu.edu.attendance.service;
import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.CourseDto;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.repository.CourseRepository;
import miu.edu.attendance.repository.PersonRepository;
import miu.edu.attendance.repository.PersonRoleRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    // @Override
    public List<Course> getAllCourses(){
        return (List<Course>) courseRepository.findAll();

    }

	@Override
	public Person registerPerson(RegisterUserDto registerUserDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
