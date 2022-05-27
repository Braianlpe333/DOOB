package co.edu.uco.bussinesslogic.impl;

import java.util.List;
import co.edu.uco.bussinesslogic.bussines.CourseBussines;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.CourseDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class CourseBussinesImpl implements CourseBussines{

	private DAOFactory daoFactory;
	
	public CourseBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It´s not possible create a CourseBussinesImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(CourseDTO dto) {
		daoFactory.getCourseDAO().create(dto);
	}

	@Override
	public void update(CourseDTO dto) {
		daoFactory.getCourseDAO().update(dto);
	}

	@Override
	public List<CourseDTO> find(CourseDTO dto) {
		return daoFactory.getCourseDAO().find(dto);
	}

}
