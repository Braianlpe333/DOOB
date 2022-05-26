package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.CourseDTO;

public interface CourseDAO {
	
	void create(CourseDTO idType);
	
	void update(CourseDTO idType);
	
	void delete(int id);
	
	List<CourseDTO> find(CourseDTO idType);
}
