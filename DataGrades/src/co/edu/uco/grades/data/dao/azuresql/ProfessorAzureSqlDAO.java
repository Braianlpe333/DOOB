package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.IdTypeDTO;
import co.edu.uco.grades.dto.ProfessorDTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.grades.data.dao.ProfessorDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class ProfessorAzureSqlDAO extends ConnectionSQL implements ProfessorDAO{

	protected ProfessorAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static ProfessorDAO build(Connection connection) {
		return new ProfessorAzureSqlDAO(connection);
	}
		
	@Override
	public void create(ProfessorDTO professor) {
		String sql = "INSERT INTO Professor(idNumber, idType, name, email) VALUES(?, ?, ?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, professor.getIdNumber());
			preparedStatement.setInt(1, professor.getIdType().getId());
			preparedStatement.setString(1, professor.getName());
			preparedStatement.setString(1, professor.getEmail());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Professor on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Professor on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(ProfessorDTO professor) {
		String sql = "UPDATE Professor SET idNumber=?, idType=?, name=?, email=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, professor.getIdNumber());
			preparedStatement.setObject(1, professor.getIdType());
			preparedStatement.setString(1, professor.getName());
			preparedStatement.setString(1, professor.getEmail());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Professor on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Professor on Azure SQL Server", exception);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Professor WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to delete the new Professor on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new Professor on Azure SQL Server", exception);
		}

	}

	@Override
	public List<ProfessorDTO> find(ProfessorDTO professor) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<ProfessorDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, idNumber, idType, name, email").append(SPACE);
		sb.append("FROM Professor").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(professor)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(professor.getId(), 0)) {

				sb.append("WHERE id = ? ");
				parameters.add(professor.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(professor.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(professor.getIdNumber()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(professor.getIdType().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(professor.getIdType().getId());
				setWhere = false;
			}
			
			if(!UtilText.isEmpty(professor.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(professor.getName()));
				setWhere = false;
			}
			
			if(!UtilText.isEmpty(professor.getEmail())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("email = ? ");
				parameters.add(UtilText.trim(professor.getEmail()));
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
					"There was a problem trying to retrieve Professor on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve Professor on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<ProfessorDTO> executeQuery(PreparedStatement preparedStatement){
		
		List<ProfessorDTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the Professors on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the Professors on Azure SQL Server", exception);
		}
		return results;
	}

	private List<ProfessorDTO> assembleResults(ResultSet resultSet) {
		List<ProfessorDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the Professors on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the Professors on Azure SQL Server", exception);
		}

		return results;
	}

	private ProfessorDTO assembleDTO(ResultSet resultSet) {
		ProfessorDTO dto = new ProfessorDTO();
		try {
			
			int idType = resultSet.getInt("");
			IdTypeDTO idtypeDTO = new IdTypeDTO(idType,UtilText.EMPTY);
			List<IdTypeDTO> idTypeList = IdTypeAzureSqlDAO.build(getConnection()).find(idtypeDTO);
			dto.setId(resultSet.getInt("id"));
			dto.setIdNumber(resultSet.getString("idNumber"));
			dto.setIdType(idTypeList.get(0));
			
			System.out.println("Prueba Commit");
			dto.setName(resultSet.getString("name"));
			dto.setEmail(resultSet.getString("email"));

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the Professors on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to assemble the Professors on Azure SQL Server", exception);
		}

		return dto;
	}

}




