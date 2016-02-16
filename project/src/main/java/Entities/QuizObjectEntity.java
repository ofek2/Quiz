package Entities;

import java.io.Serializable;
import java.util.ArrayList;

import Controllers.qPanelController;
import Views.qPanel;

public class QuizObjectEntity implements Serializable {
	private QuizEntity quizEntity;
	private ArrayList<qPanelController> qPanels;
	public QuizObjectEntity(QuizEntity quizEntity, ArrayList<qPanelController> qPanels) {
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
	public ArrayList<qPanelController> getqPanels() {
		return qPanels;
	}
	public void setqPanels(ArrayList<qPanelController> qPanels) {
		this.qPanels = qPanels;
	}
	
}
