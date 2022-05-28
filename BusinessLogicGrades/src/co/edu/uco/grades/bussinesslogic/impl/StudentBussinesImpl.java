package co.edu.uco.grades.bussinesslogic.impl;

import java.util.List;

import co.edu.grades.data.dao.StudentDAO;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.Student_DTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class StudentBussinesImpl implements StudentDAO{

	private DAOFactory daoFactory;
	
	public StudentBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It�s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(Student_DTO dto) {
		daoFactory.getStudentDAO().create(dto);
	}

	@Override
	public void update(Student_DTO dto) {
		daoFactory.getStudentDAO().update(dto);
	}

	@Override
	public void delete(int id) {
		daoFactory.getIdTypeDAO().delete(id);
	}

	@Override
	public List<Student_DTO> find(Student_DTO dto) {
		return daoFactory.getStudentDAO().find(dto);
	}
}
