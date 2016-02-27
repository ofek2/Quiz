package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import project.GradingOperation;
import Views.StudentGradingPanel;

public class StudentGradingController {
	public StudentGradingPanel view;
	public StudentGradingController(StudentGradingPanel view)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());
	}
	
	class gradeBtnAddActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GradingOperation gradingOperation = new GradingOperation(view);
		}
		
	}
	
}
