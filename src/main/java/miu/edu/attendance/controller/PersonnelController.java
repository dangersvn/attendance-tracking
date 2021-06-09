package miu.edu.attendance.controller;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personnel")
public class PersonnelController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Autowired
    StudentService studentService;
    @Autowired
    BarcodeRecordService barcodeRecordService;
    @Autowired
    ClassSessionService classSessionService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Person> findStudent(@RequestBody String keyword) throws JSONException {
        JSONObject key = new JSONObject(keyword);
        String k = key.getString("keyword");
        return studentService.getStudentByKeyWord(k);
    }

    @PostMapping("/attendance")
    public List<BarcodeRecord> getAttendance(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer studentId = requestStr.getInt("studentId");
        Integer courseOfferId = requestStr.getInt("courseOfferId");
       Student student =  studentService.getStudentById(studentId);
        return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(studentId, courseOfferId);
    }
    @PostMapping("/classsession")
    public List<ClassSession> getClassSession(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer studentId = requestStr.getInt("studentId");
        Integer courseOfferId = requestStr.getInt("courseOfferId");
        Student student =  studentService.getStudentById(studentId);
       // return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(studentId, courseOfferId);
        System.out.println(classSessionService.attendanceStatus(studentId, courseOfferId));
        return  classSessionService.getClassSessionByCourseOfferingId(courseOfferId);
    }
}
