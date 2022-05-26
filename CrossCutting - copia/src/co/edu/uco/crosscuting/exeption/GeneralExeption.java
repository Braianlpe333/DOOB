package co.edu.uco.crosscuting.exeption;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class GeneralExeption extends RuntimeException {

	private static final long serialVersionUID = -1285367579906667798L;
	/**
	 * 
	 */
	private String userMessage;
	private String tecnicalMessage;
	private Exception rootExeption;
	
	protected GeneralExeption(String userMessage, String tecnicalMessage, Exception rootExeption) {
		super();
		getUserMessage();
		getTecnicalMessage();
		getRootExeption();
	}
	
	public static GeneralExeption build(String technicalMessage) {
		return new GeneralExeption(null, technicalMessage, null);
	}
	
	public static GeneralExeption build(String technicalMessage, Exception rootExeption) {
		return new GeneralExeption(null, technicalMessage,rootExeption);
	}
	
	
	public String getUserMessage() {
		return userMessage;
	}
	public String getTecnicalMessage() {
		return tecnicalMessage;
	}
	public Exception getRootExeption() {
		return rootExeption;
	}
	private void setUserMessage(String userMessage) {
		this.userMessage = UtilText.trim(userMessage);
	}
	private void setTecnicalMessage(String tecnicalMessage) {
		this.tecnicalMessage = UtilText.trim(tecnicalMessage);
	}
	private void setRootExeption(Exception rootExeption) {
		this.rootExeption = UtilObject.getUtilObject().getDefault(rootExeption, new Exception());
	}
	

}
