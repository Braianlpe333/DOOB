package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.StudentCourseStateDTO;

public interface StudentCourseStateDAO {
	
	void create(StudentCourseStateDTO studentCourseState);
	
	void update(StudentCourseStateDTO studentCourseState);
	
	List<StudentCourseStateDTO> find(StudentCourseStateDTO studentCourseState);
}
