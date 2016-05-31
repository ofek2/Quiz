package Entities;

import java.io.Serializable;
import java.util.ArrayList;

import Controllers.qPanelController;
import Views.qPanel;

/**
 * The Class QuizObjectEntity.
 * This class is an entity used for saving quiz creation progress for later editing.
 */
public class QuizObjectEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The quiz entity. */
	private QuizEntity quizEntity;

	/** The questions panels. */
	private ArrayList<qPanelController> qPanels;

	/**
	 * Instantiates a new quiz object entity.
	 *
	 * @param quizEntity
	 *            the quiz entity
	 * @param qPanels
	 *            the questions panels
	 */
	public QuizObjectEntity(QuizEntity quizEntity, ArrayList<qPanelController> qPanels) {
		super();
		this.quizEntity = quizEntity;
		this.qPanels = qPanels;
	}

	/**
	 * Gets the quiz entity.
	 *
	 * @return the quiz entity
	 */
	public QuizEntity getQuizEntity() {
		return quizEntity;
	}

	/**
	 * Sets the quiz entity.
	 *
	 * @param quizEntity
	 *            the new quiz entity
	 */
	public void setQuizEntity(QuizEntity quizEntity) {
		this.quizEntity = quizEntity;
	}

	/**
	 * Gets the q panels.
	 *
	 * @return the q panels
	 */
	public ArrayList<qPanelController> getqPanels() {
		return qPanels;
	}

	/**
	 * Sets the questions panels.
	 *
	 * @param qPanels
	 *            the new questions panels
	 */
	public void setqPanels(ArrayList<qPanelController> qPanels) {
		this.qPanels = qPanels;
	}

}
