package Entities;

import java.io.File;
import java.io.Serializable;

/**
 * The Class QuizEntity.
 * This class is an entity for a quiz.
 */
public class QuizEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The quiz name. */
	private String quizName;

	/** The course name. */
	private String courseName;

	/**
	 * Instantiates a new quiz entity.
	 *
	 * @param quizName
	 *            the quiz name
	 * @param courseName
	 *            the course name
	 */
	public QuizEntity(String quizName, String courseName) {
		super();
		this.quizName = quizName;
		this.courseName = courseName;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return quizName;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.quizName = name;
	}

	/**
	 * Gets the quiz folder.
	 *
	 * @return the quiz folder
	 */
	public File getQuizFolder() {
		return new File(Constants.ROOTPATH + courseName + "/Quizzes/" + quizName);
	}

	/**
	 * Gets the quiz form folder.
	 *
	 * @return the quiz form folder
	 */
	public File getQuizFormFolder() {
		return new File(Constants.ROOTPATH + courseName + "/Quizzes/" + quizName + "/Form");
	}

	/**
	 * Gets the students answers folder.
	 *
	 * @return the students answers folder
	 */
	public File getStudentsAnswersFolder() {
		return new File(Constants.ROOTPATH + courseName + "/Quizzes/" + quizName + "/StudentsAnswers");
	}

}
