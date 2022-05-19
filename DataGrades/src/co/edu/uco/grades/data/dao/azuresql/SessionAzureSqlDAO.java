package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SessionDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.grades.data.dao.SessionDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class SessionAzureSqlDAO extends ConnectionSQL implements SessionDAO{

	protected SessionAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SessionDAO build(Connection connection) {
		return new SessionAzureSqlDAO(connection);
	}
		
	@Override
	public void create(SessionDTO session) {
		String sql = "INSERT INTO Professor(course, date) VALUES(?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setObject(1, session.getCourse());
			preparedStatement.setDate(1, (Date) session.getDate());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Professor on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Professor on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(SessionDTO session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(SessionDTO session) {
		// TODO Auto-generated method stub
		
	}

	
}
