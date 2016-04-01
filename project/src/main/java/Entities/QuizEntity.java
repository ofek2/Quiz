package Entities;

import java.io.File;
import java.io.Serializable;

public class QuizEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quizName;
//	private File quizFolder;
//	private File quizFormFolder;
//	private File studentsAnswersFolder;
//	private int percentageFromFGrade;
	private String courseName;
//	public QuizEntity(String name) {
//		super();
//		this.name = name;
//	}
	
//	public QuizEntity(String name, File quizFolder,File quizFormFolder, File studentsAnswersFolder, int percentageFromFGrade) {
//		super();
//		this.name = name;
//		this.quizFolder = quizFolder;
//		this.quizFormFolder = quizFormFolder;
//		this.studentsAnswersFolder = studentsAnswersFolder;
//		this.percentageFromFGrade = percentageFromFGrade;
//	}

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

//	public void setQuizFolder(File quizFolder) {
//		this.quizFolder = quizFolder;
//	}

//	public int getPercentageFromFGrade() {//////////////////set the value form the listener
//		return percentageFromFGrade;
//	}
//	public void setPercentageFromFGrade(int percentageFromFGrade) {
//		this.percentageFromFGrade = percentageFromFGrade;
//	}
	
	public File getQuizFormFolder() {
		return new File(Constants.ROOTPATH+courseName+"/Quizzes/"+quizName+"/Form");
	}

	public File getStudentsAnswersFolder() {
		return new File(
				Constants.ROOTPATH+courseName+"/Quizzes/"+quizName+"/StudentsAnswers");
	}

//	public void setStudentsAnswersFolder(File studentsAnswersFolder) {
//		this.studentsAnswersFolder = studentsAnswersFolder;
//	}
//
//	public void setQuizFormFolder(File quizFormFolder) {
//		this.quizFormFolder = quizFormFolder;
//	}
	
}
