package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Views.GmailAuthFrame;
import Views.GradingWindowView;
import Views.StudentGradingPanel;
import Views.ViewPanel;

public class GradingWindowController {
	private GradingWindowView view;
	private ArrayList<String> studentsIds;
	private ArrayList<StudentGradingPanel> studentGradingPanels;
	private ArrayList<StudentGradingController> studentGradingControllers;
	private ArrayList<String> studentsQuizzesPaths;
	private Container previousView;

	public GradingWindowController(GradingWindowView view) {
		this.view = view;

		addListeners();
	}

	public Container getPreviousView() {
		return previousView;
	}

	public void setPreviousView(Container previousView) {
		this.previousView = previousView;
	}

	private void addListeners() {
		ActionListener[] fileListeners = { new SendListener(), new ExitListener() };
		view.addFileListeners(fileListeners);

	}

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

	class SendListener implements ActionListener {
		private boolean allChecked = true;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

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

	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel) previousView);
		}

	}

}
