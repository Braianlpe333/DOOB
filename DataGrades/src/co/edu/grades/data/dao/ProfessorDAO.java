package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.ProfessorDTO;

public interface ProfessorDAO {
	
	void create(ProfessorDTO professor);
	
	void update(ProfessorDTO professor);
	
	void delete(int id);
	
	List<ProfessorDTO> find(ProfessorDTO professor);
}
