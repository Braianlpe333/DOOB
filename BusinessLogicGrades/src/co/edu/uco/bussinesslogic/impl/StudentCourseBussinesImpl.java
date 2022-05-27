package co.edu.uco.bussinesslogic.impl;

import java.util.List;

import co.edu.grades.data.dao.StudentCourseDAO;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.StudentCourseDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class StudentCourseBussinesImpl implements StudentCourseDAO{

	private DAOFactory daoFactory;
	
	public StudentCourseBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It´s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(StudentCourseDTO dto) {
		daoFactory.getStudentCourseDAO().create(dto);
	}

	@Override
	public void update(StudentCourseDTO dto) {
		daoFactory.getStudentCourseDAO().update(dto);
	}

	@Override
	public List<StudentCourseDTO> find(StudentCourseDTO dto) {
		return daoFactory.getStudentCourseDAO().find(dto);
	}
}
