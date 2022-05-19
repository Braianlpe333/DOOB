package co.edu.uco.grdes.data.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.grades.data.dao.CourseDAO;
import co.edu.grades.data.dao.IdTypeDAO;
import co.edu.grades.data.dao.ProfessorDAO;
import co.edu.grades.data.dao.StudentDAO;
import co.edu.grades.data.dao.SubjectDAO;
import co.edu.uco.corsscuting.util.sql.UtilConnection;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.crosscuting.exeption.enumeration.ExceptionLocation;
import co.edu.uco.grades.crosscuting.exeption.enumeration.ExeptionType;
import co.edu.uco.grades.data.dao.azuresql.CourseAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.IdTypeAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.ProfessorAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.StudentAzureSqlDAO;
import co.edu.uco.grades.data.dao.azuresql.SubjectAzureSqlDAO;
import co.edu.uco.grades.dto.Student_DTO;
import co.edu.uco.grdes.data.factory.DAOFactory;

public class AzuresqlDAOFactory extends DAOFactory {
	
	private Connection connection;
	
	private AzuresqlDAOFactory() {
		openConnection();
	}
	
	@Override
	public void initTransaction() {
		if(UtilConnection.isClosed(getConnection())){
			throw GradesException.buildTechnicalExeption("it's not possible to init the transaction because the connection is close");
		}
		
		try {
			if(!getConnection().getAutoCommit()){
				throw GradesException.buildTechnicalExeption("it's not possible to init the transaction because it was already initiated");
			}
			getConnection().setAutoCommit(false);
		}
		catch(SQLException exception) {
			throw GradesException.buildTechnicalExeption("There was a problem tryin to init with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
		}
		 catch(Exception exception) {
				throw GradesException.buildTechnicalExeption("An unexpected problem has ocurred tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
			}
	}

	@Override
	public void commitTransaction() {
		if(UtilConnection.isClosed(connection)){
			throw GradesException.buildTechnicalExeption("it's not possible to commit the transaction because the connection is close");
		} 
		
		
		try {
			if(getConnection().getAutoCommit()){
				throw GradesException.buildTechnicalExeption("it's not possible to commit the transaction because the database is managin the ");
			}
			getConnection().commit();
		}catch(GradesException exception) {
			throw exception;
		}
		catch(SQLException exception) {
			throw GradesException.buildTechnicalExeption("There was a problem tryin to commit with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
			exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
		}
		 catch(Exception exception) {
				throw GradesException.buildTechnicalExeption("An unexpected problem has ocurred tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
			}
	}
	
	

	@Override
	public void rollbackTransaction() {
		if(UtilConnection.isClosed(connection)){
			throw GradesException.buildTechnicalExeption("it's not possible to rollback the transaction because the connection is close");
		}
		
		try {
			if(getConnection().getAutoCommit()){
				throw GradesException.buildTechnicalExeption("it's not possible to rollback the transaction because the database is managin the ");
			}
			getConnection().rollback();
		}
		catch(GradesException exception) {
			throw exception;
		}
		catch(SQLException exception) {
			throw GradesException.buildTechnicalExeption("There was a problem tryin to rollback with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
		}
		 catch(Exception exception) {
				throw GradesException.buildTechnicalExeption("An unexpected problem has ocurred tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
						exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
			}
	}

	@Override
	protected void openConnection() {
		String stringConnection = "jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser;password=4c4d3m1cDmlUs3r;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(stringConnection);
		}
		catch(SQLException exception) {
			throw GradesException.buildTechnicalExeption("There was a problem tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
		}
		 catch(Exception exception) {
				throw GradesException.buildTechnicalExeption("An unexpected problem has ocurred tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
						exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
			}
	}

	
	
	@Override
	public void closeConection() {
		if(UtilConnection.isClosed(connection)){
			throw GradesException.buildTechnicalExeption("it's not possible close a connection because it's alredy is closed or is null");
	}
		try {
			getConnection().close();
		}
		catch(SQLException exception) {
			throw GradesException.buildTechnicalExeption("There was a problem tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
		}
		 catch(Exception exception) {
				throw GradesException.buildTechnicalExeption("An unexpected problem has ocurred tryin to get the connection with sql server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
						exception, ExeptionType.TECHNICAL, ExceptionLocation.DATA );
			}
	}

	@Override
	protected Connection getConnection() {
		return connection;
		
	}
	
	public static void close(String[] args) {
		AzuresqlDAOFactory az = new AzuresqlDAOFactory();
		System.out.println("Conexion Abierta!");
		az.closeConection();
		System.out.println("Conexion Cerrada!");
	}

	@Override
	public StudentDAO getStudentDAO() {
		return StudentAzureSqlDAO.build(getConnection());
	}

	@Override
	public IdTypeDAO getIdTypeDAO() {
		return IdTypeAzureSqlDAO.build(getConnection());
	}

	@Override
	public ProfessorDAO getProfessorDAO() {
		return ProfessorAzureSqlDAO.build(getConnection());
	}

	@Override
	public SubjectDAO getSubjectDAO() {
		return SubjectAzureSqlDAO.build(getConnection());
	}

	@Override
	public CourseDAO getCourseDAO() {
		return CourseAzureSqlDAO.build(getConnection());
	}
	
	
}
