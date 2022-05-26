package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.StudentCourseStateDTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
		String sql = "UPDATE Subject SET name=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, studentCourseState.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new StudentCourseState on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new StudentCourseState on Azure SQL Server", exception);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Course WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to delete the new StudentCourseState on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new StudentCourseState on Azure SQL Server", exception);
		}

	}

	@Override
	public List<StudentCourseStateDTO> find(StudentCourseStateDTO studentCourseState) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<StudentCourseStateDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, name").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(studentCourseState)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(studentCourseState.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(studentCourseState.getId());
				setWhere = false;

			}

			if(!UtilText.isEmpty(studentCourseState.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(studentCourseState.getName()));
			}
			

		}

		sb.append("ORDER BY idNumber ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		}catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to retrieve StudentCourseStates on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve StudentCourseStates on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<StudentCourseStateDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<StudentCourseStateDTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the StudentCourseStates on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the StudentCourseStates on Azure SQL Server", exception);
		}
		return results;
	}

	private List<StudentCourseStateDTO> assembleResults(ResultSet resultSet) {
		List<StudentCourseStateDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the StudentCourseStates on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the StudentCourseStates on Azure SQL Server", exception);
		}

		return results;
	}

	private StudentCourseStateDTO assembleDTO(ResultSet resultSet) {
		StudentCourseStateDTO dto = new StudentCourseStateDTO();
		try {
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the StudentCourseStates on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption( 	
					"An unexpected has ocurred problem trying to assemble the StudentCourseStates on Azure SQL Server", exception);
		}

		return dto;
	}

}
