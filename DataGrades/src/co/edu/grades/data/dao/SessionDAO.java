package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.SessionDTO;

public interface SessionDAO {
	
	void create(SessionDTO session);
	
	void update(SessionDTO session);
	
	void delete(int id);
	
	List<SessionDTO> find(SessionDTO session);
}
