package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class StudentInfoDTO {
    Integer studentId;
    String username;
    String firstName;
    String lastName;
    String barcodeId;

    public StudentInfoDTO(Integer studentId, String username, String firstName, String lastName, String barcodeId) {
        this.studentId = studentId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.barcodeId = barcodeId;
    }
}
