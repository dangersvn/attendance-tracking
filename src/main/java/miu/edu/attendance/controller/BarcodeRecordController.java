package miu.edu.attendance.controller;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.BarcodeRecordServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barcoderecord")
public class BarcodeRecordController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BarcodeRecordServiceImp barcodeRecordServiceImp;

    @GetMapping
    public String test() {
        return "OK";
    }

    @PostMapping
    public BarcodeRecord createBarcodeRecord(@RequestBody BarcodeRecordDTO barcodeRecord){
      return barcodeRecordServiceImp.createBarcodeRecord(barcodeRecord);
    }

}
