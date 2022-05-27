package co.edu.uco.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.StudentCourseStateDTO;

public interface StudentCourseStateBussines {
	
	void create(StudentCourseStateDTO dto);
	
	void update(StudentCourseStateDTO dto);
	
	List<StudentCourseStateDTO> find(StudentCourseStateDTO dto);
}
