package co.edu.grades.data.dao;

import co.edu.uco.grades.dto.ProfessorDTO;

public interface ProfessorDAO {
	
	void create(ProfessorDTO professor);
	
	void update(ProfessorDTO professor);
	
	void delete(int id);
	
	void find(ProfessorDTO professor);
}
