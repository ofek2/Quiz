package Entities;

public class QuizEntity {
	private String name;
	
	private int percentageFromFGrade;
	public QuizEntity(String name) {
		super();
		this.name = name;
	}
	public QuizEntity(String name,int percentageFFgrade) {
		super();
		this.name = name;
		this.percentageFromFGrade = percentageFFgrade;
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
	
}
