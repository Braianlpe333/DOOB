package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.grades.data.dao.StudentCourseDAO;

import co.edu.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.StudentCourseDTO;


public class StudentCourseAzureSqlDAO extends ConnectionSQL implements StudentCourseDAO{
	protected StudentCourseAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StudentCourseDAO build(Connection connection) {
		return new StudentCourseAzureSqlDAO(connection);
	}
		
	@Override
	public void create(StudentCourseDTO studentCourse) {
		String sql = "INSERT INTO StudentCourse(student, course, state) VALUES(?, ?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setObject(1, studentCourse.getStudent());
			preparedStatement.setObject(1, studentCourse.getCourse());
			preparedStatement.setObject(1, studentCourse.getState());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new StudentCourse on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new StudentCourse on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(StudentCourseDTO studentCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(StudentCourseDTO studentCourse) {
		// TODO Auto-generated method stub
		
	}
}
