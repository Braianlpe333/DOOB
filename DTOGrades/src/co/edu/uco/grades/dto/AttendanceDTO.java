package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class AttendanceDTO {
	
	private int id;
	private StudentCourseDTO studentCourse;
	private SessionDTO session;
	private boolean attended;
	
	public AttendanceDTO() {
		super();
		setStudentCourse(new StudentCourseDTO());
		setSession(new SessionDTO());
	}
	
	public AttendanceDTO(int id, StudentCourseDTO studentCourse, SessionDTO session, boolean attended) {
		super();
		setId(id);
		setStudentCourse(studentCourse);
		setSession(session);
		setAttended(attended);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StudentCourseDTO getStudentCourse() {
		return studentCourse;
	}
	public void setStudentCourse(StudentCourseDTO studentCourse) {
		this.studentCourse = UtilObject.getUtilObject().getDefault(studentCourse, new StudentCourseDTO());
	}
	public SessionDTO getSession() {
		return session;
	}
	public void setSession(SessionDTO session) {
		this.session = UtilObject.getUtilObject().getDefault(session, new SessionDTO());
	}
	public boolean isAttended() {
		return attended;
	}
	public void setAttended(boolean attended) {
		this.attended = attended;
	}
	
	
}
