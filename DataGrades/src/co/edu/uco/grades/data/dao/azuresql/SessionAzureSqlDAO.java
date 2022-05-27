package co.edu.uco.grades.data.dao.azuresql;

import co.edu.uco.crosscuting.util.date.UtilDate;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SessionDTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.grades.data.dao.SessionDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class SessionAzureSqlDAO extends ConnectionSQL implements SessionDAO{

	protected SessionAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SessionDAO build(Connection connection) {
		return new SessionAzureSqlDAO(connection);
	}
		
	@Override
	public void create(SessionDTO session) {
		String sql = "INSERT INTO Session(course, date) VALUES(?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setInt(1, session.getCourse().getId());
			preparedStatement.setDate(1, (Date) session.getDate());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Session on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Session on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(SessionDTO session) {
		String sql = "UPDATE Session SET course=?, date=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, session.getCourse().getId());
			preparedStatement.setDate(1, (Date) session.getDate());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Session on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Session on Azure SQL Server", exception);
		}

	} 

	@Override
	public List<SessionDTO> find(SessionDTO session) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<SessionDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, course, date").append(SPACE);
		sb.append("FROM Session").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(session)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(session.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(session.getId());
				setWhere = false;

			}

			if (UtilNumeric.getUtilObject().isGreatherThan(session.getCourse().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("course = ? ");
				parameters.add(session.getCourse().getId());
				setWhere = false;
			}
			
			if(!UtilDate.getUtilDate().isNull(session.getDate())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("initialDate = ? ");
				parameters.add(session.getDate());
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
					"There was a problem trying to retrieve Sessions on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve Sessions on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<SessionDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<SessionDTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the Sessions on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the Sessions on Azure SQL Server", exception);
		}
		return results;
	}

	private List<SessionDTO> assembleResults(ResultSet resultSet) {
		List<SessionDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the Sessions on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the Sessions on Azure SQL Server", exception);
		}

		return results;
	}

	private SessionDTO assembleDTO(ResultSet resultSet) {
		SessionDTO dto = new SessionDTO();
		try {
			dto.setId(resultSet.getInt("id"));
		    //
			dto.setDate(resultSet.getDate("date"));
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the Sessions on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption( 	
					"An unexpected has ocurred problem trying to assemble the Sessions on Azure SQL Server", exception);
		}

		return dto;
	}

}