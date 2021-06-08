package miu.edu.attendance.service;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.PersonRoleDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonRoleService {
    Person updateRole(int personId, PersonRoleDto role);
}