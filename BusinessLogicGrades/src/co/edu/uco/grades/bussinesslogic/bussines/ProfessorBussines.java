package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.ProfessorDTO;

public interface ProfessorBussines {
	
	void create(ProfessorDTO dto);
	
	void update(ProfessorDTO dto);
	
	void delete(int id);
	
	List<ProfessorDTO> find(ProfessorDTO dto);
}
