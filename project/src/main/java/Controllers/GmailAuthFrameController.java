package Controllers;

import java.util.ArrayList;

import Views.GmailAuthFrame;
import project.GoogleMail;

public class GmailAuthFrameController {
	private GmailAuthFrame view;

	public GmailAuthFrameController(GmailAuthFrame view,ArrayList<StudentGradingController> studentGradingControllers,ArrayList<String> studentsQuizzesPaths) {
		this.view = view;
		String quizName="";
		GoogleMail mail = new GoogleMail();
		if(!studentGradingControllers.isEmpty())
			quizName= studentGradingControllers.get(0).getQuizName();
		for(int i=0;i<studentGradingControllers.size();i++)
		{
			mail.SendMail(studentGradingControllers.get(i).getStudentEmail(), 
					quizName+" - Graded Quiz", studentsQuizzesPaths.get(i), quizName);
		}
		
	}
	
}
