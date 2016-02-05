package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Entities.QuizEntity;
import Views.GradingWindowView;
import Views.InitialWindowView;
import Views.Main;
import Views.MainFrameView;
import Views.QuizCreationView;

public class InitialWindowController {
	private InitialWindowView view;

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
	}
	
	class NewQuizListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCreationView quizCreationView = new QuizCreationView();
			QuizEntity quizEntity = new QuizEntity("QuizTest",25);
			new QuizCreationController(quizCreationView,quizEntity);
		MainFrameController.view.changeContentPane(quizCreationView);
		}
		
	}
	class EditQuizListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCreationView quizCreationView = new QuizCreationView();
			QuizEntity quizEntity = new QuizEntity("QuizTest",25);
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
