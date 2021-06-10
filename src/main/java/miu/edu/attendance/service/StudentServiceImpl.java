package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.StudentInfoDTO;
import miu.edu.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<StudentInfoDTO> getStudentByKeyWord(String keyword) {
        List<StudentInfoDTO> studentInfoDTOList = new LinkedList<>();
        List<Person>  studentList = studentRepository.findByKeyword(keyword);
        for (Person person: studentList){
            studentInfoDTOList.add(new StudentInfoDTO(person.getId(), person.getUsername(),
                    person.getFirstName(), person.getLastName(), person.getBarcodeId()));
        }
        return studentInfoDTOList;
    }
    public Student getStudentById(Integer studentId){
        return studentRepository.getById(studentId);
    }
	@Override
	public List<Student> getStudentsByCourseOffering(int courseOffering_id) {
		return studentRepository.getStudentsByCourseOffering(courseOffering_id);
	}
}
