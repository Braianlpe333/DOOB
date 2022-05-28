package co.edu.uco.grades.data.dao.azuresql;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.grades.crosscuting.exception.GradesException;
import co.edu.uco.grades.dto.IdTypeDTO;
import co.edu.uco.grades.dto.Student_DTO;
import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.grades.data.dao.StudentDAO;
import co.edu.grades.data.dao.connection.ConnectionSQL;

public class StudentAzureSqlDAO extends ConnectionSQL implements StudentDAO{

	protected StudentAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StudentDAO build(Connection connection) {
		return new StudentAzureSqlDAO(connection);
	}
		
	@Override
	public void create(Student_DTO student) {
		String sql = "INSERT INTO Students(idNumber, idType, name, email) VALUES(?, ?, ?, ?)";
		 
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
			preparedStatement.setString(1, student.getIdNumber());
			preparedStatement.setInt(1, student.getIdType().getId());
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(1, student.getEmail());
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException exception){
			throw GradesException.buildTechnicalDataExeption("There was a problem trying to create the new Student on Azure SQL Server", exception);
		}catch(Exception exception){
			throw GradesException.buildTechnicalDataExeption("An unexpected has ocurred problem trying to create the new Student on Azure SQL Server", exception);
		}
	}

	@Override
	public void update(Student_DTO student) {
		String sql = "UPDATE Students SET idNumber=?, idType=?, name=?, email=?  WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, student.getIdNumber());
			preparedStatement.setInt(1, student.getIdType().getId());
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(1, student.getEmail());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to update the new Student on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to update the new Student on Azure SQL Server", exception);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Student WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to delete the new Student on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to delete the new Student on Azure SQL Server", exception);
		}

	}

	@Override
	public List<Student_DTO> find(Student_DTO student) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<Student_DTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT id, idNumber, idType, name, email").append(SPACE);
		sb.append("FROM Student").append(SPACE);

		if (!UtilObject.getUtilObject().isNull(student)) {

			if (UtilNumeric.getUtilObject().isGreatherThan(student.getId(), 0)) {

				sb.append("WHERE id = ? ");
				parameters.add(student.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(student.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(student.getIdNumber()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilObject().isGreatherThan(student.getIdType().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(student.getIdType().getId());
				setWhere = false;
			}
			
			if(!UtilText.isEmpty(student.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(student.getName()));
				setWhere = false;
			}
			
			if(!UtilText.isEmpty(student.getEmail())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("email = ? ");
				parameters.add(UtilText.trim(student.getEmail()));
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
					"There was a problem trying to retrieve Students on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to retrieve Students on Azure SQL Server", exception);
		}

		return results;
	}
	
	private List<Student_DTO> executeQuery(PreparedStatement preparedStatement){
		
		List<Student_DTO> results = new ArrayList<>();
		
		try (ResultSet resultset = preparedStatement.executeQuery()) {
			results = assembleResults(resultset);
		}catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to execute the Query for recovery the Students on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to execute the Query for recovery the Students on Azure SQL Server", exception);
		}
		return results;
	}

	private List<Student_DTO> assembleResults(ResultSet resultSet) {
		List<Student_DTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {
				results.add(assembleDTO(resultSet));
			}
		} catch (GradesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to recover the Students on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to recover the Students on Azure SQL Server", exception);
		}

		return results;
	}

	private Student_DTO assembleDTO(ResultSet resultSet) {
		Student_DTO dto = new Student_DTO();
		try {
			int idType = resultSet.getInt("");
			IdTypeDTO idtypeDTO = new IdTypeDTO(idType,UtilText.EMPTY);
			List<IdTypeDTO> idTypeList = IdTypeAzureSqlDAO.build(getConnection()).find(idtypeDTO);
			dto.setId(resultSet.getInt("id"));
			dto.setIdNumber(resultSet.getString("idNumber"));
			dto.setIdType(idTypeList.get(0));
			dto.setName(resultSet.getString("name"));
			dto.setEmail(resultSet.getString("email"));

		} catch (SQLException exception) {
			throw GradesException.buildTechnicalDataExeption(
					"There was a problem trying to assemble the Students on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw GradesException.buildTechnicalDataExeption(
					"An unexpected has ocurred problem trying to assemble the Students on Azure SQL Server", exception);
		}

		return dto;
	}

}


