package Entities;

import java.io.File;
import java.io.Serializable;

public class QuizEntity implements Serializable{
	private transient String name;
	private transient File quizFolder;
	private transient int percentageFromFGrade;
	public QuizEntity(String name) {
		super();
		this.name = name;
	}
	public QuizEntity(String name,int percentageFFgrade,File quizFolder) {
		super();
		this.name = name;
		this.percentageFromFGrade = percentageFFgrade;
		this.quizFolder = quizFolder;
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
	
	public File getQuizFolder() {
		return quizFolder;
	}
	
}
