package co.edu.uco.grades.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;

public class StudentCourseDTO {

	private int id;
	private Student_DTO student;
	private CourseDTO course;
	private StudentCourseStateDTO state;
	
	public StudentCourseDTO() {
		super();
		setStudent(new Student_DTO());
		setCourse(new CourseDTO());
		setState(new StudentCourseStateDTO());
	}
	
	public StudentCourseDTO(int id, Student_DTO student, CourseDTO course, StudentCourseStateDTO state) {
		super();
		setId(id);
		setStudent(student);
		setCourse(course);
		setState(state);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student_DTO getStudent() {
		return student;
	}
	public void setStudent(Student_DTO student) {
		this.student = UtilObject.getUtilObject().getDefault(student, new Student_DTO());
	}
	public CourseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseDTO course) {
		this.course = UtilObject.getUtilObject().getDefault(course, new CourseDTO());
	}
	public StudentCourseStateDTO getState() {
		return state;
	}
	public void setState(StudentCourseStateDTO state) {
		this.state = UtilObject.getUtilObject().getDefault(state, new StudentCourseStateDTO());
	}
	
	
}
