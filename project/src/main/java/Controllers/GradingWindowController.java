package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entities.QuizEntity;
import Entities.StudentEntity;
import Entities.StudentQuizEntity;
import Views.GradingWindowView;
import Views.StudentGradingPanel;
import Views.ViewPanel;


public class GradingWindowController {
	private GradingWindowView view;

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
		for(int i=0;i<students.size();i++)
		{
			StudentGradingPanel sview = new StudentGradingPanel(students.get(i));
			StudentGradingController scontrol = new StudentGradingController(sview
					,studentsQuizzesPaths.get(i),originalQuizFormPath,view);
			view.tablePanel.add(sview);
		
		}
		view.tablePanel.revalidate();
	}
	class SendListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
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
