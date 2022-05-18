package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.ProfessorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.grades.data.dao.ProfessorDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class ProfessorAzureSqlDAO extends ConnectionSQL implements ProfessorDAO{

	protected ProfessorAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static ProfessorDAO build(Connection connection) {
		return new ProfessorAzureSqlDAO(connection);
	}
		
	@Override
	public void create(ProfessorDTO professor) {
		String sql = "INSERT INTO Professor(idNumber, idType, name, email) VALUES(?, ?, ?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, professor.getIdNumber());
			preparedStatement.setObject(1, professor.getIdType());
			preparedStatement.setString(1, professor.getName());
			preparedStatement.setString(1, professor.getEmail());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Professor on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Professor on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(ProfessorDTO professor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(ProfessorDTO professor) {
		// TODO Auto-generated method stub
		
	}

	
}

