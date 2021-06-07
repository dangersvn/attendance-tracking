package miu.edu.attendance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class RoleDto {
    @NotNull
    String role;

    Map<String, String> properties = new HashMap<>();

    @NotNull
    boolean active;
}