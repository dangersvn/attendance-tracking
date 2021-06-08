package miu.edu.attendance.controller;

import miu.edu.attendance.config.JPAUserDetails;
import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.security.SecurityUtils;
import miu.edu.attendance.service.BarcodeRecordService;
import miu.edu.attendance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barcoderecord")
public class BarcodeRecordController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BarcodeRecordService barcodeRecordService;


    @Autowired
    PersonService personService;

    @GetMapping
    public String test() {
        return "OK";
    }

    @PostMapping
    public BarcodeRecord createBarcodeRecord(@RequestBody BarcodeRecordDTO barcodeRecord) {


        return barcodeRecordService.createBarcodeRecord(barcodeRecord);
    }

}
