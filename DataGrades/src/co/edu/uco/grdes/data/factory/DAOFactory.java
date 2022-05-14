package co.edu.uco.grdes.data.factory;

import java.sql.Connection;

import co.edu.grades.data.dao.StudentDAO;
import co.edu.uco.grades.dto.Student_DTO;

public abstract class DAOFactory {
	
	public static DAOFactory getDaoFactory(){
		return null;
	}
	
	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
	
	protected abstract void openConnection();
	
	protected abstract Connection getConnection();
	
	public abstract void initTransaction();

	public abstract StudentDAO getStudentDAO();

	public void closeConection() {
	}
}
