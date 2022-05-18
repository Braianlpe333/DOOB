package co.edu.grades.data.dao;

import co.edu.uco.grades.dto.SubjectDTO;

public interface SubjectDAO {
	
	void create(SubjectDTO subject);
	
	void update(SubjectDTO subject);
	
	void delete(int id);
	
	void find(SubjectDTO subject);
}
