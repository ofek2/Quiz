package Entities;

import java.io.Serializable;
import java.util.ArrayList;

import Views.qPanel;

public class QuizObjectEntity implements Serializable {
	private QuizEntity quizEntity;
	private ArrayList<qPanel> qPanels;
	public QuizObjectEntity(QuizEntity quizEntity, ArrayList<qPanel> qPanels) {
		super();
		this.quizEntity = quizEntity;
		this.qPanels = qPanels;
	}
	public QuizEntity getQuizEntity() {
		return quizEntity;
	}
	public void setQuizEntity(QuizEntity quizEntity) {
		this.quizEntity = quizEntity;
	}
	public ArrayList<qPanel> getqPanels() {
		return qPanels;
	}
	public void setqPanels(ArrayList<qPanel> qPanels) {
		this.qPanels = qPanels;
	}
	
}
