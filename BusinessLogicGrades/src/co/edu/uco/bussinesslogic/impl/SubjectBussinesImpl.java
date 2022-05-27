package co.edu.uco.bussinesslogic.impl;

import java.util.List;

import co.edu.grades.data.dao.SubjectDAO;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SubjectDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class SubjectBussinesImpl implements SubjectDAO{

	private DAOFactory daoFactory;
	
	public SubjectBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It´s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(SubjectDTO dto) {
		daoFactory.getSubjectDAO().create(dto);
	}

	@Override
	public void update(SubjectDTO dto) {
		daoFactory.getSubjectDAO().update(dto);
	}

	@Override
	public void delete(int id) {
		daoFactory.getSubjectDAO().delete(id);
	}

	@Override
	public List<SubjectDTO> find(SubjectDTO dto) {
		return daoFactory.getSubjectDAO().find(dto);
	}
}
