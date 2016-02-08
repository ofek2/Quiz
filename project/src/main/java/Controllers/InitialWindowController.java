package Controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.qPanelController.removeBtnListener;
import Entities.QuizEntity;
import Views.GradingWindowView;
import Views.InitialWindowView;
import Views.Main;
import Views.MainFrameView;
import Views.QuizCreationView;

public class InitialWindowController {
	private InitialWindowView view;
	private File quizFile;	
	private String courseCode;
	public InitialWindowController(InitialWindowView view) {
		this.view=view;
		addListeners();
		
	}
	private void addListeners()
	{
		ActionListener[] quizMngmntListeners = {new NewQuizListener(),new EditQuizListener(),new GradeQuizListener(),new ReportsListener()};
		ActionListener[] courseMngmntListeners ={new AddCourseListener(),new RemoveCourseListener(),new RegisterStudentListener(),new RemoveStudentListener()};
		view.addQuizManagementListeners(quizMngmntListeners);
		view.addCourseManagementListeners(courseMngmntListeners);
		view.addCourseCodeListener(new CourseCodeListener());
		view.createCourseBtnAddListener(new CreateCourseBtnListener());
	}
	class CourseCodeListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			courseCode = (String) e.getItem();
		}
		
	}
	
	class CreateCourseBtnListener implements ActionListener
	{
		private String quizName;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			quizName = view.getNewQuizName().getText();
			try {
				quizFile = new File(new File(".").getCanonicalPath()+"/"+courseCode+"/"+quizName);
				if(!quizFile.exists())
				{
					quizFile.mkdir();
					QuizCreationView quizCreationView = new QuizCreationView();
					QuizEntity quizEntity = new QuizEntity("QuizTest",25,quizFile);
					new QuizCreationController(quizCreationView,quizEntity);
					MainFrameController.view.changeContentPane(quizCreationView);
				}
				else
				{
					
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (NullPointerException e1) {//quiz name is empty
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}

			
		}
		
	}
	
	
	class NewQuizListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			JDialog dialog = new JDialog(new JFrame());
			dialog.setLocationRelativeTo(MainFrameController.view);
			dialog.setSize(220,220);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.getContentPane().add(view.getNewQuizDialogPanel());
//			view.getNewQuizDialogPanel().setVisible(true);
		}
		
	}
	class EditQuizListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCreationView quizCreationView = new QuizCreationView();
//			quizFile="the chosen quiz";
			QuizEntity quizEntity = new QuizEntity("QuizTest",25,quizFile);
			QuizCreationController quizCreationController = new QuizCreationController(quizCreationView,quizEntity);
			MainFrameController.view.changeContentPane(quizCreationView);
			//quizCreationController.setBack()
		}
		
	}
	class GradeQuizListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GradingWindowView gradingWindowView = new GradingWindowView();
			GradingWindowController gradingWindowController = new GradingWindowController(gradingWindowView);
			MainFrameController.view.changeContentPane(gradingWindowView);
			gradingWindowController.setPreviousView(view);
		}
		
	}
	class ReportsListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class AddCourseListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class RemoveCourseListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class RegisterStudentListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class RemoveStudentListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
