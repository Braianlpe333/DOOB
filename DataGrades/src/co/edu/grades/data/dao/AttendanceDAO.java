package co.edu.grades.data.dao;

import co.edu.uco.grades.dto.AttendanceDTO;

public interface AttendanceDAO {
	
	void create(AttendanceDTO idType);
	
	void update(AttendanceDTO idType);
	
	void delete(int id);
	
	void find(AttendanceDTO idType);
}
