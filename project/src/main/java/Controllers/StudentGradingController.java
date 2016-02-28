package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import project.GradingOperation;
import Views.StudentGradingPanel;

public class StudentGradingController {
	public StudentGradingPanel view;
	private String studentQuizPath;
	private Container previousView;
	public StudentGradingController(StudentGradingPanel view, String studentQuizPath, Container previousView)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());
		this.studentQuizPath=studentQuizPath;
		this.previousView = previousView;
	}
	
	class gradeBtnAddActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			GradingOperation gradingOperation = new GradingOperation(view,studentQuizPath,previousView);
			MainFrameController.view.changeContentPane(gradingOperation);
		}
		
	}
	
}
