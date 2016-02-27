package Entities;

import java.io.File;

public class StudentQuizEntity {
	private StudentEntity studentEntity;
	private File quizAnswersFolder;
	
	public StudentQuizEntity(StudentEntity studentEntity, File quizAnswersFolder) {
		super();
		this.studentEntity = studentEntity;
		this.quizAnswersFolder = quizAnswersFolder;
	}
	public StudentEntity getStudentEntity() {
		return studentEntity;
	}
	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
	public File getQuizAnswersFolder() {
		return quizAnswersFolder;
	}
	public void setQuizAnswersFolder(File quizAnswersFolder) {
		this.quizAnswersFolder = quizAnswersFolder;
	}
	
}
