package co.edu.uco.grades.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.grades.data.dao.AttendanceDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
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
			preparedStatement.setInt(1, attendance.getStudentCourse().getId());
			preparedStatement.setInt(1, attendance.getSession().getId());
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
		String sql = "UPDATE Subject SET name=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, attendance.getStudentCourse().getId());
			preparedStatement.setInt(1, attendance.getSession().getId());
			preparedStatement.setBoolean(1, attendance.isAttended());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Attendance on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Attendance on Azure SQL Server", exception);
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
					"There was a problem trying to delete the new Attendance on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new Attendance on Azure SQL Server", exception);
		}

	}

	@Override
	public List<AttendanceDTO> find(AttendanceDTO attendance) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<AttendanceDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, idNumber, idType, name, email").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(attendance)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(attendance.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(attendance.getId());
				setWhere = false;

			}

			if (UtilNumeric.getUtilObject().isGreatherThan(attendance.getStudentCourse().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("course = ? ");
				parameters.add(attendance.getStudentCourse().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(attendance.getSession().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("course = ? ");
				parameters.add(attendance.getSession().getId());
				setWhere = false;
			}
			
			if(!UtilObject.getUtilObject().isNull(attendance.isAttended())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("attendance = ? ");
				parameters.add(attendance.isAttended());
				setWhere = false;
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
					"There was a problem trying to retrieve Subject on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve Subject on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<AttendanceDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<AttendanceDTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the Subjects on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the Subjects on Azure SQL Server", exception);
		}
		return results;
	}

	private List<AttendanceDTO> assembleResults(ResultSet resultSet) {
		List<AttendanceDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the Subjects on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the Subjects on Azure SQL Server", exception);
		}

		return results;
	}

	private AttendanceDTO assembleDTO(ResultSet resultSet) {
		AttendanceDTO dto = new AttendanceDTO();
		try {
			dto.setId(resultSet.getInt("id"));
			//
			dto.setAttended(resultSet.getBoolean("attended"));
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the Subjects on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption( 	
					"An unexpected has ocurred problem trying to assemble the Subjects on Azure SQL Server", exception);
		}

		return dto;
	}

}