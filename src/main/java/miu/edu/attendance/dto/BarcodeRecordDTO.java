package miu.edu.attendance.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarcodeRecordDTO {

    int locationId;
    String barcode;
}
