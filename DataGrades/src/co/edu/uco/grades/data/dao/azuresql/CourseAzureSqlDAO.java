package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.crosscuting.util.date.UtilDate;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.CourseDTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "UPDATE IdType SET subject=?, professor=?, initialDate=?, finalDate=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, course.getSubject().getId());
			preparedStatement.setInt(1, course.getProfessor().getId());
			preparedStatement.setDate(1, (Date) course.getInitialDate());
			preparedStatement.setDate(1, (Date) course.getFinalDate());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Course on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Course on Azure SQL Server", exception);
		}

	}

	@Override
	public List<CourseDTO> find(CourseDTO course) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<CourseDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, idNumber, idType, name, email").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(course)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(course.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(course.getId());
				setWhere = false;

			}

			if (UtilNumeric.getUtilObject().isGreatherThan(course.getSubject().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("subject = ? ");
				parameters.add(course.getSubject().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(course.getProfessor().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("professor = ? ");
				parameters.add(course.getProfessor().getId());
				setWhere = false;
			}
			
			if(!UtilDate.getUtilDate().isNull(course.getInitialDate())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("initialDate = ? ");
				parameters.add(course.getInitialDate());
				setWhere = false;
			}	
			
			if(!UtilDate.getUtilDate().isNull(course.getFinalDate())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("finalDate = ? ");
				parameters.add(course.getFinalDate());
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
					"There was a problem trying to retrieve Course on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve Course on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<CourseDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<CourseDTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the Courses on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the Courses on Azure SQL Server", exception);
		}
		return results;
	}

	private List<CourseDTO> assembleResults(ResultSet resultSet) {
		List<CourseDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the Courses on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the Courses on Azure SQL Server", exception);
		}

		return results;
	}

	private CourseDTO assembleDTO(ResultSet resultSet) {
		CourseDTO dto = new CourseDTO();
		try {
			dto.setId(resultSet.getInt("id"));
			//dto.setSubject(resultSet.getInt("subject"));
			//dto.setProfessor(resultSet.getInt("professor"));
			dto.setInitialDate(resultSet.getDate("initialDate"));
			dto.setFinalDate(resultSet.getDate("finalDate"));
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the Courses on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to assemble the Courses on Azure SQL Server", exception);
		}

		return dto;
	}

}
