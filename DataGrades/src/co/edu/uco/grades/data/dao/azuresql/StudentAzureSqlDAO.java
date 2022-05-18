package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.Student_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import co.edu.grades.data.dao.StudentDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class StudentAzureSqlDAO extends ConnectionSQL implements StudentDAO{

	protected StudentAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StudentDAO build(Connection connection) {
		return new StudentAzureSqlDAO(connection);
	}
		
	@Override
	public void create(Student_DTO student) {
		String sql = "INSERT INTO Students(idNumber, idType, name, email) VALUES(?, ?, ?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, student.getIdNumber());
			preparedStatement.setObject(1, student.getIdType());
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(1, student.getEmail());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Student on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Student on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(Student_DTO student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(Student_DTO student) {
		// TODO Auto-generated method stub
		
	}

	
}

