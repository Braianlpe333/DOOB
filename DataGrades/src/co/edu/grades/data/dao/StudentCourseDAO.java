package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.StudentCourseDTO;

public interface StudentCourseDAO {
	
	void create(StudentCourseDTO studentCourse);
	
	void update(StudentCourseDTO studentCourse);
	
	List<StudentCourseDTO> find(StudentCourseDTO studentCourse);
}
