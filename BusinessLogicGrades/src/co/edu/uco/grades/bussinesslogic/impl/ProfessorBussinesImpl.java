package co.edu.uco.grades.bussinesslogic.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.bussinesslogic.bussines.ProfessorBussines;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.ProfessorDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class ProfessorBussinesImpl implements ProfessorBussines{


	private DAOFactory daoFactory;
	
	public ProfessorBussinesImpl(DAOFactory daoFactory) {
		if(UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicExeption("It�s not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(ProfessorDTO dto) {
		daoFactory.getProfessorDAO().create(dto);
	}

	@Override
	public void update(ProfessorDTO dto) {
		daoFactory.getProfessorDAO().update(dto);
	}

	@Override
	public void delete(int id) {
		daoFactory.getProfessorDAO().delete(id);
	}

	@Override
	public List<ProfessorDTO> find(ProfessorDTO dto) {
		return daoFactory.getProfessorDAO().find(dto);
	}
	
}

