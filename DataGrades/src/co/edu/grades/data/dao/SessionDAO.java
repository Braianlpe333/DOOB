package co.edu.grades.data.dao;

import co.edu.uco.grades.dto.SessionDTO;

public interface SessionDAO {
	
	void create(SessionDTO professor);
	
	void update(SessionDTO professor);
	
	void delete(int id);
	
	void find(SessionDTO professor);
}
