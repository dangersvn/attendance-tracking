package miu.edu.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseAttendanceDto {
	String firstname;
	String lastname;
	int toalPresent;
	int totalSessions;
}
