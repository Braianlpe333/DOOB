package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.AttendanceDTO;

public interface AttendanceBussines {

	void create(AttendanceDTO dto);
	
	void update(AttendanceDTO dto);
	
	List<AttendanceDTO> find(AttendanceDTO dto);
}
