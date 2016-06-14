package Controllers;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Entities.Constants;
import Views.CustomDialog;
import project.GoogleMail;
import project.zipFileManager;

public class sendMails extends SwingWorker<Void, Void>{

	/** The student grading controllers. */
	private ArrayList<StudentGradingController> studentGradingControllers;
	
	/** The students quizzes paths. */
	private ArrayList<String> studentsQuizzesPaths;

	private String quizName;
	
	private CustomDialog dialog;
	
	public  sendMails(ArrayList<StudentGradingController> studentGradingControllers,ArrayList<String> studentsQuizzesPaths,String quizName,CustomDialog dialog) {
		this.studentGradingControllers = studentGradingControllers;
		this.studentsQuizzesPaths = studentsQuizzesPaths;
		this.quizName = quizName;
		this.dialog = dialog;
	}
	@Override
	protected Void doInBackground() throws Exception {
		dialog.setTitle("Sending Mails");
		dialog.setLabelText("Total mails sent successfully: 0/"+studentGradingControllers.size());
		for (int i = 0; i < studentGradingControllers.size(); i++) {
			String studentId = new File(studentsQuizzesPaths.get(i)).getName();
			String zipPath = studentsQuizzesPaths.get(i)+"/"+studentId+".zip";
			zipFileManager.createZipFile(new File(studentsQuizzesPaths.get(i)), zipPath);
			GoogleMail.SendMail(studentGradingControllers.get(i).getStudentEmail(), quizName + " - Graded Quiz",
					zipPath	, quizName + ".zip");
			new File(zipPath).delete();
			dialog.setLabelText("Total mails sent successfully: "+i+"/"+studentGradingControllers.size());
		}
		return null;
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		dialog.dispose();
		JOptionPane.showMessageDialog(null,
				"The graded quizzes were sent successfully to your students' emails.",
				"Emails Delivered Successfully", JOptionPane.INFORMATION_MESSAGE);
	}
}
