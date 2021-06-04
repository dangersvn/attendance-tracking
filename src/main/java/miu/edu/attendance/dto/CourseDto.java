package miu.edu.attendance.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    int id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
}