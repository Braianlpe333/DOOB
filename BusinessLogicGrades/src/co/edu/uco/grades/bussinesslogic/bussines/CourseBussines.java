package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.CourseDTO;

public interface CourseBussines {
	
	void create(CourseDTO dto);
	
	void update(CourseDTO dto);
	
	List<CourseDTO> find(CourseDTO dto);
}
