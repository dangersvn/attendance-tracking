package miu.edu.attendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarcodeRecordDTO {
   int classSessionId;
    int locationId;
    LocalDateTime attendanceDate;

}
