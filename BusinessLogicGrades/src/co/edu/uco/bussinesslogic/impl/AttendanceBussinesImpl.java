package co.edu.uco.bussinesslogic.impl;

import java.util.List;

import co.edu.uco.bussinesslogic.bussines.AttendanceBussines;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.AttendanceDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class AttendanceBussinesImpl implements AttendanceBussines {

	private DAOFactory daoFactory;
	
	public AttendanceBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It´s not possible create a AttendanceBussionesImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(AttendanceDTO dto) {
		daoFactory.getAttendanceDAO().create(dto);
		
	}

	@Override
	public void update(AttendanceDTO dto) {
		daoFactory.getAttendanceDAO().update(dto);
	}

	@Override
	public List<AttendanceDTO> find(AttendanceDTO dto) {
		return daoFactory.getAttendanceDAO().find(dto);
	}
	
}
