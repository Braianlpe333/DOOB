package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.StudentCourseDTO;

public interface StudentCourseBussines {
	
	void create(StudentCourseDTO dto);
	
	void update(StudentCourseDTO dto);
	
	List<StudentCourseDTO> find(StudentCourseDTO dto);
}
