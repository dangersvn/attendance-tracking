package miu.edu.attendance.controller;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> register(@RequestBody RegisterUserDto registerUserDto) {
        return null;
    }

}
