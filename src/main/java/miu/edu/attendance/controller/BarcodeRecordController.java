package miu.edu.attendance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barcoderecord")
public class BarcodeRecordController {
    @GetMapping
    public String test() {
        return "OK";
    }
}
