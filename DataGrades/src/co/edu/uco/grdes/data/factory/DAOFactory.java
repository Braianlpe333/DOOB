package co.edu.uco.grdes.data.factory;

import java.sql.Connection;

import co.edu.grades.data.dao.CourseDAO;
import co.edu.grades.data.dao.IdTypeDAO;
import co.edu.grades.data.dao.ProfessorDAO;
import co.edu.grades.data.dao.StudentDAO;
import co.edu.grades.data.dao.SubjectDAO;

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
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract ProfessorDAO getProfessorDAO();
	
	public abstract SubjectDAO getSubjectDAO();
	
	public abstract CourseDAO getCourseDAO();

	public void closeConection() {
		
	}
}
