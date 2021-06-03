package miu.edu.attendance.controller;

import miu.edu.attendance.config.JPAUserDetails;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.JwtTokenDto;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<JwtTokenDto> register(@RequestBody RegisterUserDto registerUserDto) {
        Person savedUser = personService.registerPerson(registerUserDto);
        JPAUserDetails userDetails = new JPAUserDetails(savedUser);

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenDto(token));
    }

}
