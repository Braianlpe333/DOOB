package co.edu.uco.grades.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.grades.data.dao.StudentCourseDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
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
			preparedStatement.setInt(1, studentCourse.getStudent().getId());
			preparedStatement.setInt(1, studentCourse.getCourse().getId());
			preparedStatement.setInt(1, studentCourse.getState().getId());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new StudentCourse on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new StudentCourse on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(StudentCourseDTO studentCourse) {
		String sql = "UPDATE StudentCourse SET student=?, course=?, state=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, studentCourse.getStudent().getId());
			preparedStatement.setInt(1, studentCourse.getCourse().getId());
			preparedStatement.setInt(1, studentCourse.getState().getId());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new StudentCourse on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new StudentCourse on Azure SQL Server", exception);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM studentCourse WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to delete the new StudentCourse on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new StudentCourse on Azure SQL Server", exception);
		}

	}

	@Override
	public List<StudentCourseDTO> find(StudentCourseDTO studentCourse) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<StudentCourseDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, name").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(studentCourse)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(studentCourse.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(studentCourse.getId());
				setWhere = false;

			}

			if (UtilNumeric.getUtilObject().isGreatherThan(studentCourse.getStudent().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("professor = ? ");
				parameters.add(studentCourse.getStudent().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(studentCourse.getCourse().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("course = ? ");
				parameters.add(studentCourse.getCourse().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(studentCourse.getState().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("professor = ? ");
				parameters.add(studentCourse.getState().getId());
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
	
	private List<StudentCourseDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<StudentCourseDTO> results = new ArrayList<>();
		
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

	private List<StudentCourseDTO> assembleResults(ResultSet resultSet) {
		List<StudentCourseDTO> results = new ArrayList<>();

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

	private StudentCourseDTO assembleDTO(ResultSet resultSet) {
		StudentCourseDTO dto = new StudentCourseDTO();
		try {
			dto.setId(resultSet.getInt("id"));
			//
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
