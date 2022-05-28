package co.edu.uco.grades.bussinesslogic.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.bussinesslogic.bussines.SessionBussines;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SessionDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class SessionBussinesImpl implements SessionBussines{

	private DAOFactory daoFactory;
	
	public SessionBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It�s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(SessionDTO dto) {
		daoFactory.getSessionDAO().create(dto);
	}

	@Override
	public void update(SessionDTO dto) {
		daoFactory.getSessionDAO().update(dto);
	}

	@Override
	public List<SessionDTO> find(SessionDTO dto) {
		return daoFactory.getSessionDAO().find(dto);
	}
	
}

