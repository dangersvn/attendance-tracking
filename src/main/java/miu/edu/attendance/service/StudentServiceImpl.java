package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Person> getStudentByKeyWord(String keyword) {
        return studentRepository.findByKeyword(keyword);
    }
    public Student getStudentById(Integer studentId){
        return studentRepository.getById(studentId);
    }
}
