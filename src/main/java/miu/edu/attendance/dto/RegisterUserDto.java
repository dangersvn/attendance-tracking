package miu.edu.attendance.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String username;
    private String password;

    private String firstName;
    private String lastName;


    private String personRole;
}