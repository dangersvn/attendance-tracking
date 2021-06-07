package miu.edu.attendance.service.utils;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Personnel;
import miu.edu.attendance.domain.Student;

import java.util.HashMap;
import java.util.Map;

public class PersonRoleUtils {
    private static Map<String, Class> PERSON_ROLES = new HashMap<String, Class>() {{
        put("admin", Admin.class);
        put("faculty", Faculty.class);
        put("personnel", Personnel.class);
        put("student", Student.class);
    }};

    public static Class convertRoleNameToPersonRole(String role) {
        if (!PERSON_ROLES.containsKey(role.toLowerCase())) {
            throw new IllegalArgumentException(String.format("Role name: %s is invalid", role));
        }
        return PERSON_ROLES.get(role.toLowerCase());
    }
}