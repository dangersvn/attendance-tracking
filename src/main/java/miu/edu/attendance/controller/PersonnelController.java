package miu.edu.attendance.controller;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personnel")
public class PersonnelController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    BarcodeRecordService barcodeRecordService;
    @Autowired
    ClassSessionService classSessionService;
    @Autowired
    CourseOfferingService courseOfferingService;

    @RequestMapping(value = "/student/search", method = RequestMethod.POST)
    public List<Person> findStudent(@RequestBody String keyword) throws JSONException {
        JSONObject key = new JSONObject(keyword);
        String k = key.getString("keyword");
        return studentService.getStudentByKeyWord(k);
    }

    @RequestMapping(value = "/student/couseroffering", method = RequestMethod.POST)
    public List<CourseOffering> getCourseOffering(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer studentId = requestStr.getInt("studentId");
        return courseOfferingService.getAllCourseOffering(studentId);
    }


    @PostMapping("/student/attendancerecord")
    public List<BarcodeRecord> getAttendance(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer studentId = requestStr.getInt("studentId");
        Integer courseOfferId = requestStr.getInt("courseOfferId");
       Student student =  studentService.getStudentById(studentId);
        return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(studentId, courseOfferId);
    }

    @PostMapping("/student/classsession")
    public List<String> getClassSession(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer studentId = requestStr.getInt("studentId");
        Integer courseOfferId = requestStr.getInt("courseOfferId");
        Student student =  studentService.getStudentById(studentId);
        return  classSessionService.attendanceStatus(studentId, courseOfferId);
    }

    @PostMapping("/student/attendance/present")
    public BarcodeRecord createBarcodeRecord(@RequestBody ClassSessionDTO classSessionDTO)  {
       return barcodeRecordService.createBarcodeRecordToStudent(classSessionDTO);
    }
}
