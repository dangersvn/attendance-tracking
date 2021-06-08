package miu.edu.attendance.controller;

import miu.edu.attendance.config.JPAUserDetails;
import miu.edu.attendance.config.JPAUserDetailsService;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.JwtTokenDto;
import miu.edu.attendance.dto.LoginRequestDto;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.error.BadCredentialDto;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    JPAUserDetailsService userDetailsService;

    @Autowired
    PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BadCredentialDto("Invalid username and password"));
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenDto(token));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<JwtTokenDto> register(@RequestBody RegisterUserDto registerUserDto) {
        Person savedUser = personService.registerPerson(registerUserDto);
        JPAUserDetails userDetails = new JPAUserDetails(savedUser);

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenDto(token));
    }
}
