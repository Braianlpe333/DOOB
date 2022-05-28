package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.IdTypeDTO;

public interface IdTypeBusiness {

	void create(IdTypeDTO dto);
	
	void update(IdTypeDTO dto);
	
	void delete(int id);
	
	List<IdTypeDTO> find(IdTypeDTO dto);
	
}
