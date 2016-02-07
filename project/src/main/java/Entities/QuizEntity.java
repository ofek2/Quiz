package Entities;

import java.io.File;

public class QuizEntity {
	private String name;
	private File quizFile;
	private int percentageFromFGrade;
	public QuizEntity(String name) {
		super();
		this.name = name;
	}
	public QuizEntity(String name,int percentageFFgrade,File quizFile) {
		super();
		this.name = name;
		this.percentageFromFGrade = percentageFFgrade;
		this.quizFile = quizFile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPercentageFromFGrade() {
		return percentageFromFGrade;
	}
	public void setPercentageFromFGrade(int percentageFromFGrade) {
		this.percentageFromFGrade = percentageFromFGrade;
	}
	
	public File getQuizFile() {
		return quizFile;
	}
	
}
