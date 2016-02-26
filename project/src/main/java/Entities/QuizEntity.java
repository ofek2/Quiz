package Entities;

import java.io.File;
import java.io.Serializable;

public class QuizEntity implements Serializable{
	private String name;
	private File quizFolder;
	private File quizFormFolder;
	private File studentsAnswersFolder;
	private int percentageFromFGrade;
	public QuizEntity(String name) {
		super();
		this.name = name;
	}
	
	public QuizEntity(String name, File quizFolder,File quizFormFolder, File studentsAnswersFolder, int percentageFromFGrade) {
		super();
		this.name = name;
		this.quizFolder = quizFolder;
		this.quizFormFolder = quizFormFolder;
		this.studentsAnswersFolder = studentsAnswersFolder;
		this.percentageFromFGrade = percentageFromFGrade;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public File getQuizFolder() {
		return quizFolder;
	}

	public void setQuizFolder(File quizFolder) {
		this.quizFolder = quizFolder;
	}

	public int getPercentageFromFGrade() {//////////////////set the value form the listener
		return percentageFromFGrade;
	}
	public void setPercentageFromFGrade(int percentageFromFGrade) {
		this.percentageFromFGrade = percentageFromFGrade;
	}
	
	public File getQuizFormFolder() {
		return quizFormFolder;
	}

	public File getStudentsAnswersFolder() {
		return studentsAnswersFolder;
	}

	public void setStudentsAnswersFolder(File studentsAnswersFolder) {
		this.studentsAnswersFolder = studentsAnswersFolder;
	}

	public void setQuizFormFolder(File quizFormFolder) {
		this.quizFormFolder = quizFormFolder;
	}
	
}
