package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entities.StudentEntity;
import Views.GradingWindowView;
import Views.ViewPanel;
import project.CustomTable;

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
	public void loadStudentsToTable(ArrayList<StudentEntity> students)
	{
	//	CustomTable studentsTable = new CustomTable(students);
		view.table= new CustomTable(students);
		view.revalidate();
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
