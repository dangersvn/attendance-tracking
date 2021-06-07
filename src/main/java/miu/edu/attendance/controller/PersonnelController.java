package miu.edu.attendance.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.BarcodeRecordService;
import miu.edu.attendance.service.CourseService;
import miu.edu.attendance.service.StudentService;

@RestController
@RequestMapping("/personnel")
public class PersonnelController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CourseService courseService;
	
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
	public List<BarcodeRecord> getAttendance(@RequestBody String studentId) throws JSONException {
		JSONObject key = new JSONObject(studentId);
		Integer stId = key.getInt("studentId");
		return barcodeRecordService.getAllByStudentId(stId);
	}
}
