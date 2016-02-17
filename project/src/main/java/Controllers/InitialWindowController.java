package Controllers;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

import Controllers.qPanelController.removeBtnListener;
import Entities.Constants;
import Entities.CourseEntity;
import Entities.QuizEntity;
import Views.GradingWindowView;
import Views.InitialWindowView;
import Views.Main;
import Views.MainFrameView;
import Views.QuizCreationView;
import javafx.scene.control.ComboBox;

public class InitialWindowController {
	private InitialWindowView view;
	private JDialog dialog;
	public static ArrayList<CourseEntity> coursesFiles;
	public InitialWindowController(InitialWindowView view) {
//		coursesFiles = new ArrayList<File>();
		this.view=view;
		addListeners();		
	}
	private void addListeners()
	{
		ActionListener[] quizMngmntListeners = {new NewQuizListener(),new EditQuizListener(),new GradeQuizListener(),new ReportsListener()};
		ActionListener[] courseMngmntListeners ={new AddCourseListener(),new RemoveCourseListener(),new RegisterStudentListener(),new RemoveStudentListener()};
		view.addQuizManagementListeners(quizMngmntListeners);
		view.addCourseManagementListeners(courseMngmntListeners);
		view.createQuizBtnAddListener(new CreateQuizBtnListener());
		view.editQuizBtnAddListener(new EditQuizBtnListener());
		view.createCourseBtnAddListener(new CreateCourseBtnListener());
		view.removeCourseBtnAddListener(new RemoveCourseBtnListener());
		view.coursesIdsEditAddItemListener(new coursesIdsEditAddItemListener());
	}

	
	class CreateCourseBtnListener implements ActionListener
	{
		private File courseFolder;
		private String courseId;
		private String courseName;
		private CourseEntity courseEntity;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			courseName = view.getNewCourseName().getText();
			courseId = view.getNewCourseId().getText();
			if(courseId.isEmpty())
				JOptionPane.showMessageDialog(null,"This course id is empty, please choose another name","Alert",JOptionPane.ERROR_MESSAGE);
			else if(courseName.isEmpty())
				JOptionPane.showMessageDialog(null,"This course name is empty, please choose another name","Alert",JOptionPane.ERROR_MESSAGE);
			else{
				try {
					courseFolder = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+CourseEntity.getCourseFolderName(courseId, courseName));
					courseEntity = new CourseEntity(courseFolder, courseId, courseName);
					if(!courseEntity.CourseExist())
					{
						courseFolder.mkdir();			
						coursesFiles.add(courseEntity.checkPosition(),courseEntity);
						coursesUpdate();
						dialog.setVisible(false);

					}
					else
					{
						JOptionPane.showMessageDialog(null,"This course already exists, please choose another id","Alert",JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
	}
	class coursesIdsEditAddItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox temp = (JComboBox)e.getSource();
			loadQuizzesToComboBox(temp.getSelectedIndex());
		}
		
	}
	class EditQuizBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class CreateQuizBtnListener implements ActionListener
	{
		private String quizName;
		private File quizFolder;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			quizName = view.getNewQuizName().getText();
		if(quizName.isEmpty())
				JOptionPane.showMessageDialog(null,"This quiz name is empty, please choose another name","Alert",JOptionPane.ERROR_MESSAGE);
		else{
			try {
				quizFolder = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+(String)view.getCoursesIds().getSelectedItem()+"/"+quizName);
				if(!quizFolder.exists())
				{
					quizFolder.mkdir();
			
					QuizCreationView quizCreationView = new QuizCreationView();
					QuizEntity quizEntity = new QuizEntity("QuizTest",25,quizFolder);
					new QuizCreationController(quizCreationView,quizEntity,view);
					quizCreationView.getQuizName().setText(quizName);
					dialog.setVisible(false);
					MainFrameController.view.changeContentPane(quizCreationView);
//					view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
				}
				else
				{
					
					JOptionPane.showMessageDialog(null,"This quiz name already exists, please choose another name","Alert",JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			
			}
		}
	}
	
	class RemoveCourseBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				File file = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker/"+(String)view.getRemoveCourses().getSelectedItem());
				removeFolder(file);
				coursesFiles.remove(view.getRemoveCourses().getSelectedIndex());
				coursesUpdate();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	
	class NewQuizListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			dialog = new JDialog(MainFrameController.view,"New Quiz Dialog");
		
			dialog.setSize(300,220);
			dialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
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
			dialog = new JDialog(MainFrameController.view,"Edit Quiz Dialog");
			
			dialog.setSize(300,220);
			dialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.getContentPane().add(view.getEditQuizDialogPanel());
			
			loadQuizzesToComboBox(view.getCoursesIdsEdit().getSelectedIndex());
			
			// read object from file
			/*			FileInputStream fis = new FileInputStream("mybean.ser");
						ObjectInputStream ois = new ObjectInputStream(fis);
						MyBean result = (MyBean) ois.readObject();
						ois.close();

			QuizCreationView quizCreationView = new QuizCreationView();
//			quizFile="the chosen quiz";
			QuizEntity quizEntity = new QuizEntity("QuizTest",25,quizFolder);
			QuizCreationController quizCreationController = new QuizCreationController(quizCreationView,quizEntity);
			MainFrameController.view.changeContentPane(quizCreationView);
			//quizCreationController.setBack()*/
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
			dialog = new JDialog(MainFrameController.view,"New Course Dialog");
			dialog.setSize(220,220);
			dialog.setLocationRelativeTo(MainFrameController.view);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.getContentPane().add(view.getNewCourseDialogPanel());
		}
		
	}
	class RemoveCourseListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dialog = new JDialog(MainFrameController.view,"Remove Course Dialog");
			dialog.setLocationRelativeTo(MainFrameController.view);
			dialog.setSize(220,220);
			dialog.setVisible(true);
			dialog.setResizable(false);
			dialog.getContentPane().add(view.getRemoveCourseDialogPanel());
			
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
	public void removeFolder(File file)
	{
		if(file.isDirectory())
			for(File child: file.listFiles())
				removeFolder(child);
		file.delete();
	}
	public void coursesUpdate()
	{
		view.getCoursesIds().removeAllItems();
		view.getRemoveCourses().removeAllItems();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
		{	
			view.getCoursesIds().addItem(InitialWindowController.coursesFiles.get(i).getCourseFolderName());		
			view.getRemoveCourses().addItem(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		}
		try {
			view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loadQuizzesToComboBox(int courseIndex)
	{
		
		view.quizzes.removeAllItems();
		System.out.println(coursesFiles.get(courseIndex).getCourseFolder().getName());
		for(File child: coursesFiles.get(courseIndex).getCourseFolder().listFiles())
		{
			System.out.println("add");
			view.quizzes.addItem(child.getName());
		}
		view.getEditQuizDialogPanel().revalidate();
	}
	
}
