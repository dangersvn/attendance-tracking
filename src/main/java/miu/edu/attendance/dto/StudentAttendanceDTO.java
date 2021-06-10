package miu.edu.attendance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @NoArgsConstructor @Setter
public class StudentAttendanceDTO {
    LocalDate date;
    LocalTime beginTime;
    LocalTime endTime;
    String status;
    String abbreviation;

    public StudentAttendanceDTO(LocalDate date, LocalTime beginTime, LocalTime endTime, String status, String abbreviation) {
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.status = status;
        this.abbreviation = abbreviation;
    }


    @Override
    public String toString() {
        return "Date: " + date + ", BeginTime: " + beginTime + " " + abbreviation + ", BndTime: " + endTime  + " " + abbreviation + ", Status: " + status ;
    }
}
