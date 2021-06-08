package miu.edu.attendance.controller;

import miu.edu.attendance.domain.BarcodeRecord;
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Person> findStudent(@RequestBody String keyword) throws JSONException {
        JSONObject key = new JSONObject(keyword);
        String k = key.getString("keyword");
        return studentService.getStudentByKeyWord(k);
    }

    @PostMapping("/attendance")
    public List<BarcodeRecord> getAttendance(@RequestBody String request) throws JSONException {
        JSONObject requestStr = new JSONObject(request);
        Integer stId = requestStr.getInt("studentId");
        Integer sessionId = requestStr.getInt("sessionId");
       Student student =  studentService.getStudentById(stId);
        return barcodeRecordService.getBarcodeRecordByStudentIdAndSessionId(stId, sessionId);
    }
}