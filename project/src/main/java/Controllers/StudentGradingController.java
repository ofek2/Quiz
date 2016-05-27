package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import Entities.StudentEntity;
import Views.GradingOperation;
import Views.StudentGradingPanel;
import project.HtmlBuilder;
import project.ObjectFileManager;

public class StudentGradingController {
	public StudentGradingPanel view;
	private String studentQuizFolderPath;
	private String originalQuizFormPath;
	private Container previousView;
	private File studentQuizFolder;
	private String studentId;
	private String quizName;
	private StudentEntity result;
	private String quizScore;
	private String studentEmail;
	public StudentGradingController(StudentGradingPanel view, String studentQuizFolderPath,String originalQuizFormPath, Container previousView)
	{
		this.view=view;
		this.view.gradeBtnAddActionListener(new gradeBtnAddActionListener());	
		this.studentQuizFolderPath=studentQuizFolderPath;
		this.originalQuizFormPath = originalQuizFormPath;
		this.previousView = previousView;
		try {
			studentQuizFolder = new File(studentQuizFolderPath);
			studentId= studentQuizFolder.getName();
			quizName = studentQuizFolder.getParentFile().getParentFile().getName();			
			result =(StudentEntity) ObjectFileManager.loadObject(
					studentQuizFolder.getParentFile().getParentFile().
							getParentFile().getParentFile().getCanonicalPath()
							+"/Students/"+studentId+".ser");
			studentEmail=result.getStudentEmail();
			quizScore = result.getScore(quizName);
			updateQuizScoreInView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateQuizScoreInView()
	{

			if(!quizScore.equals("-1"))
			{
				view.getLblGrade().setText(quizScore);
				view.getGradeBtn().setText("Edit");
			}
	}
	class gradeBtnAddActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String studentQuizPath=studentQuizFolderPath+"/"+studentId+".html";
			try {
				if(view.getGradeBtn().getText().equals("Grade"))
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	public String getStudentQuizFolderPath() {
		return studentQuizFolderPath;
	}

	public File getStudentQuizFolder() {
		return studentQuizFolder;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public String getQuizName() {
		return quizName;
	}
	
}
