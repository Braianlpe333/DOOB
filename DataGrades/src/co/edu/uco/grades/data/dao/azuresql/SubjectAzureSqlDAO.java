package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.SubjectDTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.grades.data.dao.SubjectDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class SubjectAzureSqlDAO extends ConnectionSQL implements SubjectDAO{

	protected SubjectAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SubjectDAO build(Connection connection) {
		return new SubjectAzureSqlDAO(connection);
	}
		
	@Override
	public void create(SubjectDTO subject) {
		String sql = "INSERT INTO Subject(name) VALUES(?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, subject.getName());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Subject on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Subject on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(SubjectDTO subject) {
		String sql = "UPDATE Subject SET name=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, subject.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Subject on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Subject on Azure SQL Server", exception);
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
					"There was a problem trying to delete the new Subject on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new Subject on Azure SQL Server", exception);
		}

	}

	@Override
	public List<SubjectDTO> find(SubjectDTO subject) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<SubjectDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, idNumber, idType, name, email").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(subject)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(subject.getId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(subject.getId());
				setWhere = false;

			}

			if(!UtilText.isEmpty(subject.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(subject.getName()));
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
	
	private List<SubjectDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<SubjectDTO> results = new ArrayList<>();
		
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

	private List<SubjectDTO> assembleResults(ResultSet resultSet) {
		List<SubjectDTO> results = new ArrayList<>();

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

	private SubjectDTO assembleDTO(ResultSet resultSet) {
		SubjectDTO dto = new SubjectDTO();
		try {
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
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
