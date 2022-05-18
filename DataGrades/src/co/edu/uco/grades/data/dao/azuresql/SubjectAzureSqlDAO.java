package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SubjectDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import co.edu.grades.data.dao.SubjectDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class SubjectAzureSqlDAO extends ConnectionSQL implements SubjectDAO{

	protected SubjectAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SubjectDAO build(Connection connection) {
		return new SubjectAzureSqlDAO(connection);
	}
		
	@Override
	public void create(SubjectDTO subject) {
		String sql = "INSERT INTO Subject(name) VALUES(?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, subject.getName());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Subject on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Subject on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(SubjectDTO subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(SubjectDTO subject) {
		// TODO Auto-generated method stub
		
	}

	
}
