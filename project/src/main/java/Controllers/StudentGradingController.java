package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import project.GradingOperation;
import project.HtmlBuilder;
import project.ObjectFileManager;
import Entities.StudentEntity;
import Views.StudentGradingPanel;
import Views.ViewPanel;

public class StudentGradingController {
	public StudentGradingPanel view;
	private String studentQuizPath;
	private String originalQuizFormPath;
	private Container previousView;
	private File studentQuizFile;
	private String studentId;
	private String quizName;
	private StudentEntity result;
	private String quizScore;
	public StudentGradingController(StudentGradingPanel view, String studentQuizPath,String originalQuizFormPath, Container previousView)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());	
		this.studentQuizPath=studentQuizPath;
		this.originalQuizFormPath = originalQuizFormPath;
		this.previousView = previousView;
		try {
			studentQuizFile = new File(studentQuizPath);
			studentId= (String) studentQuizFile.getName().
					subSequence(0,studentQuizFile.getName().length()-5);
			quizName = studentQuizFile.getParentFile().getParentFile().getName();			
			result =(StudentEntity) ObjectFileManager.loadObject(
							studentQuizFile.getParentFile().getParentFile().
							getParentFile().getParentFile().getCanonicalPath()
							+"/Students/"+studentId+".ser");
			quizScore = result.getScore(quizName);
			updateQuizScoreInView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updateQuizScoreInView()
	{
		System.out.println(quizScore);
			if(!quizScore.equals("-1"))
				view.getLblGrade().setText(quizScore);
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
