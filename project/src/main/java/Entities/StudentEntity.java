package Entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Controllers.qPanelController;

public class StudentEntity implements Serializable{
	private String studentCourse;
	private String studentId;
	private String studentName;
	private String studentEmail;
//	private QuizScore quizScore;
	private ArrayList<QuizScore> quizzesScores;
	public StudentEntity(String studentCourse, String studentId,
			String studentName, String studentEmail) {
		super();
		this.studentCourse = studentCourse;
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.quizzesScores = new ArrayList<QuizScore>();
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
	public void addQuizScore(String quizName,String quizScore)
	{
		quizzesScores.add(new QuizScore(quizName, quizScore));
	}
	public void setQuizScore(String quizName,String quizScore)
	{
		boolean flag=false;
		for (int i = 0; i < quizzesScores.size(); i++) {
			if(quizzesScores.get(i).quizName.equals(quizName))
			{
				flag = true;
				quizzesScores.get(i).score = quizScore;
			}
		}		
		if(flag==false)
			addQuizScore(quizName,quizScore);
	}
	public void removeQuiz(String quizName)
	{
		for (int i = 0; i < quizzesScores.size(); i++) {
			if(quizzesScores.get(i).quizName.equals(quizName))
				quizzesScores.remove(i);
		}		
	}
	public String getScore(String quizName)
	{
		for (int i = 0; i < quizzesScores.size(); i++) {
			if(quizzesScores.get(i).quizName.equals(quizName))
				return quizzesScores.get(i).score;
		}
		return "-1";
	}
	class QuizScore implements Serializable
	{
		private String quizName;
		private String score;
		
		public QuizScore(String quizName, String score) {
			super();
			this.quizName = quizName;
			this.score = score;
		}
		
	}
}
