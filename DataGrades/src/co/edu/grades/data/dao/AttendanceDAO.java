package co.edu.grades.data.dao;

import java.util.List;

import co.edu.uco.grades.dto.AttendanceDTO;

public interface AttendanceDAO {
	
	void create(AttendanceDTO idType);
	
	void update(AttendanceDTO idType);
	
	void delete(int id);
	
	List<AttendanceDTO> find(AttendanceDTO idType);
}
