package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.StudentCourseStateDTO;

public interface StudentCourseStateDAO {
	
	void create(StudentCourseStateDTO studentCourseState);
	
	void update(StudentCourseStateDTO studentCourseState);
	
	void delete(int id);
	
	List<StudentCourseStateDTO> find(StudentCourseStateDTO studentCourseState);
}
