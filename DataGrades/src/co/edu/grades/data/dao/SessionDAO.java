package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.SessionDTO;

public interface SessionDAO {
	
	void create(SessionDTO session);
	
	void update(SessionDTO session);
	
	List<SessionDTO> find(SessionDTO session);
}
