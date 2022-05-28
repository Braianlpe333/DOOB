package co.edu.uco.grades.businesslogic.facade.Impl;

import java.util.List;

import co.edu.uco.grades.businesslogic.facade.IdTypeFacade;
import co.edu.uco.grades.bussinesslogic.bussines.IdTypeBusiness;
import co.edu.uco.grades.bussinesslogic.impl.IdTypeBusinessImpl;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.IdTypeDTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class IdTypeFacadeImpl implements IdTypeFacade{

	
	
	@Override
	public void create(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			daoFactory.initTransaction();
			//Ejecutar el Negocio
			IdTypeBusiness idTypeBussines = new IdTypeBusinessImpl(daoFactory);
			idTypeBussines.create(dto);
			
			daoFactory.commitTransaction();
		}catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		}catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "An unexpected has ocurred problem trying to create the new IdType on create method in IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBusinessLogicExeption(message);
		}finally {
			daoFactory.closeConection();
		}
	}

	@Override
	public void update(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();
			//Ejecutar el Negocio
			IdTypeBusiness idTypeBussines = new IdTypeBusinessImpl(daoFactory);
			idTypeBussines.update(dto);
			
			daoFactory.commitTransaction();
		}catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		}catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "An unexpected has ocurred problem trying to update the IdType on update method in IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBusinessLogicExeption(message);
		}finally {
			daoFactory.closeConection();
		}
	}

	@Override
	public void delete(int id) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();
			//Ejecutar el Negocio
			IdTypeBusiness idTypeBussines = new IdTypeBusinessImpl(daoFactory);
			idTypeBussines.delete(id);
			
			daoFactory.commitTransaction();
		}catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		}catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "An unexpected has ocurred problem trying to delete the IdType on delete method in IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBusinessLogicExeption(message);
		}finally {
			daoFactory.closeConection();
		}
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			//Ejecutar el Negocio
			IdTypeBusiness idTypeBussines = new IdTypeBusinessImpl(daoFactory);
			return idTypeBussines.find(dto);
			
		}catch (GradesException exception) {
			throw exception;
		}catch (Exception exception){
			var message = "An unexpected has ocurred problem trying to find the IdType on find find in IdTypeFacadeImpl";
			throw GradesException.buildTechnicalBusinessLogicExeption(message);
		}finally {
			daoFactory.closeConection();
		}
	}

}
