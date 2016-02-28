package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import project.GradingOperation;
import Views.StudentGradingPanel;

public class StudentGradingController {
	public StudentGradingPanel view;
	private String studentQuizPath;
	public StudentGradingController(StudentGradingPanel view, String studentQuizPath)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());
		this.studentQuizPath=studentQuizPath;
	}
	
	class gradeBtnAddActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			GradingOperation gradingOperation = new GradingOperation(view,studentQuizPath);
			MainFrameController.view.changeContentPane(gradingOperation);
		}
		
	}
	
}
