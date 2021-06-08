package miu.edu.attendance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class PersonRoleDto {
    List<RoleDto> roles = new ArrayList<>();
}