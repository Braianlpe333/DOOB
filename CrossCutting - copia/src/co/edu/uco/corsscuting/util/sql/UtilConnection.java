package co.edu.uco.corsscuting.util.sql;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.crosscuting.exeption.GeneralExeption;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class UtilConnection {

    private UtilConnection() {

    }

    public static boolean isClosed(Connection connection) {

        if (UtilObject.getUtilObject().isNull(connection)) {

            throw GeneralExeption.build("Connection is null!!!");
        }
        try {
            return connection.isClosed();
        } catch (SQLException exception) {
            throw GeneralExeption.build("Problems trying validate if connection was closed", exception);
        }

    }
	
	public static boolean isOpen(Connection connection) {
		return !isClosed(connection);
	}
}
