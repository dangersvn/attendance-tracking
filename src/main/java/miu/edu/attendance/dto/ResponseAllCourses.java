package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseAllCourses extends Response{

    private List<CourseDto> courses;

}
