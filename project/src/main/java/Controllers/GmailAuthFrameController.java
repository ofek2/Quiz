package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Views.GmailAuthFrame;
import project.GoogleMail;

public class GmailAuthFrameController {
	private GmailAuthFrame view;
	private ArrayList<StudentGradingController> studentGradingControllers;
	private ArrayList<String> studentsQuizzesPaths;

	public GmailAuthFrameController(GmailAuthFrame view, ArrayList<StudentGradingController> studentGradingControllers,
			ArrayList<String> studentsQuizzesPaths) {
		this.view = view;
		this.studentGradingControllers = studentGradingControllers;
		this.studentsQuizzesPaths = studentsQuizzesPaths;

		authorizeGmailAccount();

		this.view.sendBtnAddListener(new sendBtnListener());

	}

	private void authorizeGmailAccount() {
		// TODO Auto-generated method stub
		try {
			String url = GoogleMail.startAuthorize();
			view.loadURL(url);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class sendBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to send graded quizzes to your students?",
					"Alert", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String quizName = "";
				if (!studentGradingControllers.isEmpty())
					quizName = studentGradingControllers.get(0).getQuizName();
				for (int i = 0; i < studentGradingControllers.size(); i++) {
					GoogleMail.SendMail(studentGradingControllers.get(i).getStudentEmail(), quizName + " - Graded Quiz",
							studentsQuizzesPaths.get(i), quizName + ".html");
				}
				JOptionPane.showMessageDialog(null,
						"The graded quizzes were sent successfully to your students' emails.",
						"Emails Delivered Successfully", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

}
