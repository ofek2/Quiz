package Entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class StudentEntity.
 * This class is an entity for a student.
 */
public class StudentEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The student course. */
	private String studentCourse;

	/** The student id. */
	private String studentId;

	/** The student name. */
	private String studentName;

	/** The student email. */
	private String studentEmail;

	/** The quizzes scores. */
	private ArrayList<QuizScore> quizzesScores;

	/**
	 * Instantiates a new student entity.
	 *
	 * @param studentCourse
	 *            the student course
	 * @param studentId
	 *            the student id
	 * @param studentName
	 *            the student name
	 * @param studentEmail
	 *            the student email
	 */
	public StudentEntity(String studentCourse, String studentId, String studentName, String studentEmail) {
		super();
		this.studentCourse = studentCourse;
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.quizzesScores = new ArrayList<QuizScore>();
	}

	/**
	 * Gets the student course.
	 *
	 * @return the student course
	 */
	public String getStudentCourse() {
		return studentCourse;
	}

	/**
	 * Sets the student course.
	 *
	 * @param studentCourse
	 *            the new student course
	 */
	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * Gets the quizzes scores.
	 *
	 * @return the quizzes scores
	 */
	public ArrayList<QuizScore> getQuizzesScores() {
		return quizzesScores;
	}

	/**
	 * Sets the quizzes scores.
	 *
	 * @param quizzesScores
	 *            the new quizzes scores
	 */
	public void setQuizzesScores(ArrayList<QuizScore> quizzesScores) {
		this.quizzesScores = quizzesScores;
	}

	/**
	 * Sets the student id.
	 *
	 * @param studentId
	 *            the new student id
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the student name.
	 *
	 * @return the student name
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * Sets the student name.
	 *
	 * @param studentName
	 *            the new student name
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * Gets the student email.
	 *
	 * @return the student email
	 */
	public String getStudentEmail() {
		return studentEmail;
	}

	/**
	 * Sets the student email.
	 *
	 * @param studentEmail
	 *            the new student email
	 */
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	/**
	 * Adds the quiz score.
	 *
	 * @param quizName
	 *            the quiz name
	 * @param quizScore
	 *            the quiz score
	 */
	public void addQuizScore(String quizName, String quizScore) {
		quizzesScores.add(new QuizScore(quizName, quizScore));
	}

	/**
	 * Sets the quiz score.
	 *
	 * @param quizName
	 *            the quiz name
	 * @param quizScore
	 *            the quiz score
	 */
	public void setQuizScore(String quizName, String quizScore) {
		boolean flag = false;
		for (int i = 0; i < quizzesScores.size(); i++) {
			if (quizzesScores.get(i).quizName.equals(quizName)) {
				flag = true;
				quizzesScores.get(i).score = quizScore;
			}
		}
		if (flag == false)
			addQuizScore(quizName, quizScore);
	}

	/**
	 * Removes the quiz.
	 *
	 * @param quizName
	 *            the quiz name
	 */
	public void removeQuiz(String quizName) {
		for (int i = 0; i < quizzesScores.size(); i++) {
			if (quizzesScores.get(i).quizName.equals(quizName))
				quizzesScores.remove(i);
		}
	}

	/**
	 * Gets the score.
	 *
	 * @param quizName
	 *            the quiz name
	 * @return the score
	 */
	public String getScore(String quizName) {
		for (int i = 0; i < quizzesScores.size(); i++) {
			if (quizzesScores.get(i).quizName.equals(quizName))
				return quizzesScores.get(i).score;
		}
		return "-1";
	}

	/**
	 * The Class QuizScore.
	 */
	class QuizScore implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The quiz name. */
		private String quizName;

		/** The score. */
		private String score;

		/**
		 * Instantiates a new quiz score.
		 *
		 * @param quizName
		 *            the quiz name
		 * @param score
		 *            the score
		 */
		public QuizScore(String quizName, String score) {
			super();
			this.quizName = quizName;
			this.score = score;
		}

	}
}
