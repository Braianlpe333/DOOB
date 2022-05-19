package co.edu.uco.grades.dto;

import java.util.Date;

import co.edu.uco.crosscuting.util.date.UtilDate;
import co.edu.uco.crosscutting.util.object.UtilObject;

public class SessionDTO {
	
	private int id;
	private CourseDTO course;
	private Date date;
	
	public SessionDTO() {
		super();
		setCourse(new CourseDTO());
	}
	
	public SessionDTO(int id, CourseDTO course, Date date) {
		super();
		setId(id);
		setCourse(course);
		setDate(date);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CourseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseDTO course) {
		this.course = UtilObject.getUtilObject().getDefault(course, new CourseDTO());
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = UtilDate.getUtilDate().getDefault(date);
	}
	
	
}
