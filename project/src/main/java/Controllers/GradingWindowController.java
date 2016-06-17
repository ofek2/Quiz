package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Entities.Constants;
import Views.GmailAuthFrame;
import Views.GradingWindowView;
import Views.HelpFrame;
import Views.StudentGradingPanel;
import Views.ViewPanel;

/**
 * The Class GradingWindowController. 
 * This class controls the {@link GradingWindowView} events. 
 * This class is used for grading the students' quizzes and sending final grades via Email.
 */
public class GradingWindowController {

	/** The view. */
	private GradingWindowView view;

	/** The students ids. */
	private ArrayList<String> studentsIds;

	/** The student grading panels. */
	private ArrayList<StudentGradingPanel> studentGradingPanels;

	/** The student grading controllers. */
	private ArrayList<StudentGradingController> studentGradingControllers;

	/** The students quizzes paths. */
	private ArrayList<String> studentsQuizzesPaths;

	/** The previous view. */
	private Container previousView;

	/**
	 * Instantiates a new grading window controller.
	 *
	 * @param view
	 *            the view
	 */
	public GradingWindowController(GradingWindowView view) {
		this.view = view;

		addListeners();
	}

	/**
	 * Gets the previous view.
	 *
	 * @return the previous view
	 */
	public Container getPreviousView() {
		return previousView;
	}

	/**
	 * Sets the previous view.
	 *
	 * @param previousView
	 *            the new previous view
	 */
	public void setPreviousView(Container previousView) {
		this.previousView = previousView;
	}

	/**
	 * Adds the listeners.
	 */
	private void addListeners() {
		ActionListener[] fileListeners = { new SendListener(), new ExitListener() };
		view.addFileListeners(fileListeners);
		view.addHelpActionListener(new HelpListener());
	}

	/**
	 * Load students to table.
	 *
	 * @param studentsIds
	 *            the students ids
	 * @param studentsQuizzesPaths
	 *            the students' quizzes paths
	 * @param originalQuizFormPath
	 *            the original quiz form path
	 */
	public void loadStudentsToTable(ArrayList<String> studentsIds, ArrayList<String> studentsQuizzesPaths,
			String originalQuizFormPath) {
		this.studentsIds = studentsIds;
		studentGradingPanels = new ArrayList<StudentGradingPanel>();
		studentGradingControllers = new ArrayList<StudentGradingController>();
		this.studentsQuizzesPaths = studentsQuizzesPaths;
		for (int i = 0; i < studentsIds.size(); i++) {
			StudentGradingPanel sview = new StudentGradingPanel(this.studentsIds.get(i));
			studentGradingPanels.add(sview);
			studentGradingControllers
					.add(new StudentGradingController(sview, studentsQuizzesPaths.get(i), originalQuizFormPath, view));

			view.tablePanel.add(sview);

		}
		view.tablePanel.revalidate();
	}

	/**
	 * The listener interface for receiving send events. The class that is
	 * interested in processing a send event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addSendListener<code> method. When the send event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see SendEvent
	 */
	class SendListener implements ActionListener {

		/** Signals that all the students are graded */
		private boolean allChecked = true;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			allChecked = true;
			for (int i = 0; i < studentGradingPanels.size(); i++) {
				if (studentGradingPanels.get(i).getGradeBtn().getText().equals("Grade")) {
					allChecked = false;
				}
			}
		
			if (allChecked) {

				new GmailAuthFrameController(new GmailAuthFrame(), studentGradingControllers, studentsQuizzesPaths);

			} else
				JOptionPane.showMessageDialog(null,
						"You must grade all of the quizzes before sending the graded quizzes.", "Alert",
						JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * The listener interface for receiving exit events. The class that is
	 * interested in processing a exit event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When the exit event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see ExitEvent
	 */
	class ExitListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel) previousView);
		}

	}

	/**
	 * The listener interface for receiving help events. The class that is
	 * interested in processing a help event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addHelpListener<code> method. When the help event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see HelpEvent
	 */
	class HelpListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new helpFrameController(new HelpFrame(Constants.HELP_GRADEQUIZ,Constants.HELP_GRADEQUIZ_AMOUNT));
		}

	}

}
