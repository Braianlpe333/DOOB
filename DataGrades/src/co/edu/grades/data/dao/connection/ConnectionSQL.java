package co.edu.grades.data.dao.connection;

import java.sql.Connection;

import co.edu.uco.corsscuting.util.sql.UtilConnection;
import co.edu.uco.grades.crosscuting.exception.GradesException;

public class ConnectionSQL {

	private Connection connection;

	protected ConnectionSQL(Connection connection) {
		if(UtilConnection.isClosed(connection)) {
			throw GradesException.buildTechnicalDataExeption("it's not possible to create the specific DAO because the conection is closed");
		}
		setConnection(connection);
	}

	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

}
