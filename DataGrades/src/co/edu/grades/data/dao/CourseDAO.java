package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.CourseDTO;

public interface CourseDAO {
	
	void create(CourseDTO idType);
	
	void update(CourseDTO idType);
	
	List<CourseDTO> find(CourseDTO idType);
}
