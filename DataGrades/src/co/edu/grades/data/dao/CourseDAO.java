package co.edu.grades.data.dao;

import co.edu.uco.grades.dto.CourseDTO;

public interface CourseDAO {
	
	void create(CourseDTO idType);
	
	void update(CourseDTO idType);
	
	void delete(int id);
	
	void find(CourseDTO idType);
}
