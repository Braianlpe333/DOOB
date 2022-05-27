package co.edu.uco.bussinesslogic.impl;

import java.util.List;

import co.edu.grades.data.dao.StudentCourseStateDAO;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.StudentCourseStateDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class StudentCourseStateBussinesImpl implements StudentCourseStateDAO{

	private DAOFactory daoFactory;
	
	public StudentCourseStateBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It´s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(StudentCourseStateDTO dto) {
		daoFactory.getStudentCourseStateDAO().create(dto);
	}

	@Override
	public void update(StudentCourseStateDTO dto) {
		daoFactory.getStudentCourseStateDAO().update(dto);
	}


	@Override
	public List<StudentCourseStateDTO> find(StudentCourseStateDTO dto) {
		return daoFactory.getStudentCourseStateDAO().find(dto);
	}
}
