package co.edu.uco.grades.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.edu.grades.data.dao.AttendanceDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.AttendanceDTO;

public class AttendanceAzureSqlDAO extends ConnectionSQL implements AttendanceDAO{
	
	protected AttendanceAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static AttendanceDAO build(Connection connection) {
		return new AttendanceAzureSqlDAO(connection);
	}
		
	@Override
	public void create(AttendanceDTO attendance) {
		String sql = "INSERT INTO Course(studentCourse, session, attended) VALUES(?, ?, ?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setObject(1, attendance.getStudentCourse());
			preparedStatement.setObject(1, attendance.getSession());
			preparedStatement.setBoolean(1, attendance.isAttended());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Attendance on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Attendance on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(AttendanceDTO attendance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(AttendanceDTO attendance) {
		// TODO Auto-generated method stub
		
	}
}
