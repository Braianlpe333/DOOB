package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.CourseDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.grades.data.dao.CourseDAO;

import co.edu.grades.data.dao.connection.ConnectionSQL;

public class CourseAzureSqlDAO extends ConnectionSQL implements CourseDAO{

	protected CourseAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static CourseDAO build(Connection connection) {
		return new CourseAzureSqlDAO(connection);
	}
		
	@Override
	public void create(CourseDTO course) {
		String sql = "INSERT INTO Course(subject, professor, initialDate, finalDate) VALUES(?, ?, ?, ?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setObject(1, course.getSubject());
			preparedStatement.setObject(1, course.getProfessor());
			preparedStatement.setDate(1, (Date) course.getInitialDate());
			preparedStatement.setDate(1, (Date) course.getFinalDate());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Course on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Course on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(CourseDTO course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(CourseDTO course) {
		// TODO Auto-generated method stub
		
	}

	
}