package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.StudentCourseStateDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import co.edu.grades.data.dao.StudentCourseStateDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class StudentCourseStateAzureSqlDAO extends ConnectionSQL implements StudentCourseStateDAO{

	protected StudentCourseStateAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StudentCourseStateDAO build(Connection connection) {
		return new StudentCourseStateAzureSqlDAO(connection);
	}
		
	@Override
	public void create(StudentCourseStateDTO studentCourseState) {
		String sql = "INSERT INTO StudentCourseState(name) VALUES(?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, studentCourseState.getName());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new StudentCourseState on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new StudentCourseState on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(StudentCourseStateDTO studentCourseState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(StudentCourseStateDTO studentCourseState) {
		// TODO Auto-generated method stub
		
	}

	
}
