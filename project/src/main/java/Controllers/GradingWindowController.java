package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.ObjectFileManager;
import Entities.QuizEntity;
import Entities.StudentEntity;
import Entities.StudentQuizEntity;
import Views.GradingWindowView;
import Views.StudentGradingPanel;
import Views.ViewPanel;


public class GradingWindowController {
	private GradingWindowView view;
	private ArrayList<String> students;
	private ArrayList<StudentGradingPanel> studentGradingPanel;
	private ArrayList<String> studentsQuizzesPaths;
	private Container previousView;
	public GradingWindowController(GradingWindowView view) {
		this.view = view;
	
		addListeners();
	}
	public Container getPreviousView() {
		return previousView;
	}
	public void setPreviousView(Container previousView) {
		this.previousView = previousView;
	}
	private void addListeners()
	{
		ActionListener[] fileListeners = {new SendListener(),new ExitListener()};
		view.addFileListeners(fileListeners);
		
	}

	public void loadStudentsToTable(ArrayList<String> students, ArrayList<String> studentsQuizzesPaths,String originalQuizFormPath)
	{
		this.students = students;
		studentGradingPanel = new ArrayList<StudentGradingPanel>();
		this.studentsQuizzesPaths = studentsQuizzesPaths;
		for(int i=0;i<students.size();i++)
		{
			StudentGradingPanel sview = new StudentGradingPanel(students.get(i));
			studentGradingPanel.add(sview);
			StudentGradingController scontrol = new StudentGradingController(sview
					,studentsQuizzesPaths.get(i),originalQuizFormPath,view);
			view.tablePanel.add(sview);
		
		}
		view.tablePanel.revalidate();
	}
	class SendListener implements ActionListener
	{
		private boolean allChecked = true;
		private File studentQuizFile;
		private String studentId;
		private StudentEntity result;
		private String studentEmail;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < studentGradingPanel.size(); i++) {
				if (studentGradingPanel.get(i).getGradeBtn().getText().equals("Grade")) {
					allChecked = false;
				}
			}
			if (allChecked) {
				for (int i = 0; i < studentsQuizzesPaths.size(); i++) {
					studentQuizFile = new File(studentsQuizzesPaths.get(i));
					studentId = (String) studentQuizFile.getName().
							subSequence(0,studentQuizFile.getName().length()-5);
					try {
						result = (StudentEntity) ObjectFileManager.loadObject(
								studentQuizFile.getParentFile().getParentFile().
								getParentFile().getParentFile().getCanonicalPath()
								+"/Students/"+studentId+".ser");
						studentEmail = result.getStudentEmail();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			else
				JOptionPane.showMessageDialog(null
						, "You must check all of the quizzes before sending the graded quizzes"
						, "Alert",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	class ExitListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel)previousView);
		}
		
	}

	
}
