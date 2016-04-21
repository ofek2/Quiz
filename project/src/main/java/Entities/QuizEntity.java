package Entities;

import java.io.File;
import java.io.Serializable;

public class QuizEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quizName;
	private String courseName;

	public QuizEntity(String quizName, String courseName) {
		super();
		this.quizName = quizName;
		this.courseName = courseName;
	}

	public String getName() {
		return quizName;
	}
	public void setName(String name) {
		this.quizName = name;
	}
	
	public File getQuizFolder() {
		return new File(Constants.ROOTPATH+courseName+"/Quizzes/"+quizName);
	}
	public File getQuizFormFolder() {
		return new File(Constants.ROOTPATH+courseName+"/Quizzes/"+quizName+"/Form");
	}

	public File getStudentsAnswersFolder() {
		return new File(
				Constants.ROOTPATH+courseName+"/Quizzes/"+quizName+"/StudentsAnswers");
	}
	
}
