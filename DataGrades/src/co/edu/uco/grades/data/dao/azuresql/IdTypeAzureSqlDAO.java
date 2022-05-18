package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.IdTypeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import co.edu.grades.data.dao.IdTypeDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class IdTypeAzureSqlDAO extends ConnectionSQL implements IdTypeDAO{

	protected IdTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static IdTypeDAO build(Connection connection) {
		return new IdTypeAzureSqlDAO(connection);
	}
		
	@Override
	public void create(IdTypeDTO idType) {
		String sql = "INSERT INTO IdType(name) VALUES(?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, idType.getName());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new IdType on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new IdType on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(IdTypeDTO idType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(IdTypeDTO idType) {
		// TODO Auto-generated method stub
		
	}

	
}