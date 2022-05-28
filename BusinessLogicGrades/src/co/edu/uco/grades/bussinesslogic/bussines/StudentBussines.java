package co.edu.uco.grades.bussinesslogic.bussines;

import java.util.List;

import co.edu.uco.grades.dto.Student_DTO;

public interface StudentBussines {
	
	void create(Student_DTO dto);
	
	void update(Student_DTO dto);
	
	void delete(int id);
	
	List<Student_DTO> find(Student_DTO dto);
}
