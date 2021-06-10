package miu.edu.attendance.controller;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.dto.StudentAttendanceDTO;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping("/students/search")
    public List<Person> searchByStudentKeyWord(@RequestParam("keyword")  String keyword){
        return studentService.getStudentByKeyWord(keyword);
    }

    @GetMapping("/students/{student_id}/couserofferings")
    public List<CourseOffering> getCourseOffering(@PathVariable("student_id") Integer studentId) {
        return courseOfferingService.getAllCourseOffering(studentId);
    }

    @GetMapping("/courseofferings/{courseOffering_id}/students/{student_id}/barcoderecords")
    public List<BarcodeRecord> getAllBarcodeRecords(@PathVariable("courseOffering_id") Integer courseOfferId,
                                                    @PathVariable("student_id") Integer studentId){
        return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(studentId, courseOfferId);
    }

    @GetMapping("/courseofferings/{courseOffering_id}/students/{student_id}/attendances")
    public List<StudentAttendanceDTO> getAllClassSessionsAndAttendances(@PathVariable("courseOffering_id") Integer courseOfferId,
                                                                        @PathVariable("student_id") Integer studentId ) {
        return  classSessionService.attendanceStatus(studentId, courseOfferId);
    }

    @PostMapping("/student/barcoderecords")
    public BarcodeRecord createBarcodeRecord(@RequestBody ClassSessionDTO classSessionDTO)  {
       return barcodeRecordService.createBarcodeRecordToStudent(classSessionDTO);
    }

    @DeleteMapping("/student/{barcodeRecord_id}/barcoderecords")
    public void deleteBarcodeRecord(@PathVariable("barcodeRecord_id") Integer barcodeRecordId){
        barcodeRecordService.deleteBarcodeRecord(barcodeRecordId);
    }

}
