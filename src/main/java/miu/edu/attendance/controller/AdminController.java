package miu.edu.attendance.controller;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.PersonRoleDto;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.PersonRoleService;
import miu.edu.attendance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRoleService personRoleService;

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> register(@RequestBody RegisterUserDto registerUserDto) {
        return null;
    }

    @PatchMapping("/persons/{person_id}/roles")
    public Person assignRole(@PathVariable(value = "person_id") int personId, @RequestBody PersonRoleDto personRoleDto) {
        return personRoleService.updateRole(personId, personRoleDto);
    }
}
