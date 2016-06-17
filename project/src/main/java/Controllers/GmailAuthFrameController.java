package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Views.CustomDialog;
import Views.GmailAuthFrame;
import project.GoogleMail;

/**
 * The Class GmailAuthFrameController.
 * This class controls Gmail authentication process
 */
public class GmailAuthFrameController {
	
	/** The view. */
	private GmailAuthFrame view;
	
	/** The student grading controllers. */
	private ArrayList<StudentGradingController> studentGradingControllers;
	
	/** The students quizzes paths. */
	private ArrayList<String> studentsQuizzesPaths;
	
	/**
	 * Instantiates a new Gmail auth frame controller.
	 *
	 * @param view the view
	 * @param studentGradingControllers a list of StudentGradingController 
	 * @param studentsQuizzesPaths a list of the students' quizzes paths 
	 */
	public GmailAuthFrameController(GmailAuthFrame view, ArrayList<StudentGradingController> studentGradingControllers,
			ArrayList<String> studentsQuizzesPaths) {
		this.view = view;
		this.studentGradingControllers = studentGradingControllers;
		this.studentsQuizzesPaths = studentsQuizzesPaths;
		authorizeGmailAccount();
		this.view.sendBtnAddListener(new sendBtnListener());

	}

	/**
	 * Authorize Gmail account.
	 */
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

	/**
	 * The listener interface for receiving sendBtn events.
	 * The class that is interested in processing a sendBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addsendBtnListener<code> method. When
	 * the sendBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see sendBtnEvent
	 */
	class sendBtnListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to send graded quizzes to your students?",
					"Alert", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String quizName = "";
				if (!studentGradingControllers.isEmpty())
					quizName = studentGradingControllers.get(0).getQuizName();
				CustomDialog dialog = new CustomDialog();
				dialog.setVisible(true);
				new sendMails(studentGradingControllers, studentsQuizzesPaths, quizName,dialog).execute();
			}
		}
	}
}
