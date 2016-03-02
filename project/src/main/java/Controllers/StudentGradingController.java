package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import project.GradingOperation;
import project.HtmlBuilder;
import Views.StudentGradingPanel;

public class StudentGradingController {
	public StudentGradingPanel view;
	private String studentQuizPath;
	private String originalQuizFormPath;
	private Container previousView;
	public StudentGradingController(StudentGradingPanel view, String studentQuizPath,String originalQuizFormPath, Container previousView)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());
		this.studentQuizPath=studentQuizPath;
		this.originalQuizFormPath = originalQuizFormPath;
		this.previousView = previousView;
	}
	
	class gradeBtnAddActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				if(view.getLblGrade().getText().equals(view.notGraded))
				{
					HtmlBuilder htmlBuilder = new HtmlBuilder();
					htmlBuilder.prepareQuizForGrading(studentQuizPath,originalQuizFormPath);
				}
				GradingOperation gradingOperation = new GradingOperation(view,studentQuizPath,previousView);
				MainFrameController.view.changeContentPane(gradingOperation);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
}
