package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import Entities.StudentEntity;
import Utilities.HtmlBuilder;
import Utilities.ObjectFileManager;
import Views.GradingOperation;
import Views.StudentGradingPanel;

/**
 * The Class StudentGradingController. This class controls the
 * {@link StudentGradingPanel} events.
 */
public class StudentGradingController {

	/** The view. */
	public StudentGradingPanel view;

	/** The student quiz folder path. */
	private String studentQuizFolderPath;

	/** The original quiz form path. */
	private String originalQuizFormPath;

	/** The previous view. */
	private Container previousView;

	/** The student quiz folder. */
	private File studentQuizFolder;

	/** The student id. */
	private String studentId;

	/** The quiz name. */
	private String quizName;

	/** The result. */
	private StudentEntity result;

	/** The quiz score. */
	private String quizScore;

	/** The student email. */
	private String studentEmail;

	/**
	 * Instantiates a new student grading controller.
	 *
	 * @param view
	 *            the view
	 * @param studentQuizFolderPath
	 *            the student quiz folder path
	 * @param originalQuizFormPath
	 *            the original quiz form path
	 * @param previousView
	 *            the previous view
	 */
	public StudentGradingController(StudentGradingPanel view, String studentQuizFolderPath, String originalQuizFormPath,
			Container previousView) {
		this.view = view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());
		this.studentQuizFolderPath = studentQuizFolderPath;
		this.originalQuizFormPath = originalQuizFormPath;
		this.previousView = previousView;
		try {
			studentQuizFolder = new File(studentQuizFolderPath);
			studentId = studentQuizFolder.getName();
			quizName = studentQuizFolder.getParentFile().getParentFile().getName();
			result = (StudentEntity) ObjectFileManager.loadObject(
					studentQuizFolder.getParentFile().getParentFile().getParentFile().getParentFile().getCanonicalPath()
							+ "/Students/" + studentId + ".ser");
			studentEmail = result.getStudentEmail();
			quizScore = result.getScore(quizName);
			updateQuizScoreInView();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Update quiz score in view.
	 */
	public void updateQuizScoreInView() {

		if (!quizScore.equals("-1")) {
			view.getLblGrade().setText(quizScore);
			view.getGradeBtn().setText("Edit");
		}
	}

	/**
	 * The listener interface for receiving gradeBtnAddAction events. The class
	 * that is interested in processing a gradeBtnAddAction event implements
	 * this interface, and the object created with that class is registered with
	 * a component using the component's <code>addgradeBtnAddActionListener
	 * <code> method. When the gradeBtnAddAction event occurs, that object's
	 * appropriate method is invoked.
	 *
	 * @see gradeBtnAddActionEvent
	 */
	class gradeBtnAddActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			String studentQuizPath = studentQuizFolderPath + "/" + studentId + ".html";
			try {
				if (view.getGradeBtn().getText().equals("Grade")) {
					HtmlBuilder htmlBuilder = new HtmlBuilder();
					htmlBuilder.prepareQuizForGrading(studentQuizPath, originalQuizFormPath);

				}
				GradingOperation gradingOperation = new GradingOperation(view, studentQuizPath, previousView);
				MainFrameController.view.changeContentPane(gradingOperation);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (@SuppressWarnings("hiding") IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	/**
	 * Gets the student quiz folder path.
	 *
	 * @return the student quiz folder path
	 */
	public String getStudentQuizFolderPath() {
		return studentQuizFolderPath;
	}

	/**
	 * Gets the student quiz folder.
	 *
	 * @return the student quiz folder
	 */
	public File getStudentQuizFolder() {
		return studentQuizFolder;
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
	 * Gets the student email.
	 *
	 * @return the student email
	 */
	public String getStudentEmail() {
		return studentEmail;
	}

	/**
	 * Gets the quiz name.
	 *
	 * @return the quiz name
	 */
	public String getQuizName() {
		return quizName;
	}

}
