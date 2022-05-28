package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.SessionDTO;

public interface SessionBussines {
	
	void create(SessionDTO dto);
	
	void update(SessionDTO dto);
	
	List<SessionDTO> find(SessionDTO dto);
}
