package miu.edu.attendance.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAllCourses extends Response{

    private List<CourseDto> courses;

}
