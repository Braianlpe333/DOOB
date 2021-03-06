package co.edu.uco.grades.crosscuting.exception;

import co.edu.uco.crosscuting.exeption.GeneralExeption;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.grades.crosscuting.exeption.enumeration.ExceptionLocation;
import co.edu.uco.grades.crosscuting.exeption.enumeration.ExeptionType;

public class GradesException extends GeneralExeption{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466241036465446636L;
	/**
	 * 
	 */
	private ExeptionType type;
	private ExceptionLocation location;
	
	
	private GradesException(String userMessage, String tecnicalMessage, Exception rootExeption, ExeptionType type,
			ExceptionLocation location) {
		super(userMessage, tecnicalMessage, rootExeption);
		setType(type);
		setLocation(location);
	}
	
	public static GradesException build(String userMessage, String technicalMessage) {
		return new GradesException(userMessage, technicalMessage, null, null, null);
	}
	
	public static GradesException build(String userMessage, String tecnicalMessage, Exception rootExeption) {
		return new GradesException(userMessage, tecnicalMessage,rootExeption, null, null);
	}
	
	public static GradesException build(String userMessage) {
		return new GradesException(userMessage, userMessage, null, ExeptionType.BUSINESS, null);
	}
	
	public static GradesException buildTechnicalExeption(String tecnicalMessage) {
		return new GradesException(null, tecnicalMessage, null, ExeptionType.TECHNICAL, null);
	}
	 
	public static GradesException buildTechnicalExeption(String tecnicalMessage, Exception rootException, ExeptionType type, ExceptionLocation location) {
		return new GradesException(null, tecnicalMessage, rootException, ExeptionType.TECHNICAL , location);
	}
	
	public static GradesException buildTechnicalDataExeption(String tecnicalMessage) {
		return new GradesException(null, tecnicalMessage, null, ExeptionType.TECHNICAL , ExceptionLocation.DATA);
	}
	
	public static GradesException buildTechnicalBusinessLogicExeption(String tecnicalMessage) {
		return new GradesException(null, tecnicalMessage, null, ExeptionType.TECHNICAL , ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static GradesException buildBusinessLogicExeption(String businessMessage) {
		return new GradesException(businessMessage, null, null, ExeptionType.BUSINESS , ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static GradesException buildTechnicalDataExeption(String tecnicalMessage, Exception rootException) {
		return new GradesException(null, tecnicalMessage, rootException, ExeptionType.TECHNICAL , ExceptionLocation.DATA);
	}
	
	
	
	
	
	public ExeptionType getType() {
		return type;
	}
	public ExceptionLocation getLocation() {
		return location;
	}
	private void setType(ExeptionType type) {
		this.type = UtilObject.getUtilObject().getDefault(type, ExeptionType.GENERAL);
	}
	private void setLocation(ExceptionLocation location) {
		this.location  = UtilObject.getUtilObject().getDefault(location, ExceptionLocation.GENERAL);
	}


}
