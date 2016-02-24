package Entities;

import java.io.Serializable;

public class StudentEntity implements Serializable{
	private String studentCourse;
	private String studentId;
	private String studentName;
	private String studentEmail;
	public StudentEntity(String studentCourse, String studentId,
			String studentName, String studentEmail) {
		super();
		this.studentCourse = studentCourse;
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
	}
	public String getStudentCourse() {
		return studentCourse;
	}
	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	
	
}
