package co.edu.grades.data.dao;
import java.util.List;

import co.edu.uco.grades.dto.Student_DTO;

public interface StudentDAO {
	
	void create(Student_DTO student);
	
	void update(Student_DTO student);
	
	void delete(int id);
	
	List<Student_DTO> find(Student_DTO student);
}
