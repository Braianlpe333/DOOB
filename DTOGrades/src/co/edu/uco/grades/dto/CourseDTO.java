package co.edu.uco.grades.dto;
//import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class CourseDTO {
	
	private int id;
	private SubjectDTO subject;
	private ProfessorDTO professor;
	private Date initialDate;
	private Date finalDate;
	
	public CourseDTO() {
		super();
		setSubject(new SubjectDTO());
		setProfessor(new ProfessorDTO());
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	public CourseDTO(int id, SubjectDTO subject, ProfessorDTO professor, Date initialDate, Date finalDate) {
		super();
		setId(id);
		setSubject(subject);
		setProfessor(professor);
		setInitialDate(initialDate);
		setFinalDate(finalDate);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = UtilObject.getUtilObject().getDefault(subject, new SubjectDTO());
	}
	public ProfessorDTO getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorDTO professor) {
		this.professor = UtilObject.getUtilObject().getDefault(professor, new ProfessorDTO());
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
	
	
	//public static SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY");
	
}
