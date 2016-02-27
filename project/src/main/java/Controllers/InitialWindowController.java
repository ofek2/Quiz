package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import Entities.CourseEntity;
import Entities.QuizEntity;
import Entities.QuizObjectEntity;
import Entities.StudentEntity;
import Views.GradingWindowView;
import Views.InitialWindowView;
import Views.QuizCreationView;

public class InitialWindowController {
	private InitialWindowView view;
	
	/*private JPopupMenu quizPopupMenu;
	private JPopupMenu coursePopupMenu;
	private JPopupMenu rootPopupMenu;
	private JPopupMenu studentsFolderPopupMenu;
	private JPopupMenu studentsFilePopupMenu;*/
	private MenuController menuController;

	public static ArrayList<CourseEntity> coursesFiles;
	private coursesIdsEditAddItemListener idsEditAddItemListener;
	private removeStudentCourseCBAddItemListener removeStudentCourseAddItemListener;
	private removeStudentsIdsCBAddItemListener removeStudentsIdsAddItemListener;
	public JDialog registerStudentDialog;
	public JDialog removeStudentDialog;
	public InitialWindowController(InitialWindowView view) {

		this.view=view;		
		menuController = new MenuController();
		addListeners();	
	}
	private void addListeners()
	{
		
		
		idsEditAddItemListener = new coursesIdsEditAddItemListener();
		removeStudentCourseAddItemListener = new removeStudentCourseCBAddItemListener();
		removeStudentsIdsAddItemListener = new removeStudentsIdsCBAddItemListener();
		view.coursesIdsEditAddItemListener(idsEditAddItemListener);
		view.removeStudentCourseCBAddItemListener(removeStudentCourseAddItemListener);
		view.removeStudentsIdsCBAddItemListener(removeStudentsIdsAddItemListener);
		view.courseIdGradeAddItemListener(new courseIdGradeItemListener());
		view.getTree().addMouseListener(menuController.dialogsBtnsController.popUpMenusController.treeMouseListener());
		
//		remove.addActionListener(l);
	
//		view.getTree().setComponentPopupMenu(jPopupMenu());
	}

	//Initial Window Menu Action Listeners
	class MenuController
	{
		public JDialog newQuizDialog;
		public JDialog editQuizdialog;
		public JDialog newCourseDialog;
		public JDialog removeCourseDialog;
		public JDialog gradeQuizDialog;
		
		private DialogsBtnsController dialogsBtnsController;
		public MenuController()
		{
		
			ActionListener[] quizMngmntListeners = {new NewQuizListener(),new EditQuizListener(),new GradeQuizListener(),new ReportsListener()};
			ActionListener[] courseMngmntListeners ={new AddCourseListener(),new RemoveCourseListener(),new RegisterStudentListener(),new RemoveStudentListener()};
			view.addQuizManagementListeners(quizMngmntListeners);
			view.addCourseManagementListeners(courseMngmntListeners);
			dialogsBtnsController = new DialogsBtnsController();
		}
		class NewQuizListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				newQuizDialog = new JDialog(MainFrameController.view,"New Quiz Dialog");
			
				newQuizDialog.setSize(300,220);
				newQuizDialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				newQuizDialog.setVisible(true);
				newQuizDialog.setResizable(false);
				newQuizDialog.getContentPane().add(view.getNewQuizDialogPanel());
//				view.getNewQuizDialogPanel().setVisible(true);
			}
			
		}
		class EditQuizListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editQuizdialog = new JDialog(MainFrameController.view,"Edit Quiz Dialog");
				
				editQuizdialog.setSize(300,220);
				editQuizdialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				editQuizdialog.setVisible(true);
				editQuizdialog.setResizable(false);
				editQuizdialog.getContentPane().add(view.getEditQuizDialogPanel());
				int courseIndex = view.getCoursesIdsEdit().getSelectedIndex();
				view.loadQuizzesToEditCB(getQuizzesFolder(courseIndex));
				
		
			}
			
		}
		class GradeQuizListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gradeQuizDialog = new JDialog(MainFrameController.view,"Grade Quiz Dialog");
				
				gradeQuizDialog.setSize(300,220);
				gradeQuizDialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				gradeQuizDialog.setVisible(true);
				gradeQuizDialog.setResizable(false);
				gradeQuizDialog.getContentPane().add(view.getGradeQuizDialogPanel());
				
				int courseIndex = view.getCourseIdGradeCB().getSelectedIndex();
				view.loadQuizzesToGradeCB(getQuizzesFolder(courseIndex));
				
				
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
				
				newCourseDialog = new JDialog(MainFrameController.view,"New Course Dialog");
				newCourseDialog.setSize(300,220);
				newCourseDialog.setLocationRelativeTo(MainFrameController.view);
				newCourseDialog.setVisible(true);
				newCourseDialog.setResizable(false);
				newCourseDialog.getContentPane().add(view.getNewCourseDialogPanel());
			}
			
		}
		class RemoveCourseListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeCourseDialog = new JDialog(MainFrameController.view,"Remove Course Dialog");
				removeCourseDialog.setSize(300,150);
				removeCourseDialog.setLocationRelativeTo(MainFrameController.view);
				removeCourseDialog.setVisible(true);
				removeCourseDialog.setResizable(false);
				removeCourseDialog.getContentPane().add(view.getRemoveCourseDialogPanel());
				
			}

		}
		class RegisterStudentListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				registerStudentDialog = new JDialog(MainFrameController.view,"Register Student Dialog");
				registerStudentDialog.setSize(270,300);
				registerStudentDialog.setLocationRelativeTo(MainFrameController.view);
				registerStudentDialog.setVisible(true);
				registerStudentDialog.setResizable(false);
				registerStudentDialog.getContentPane().add(view.getRegisterStudentDialogPanel());
			}
			
		}
		class RemoveStudentListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeStudentDialog = new JDialog(MainFrameController.view,"Remove Student Dialog");
				removeStudentDialog.setSize(270,300);
				removeStudentDialog.setLocationRelativeTo(MainFrameController.view);
				removeStudentDialog.setVisible(true);
				removeStudentDialog.setResizable(false);
				removeStudentDialog.getContentPane().add(view.getRemoveStudentDialogPanel());
				view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
			}
			
		}
		
		
		
		class DialogsBtnsController
		{
			private PopUpMenusController popUpMenusController;
			public DialogsBtnsController()
			{
				view.createQuizBtnAddListener(new CreateQuizBtnListener());
				view.editQuizBtnAddListener(new EditQuizBtnListener());
				view.createCourseBtnAddListener(new CreateCourseBtnListener());
				view.removeCourseBtnAddListener(new RemoveCourseBtnListener());
				view.registerStudentBtnAddListener(new registerStudentBtnListener());
				view.removeStudentBtnAddListener(new removeStudentBtnListener());
				view.gradeQuizBtnAddListener(new gradeQuizBtnListener());
				popUpMenusController= new PopUpMenusController();
			}
			class removeStudentBtnListener implements ActionListener
			{
				private String studentId;
				private String studentCourse;
				private int popUpMenuFlag; 
				private File studentFile;
				public removeStudentBtnListener()
				{
					popUpMenuFlag=0;
				}
				public removeStudentBtnListener(String studentId,String studentCourse)
				{
					this.studentId = studentId;
					this.studentCourse = studentCourse;
					popUpMenuFlag=1;
				}
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						try {
							if(((String)view.getRemoveStudentsIds().getSelectedItem()).equals(""))
								JOptionPane.showMessageDialog(null,
										"This student does not exists,Please choose another one",
										"Alert",JOptionPane.ERROR_MESSAGE);
							else
							{
							if(popUpMenuFlag==1)
							studentFile = new File(new File(".").getCanonicalPath()
											+"/OnlineQuizChecker"+"/"+
											studentCourse+"/Students/"+studentId+".ser");
							if(popUpMenuFlag==0)
								studentFile = new File(new File(".").getCanonicalPath()
										+"/OnlineQuizChecker"+"/"+
										(String)view.getRemoveStudentCourseCB().getSelectedItem()
										+"/Students/"+(String)view.getRemoveStudentsIds().getSelectedItem()
										+".ser");
							studentFile.delete();
							view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsAddItemListener);
							view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
							view.getRemoveStudentsIds().addItemListener(removeStudentsIdsAddItemListener);
							view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
							JOptionPane.showMessageDialog(null, "The student removed successfully");
					//		removeStudentDialog.setVisible(false);///////////////////////		
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				
			}

			class registerStudentBtnListener implements ActionListener
			{
				private int overWrite=JOptionPane.YES_OPTION;
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try{
						if(view.getStudentId().getText().isEmpty())
							JOptionPane.showMessageDialog(null,
									"Please enter the student's id",
									"Alert",JOptionPane.ERROR_MESSAGE);
						else if (Integer.parseInt(view.getStudentId().getText())<=0)
							JOptionPane.showMessageDialog(null,
									"The student's id must be larger from 0",
									"Alert",JOptionPane.ERROR_MESSAGE);
						else if(view.getStudentName().getText().isEmpty())
							JOptionPane.showMessageDialog(null,
									"Please enter the student's name",
									"Alert",JOptionPane.ERROR_MESSAGE);
						else if(view.getStudentEmail().getText().isEmpty())
							JOptionPane.showMessageDialog(null,
									"Please enter the student's email",
									"Alert",JOptionPane.ERROR_MESSAGE);
						else{
						///check if the email is correct
						
						String courseName = (String) view.getRegisterStudentCourseCB().getSelectedItem();
						int courseIndexInCoursesArray = CourseEntity.getIndex(courseName);
						String coursePath = coursesFiles.get(courseIndexInCoursesArray).getCourseFolder().getCanonicalPath();
						String studentId = view.getStudentId().getText();
						if(new File(coursePath + "/Students/" + studentId +".ser").exists())
							overWrite = JOptionPane.showConfirmDialog(null,
									"This student is already exists and his data will be overwritten, \n do you want to keep the application progress?"
									,"Alert",JOptionPane.YES_NO_OPTION);
						if(overWrite==JOptionPane.YES_OPTION)
							{
							StudentEntity studentEntity = new StudentEntity(courseName,
									studentId,
									view.getStudentName().getText(),
									view.getStudentEmail().getText());
							FileOutputStream fos = new FileOutputStream(
									coursePath + "/Students/" + studentId +".ser");
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(studentEntity);
							oos.close();
							JOptionPane.showMessageDialog(null, "The student registered successfully");
							view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
							registerStudentDialog.setVisible(false);///////////////////////					
							view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsAddItemListener);
							view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
							view.getRemoveStudentsIds().addItemListener(removeStudentsIdsAddItemListener);
							}
						else if(overWrite==JOptionPane.NO_OPTION||overWrite==JOptionPane.CLOSED_OPTION)
							;
						}
					}
					catch(NumberFormatException e1) { 
						JOptionPane.showMessageDialog(null,
								"Please enter integers only in the student's id",
								"Alert",JOptionPane.ERROR_MESSAGE);
				    }
					catch(NullPointerException e2) {
						JOptionPane.showMessageDialog(null,
								"Please enter integers only in the student id",
								"Alert",JOptionPane.ERROR_MESSAGE);
				    } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}

			class CreateCourseBtnListener implements ActionListener
				{
					private File courseFolder;
					private String courseId;
					private String courseName;
					private CourseEntity courseEntity;
					private File studentsFolder;
					private File quizzesFolder;
					
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
								studentsFolder = new File(courseFolder.getCanonicalPath()+"/Students");
								quizzesFolder = new File(courseFolder.getCanonicalPath()+"/Quizzes");
								courseEntity = new CourseEntity(courseFolder, courseId, courseName);
								if(!courseEntity.CourseExist())
								{
									courseFolder.mkdir();			
									coursesFiles.add(courseEntity.checkPosition(),courseEntity);
									studentsFolder.mkdir();
									quizzesFolder.mkdir();
									coursesUpdate();
			//						newCourseDialog.setVisible(false);
			
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

			public class EditQuizBtnListener implements ActionListener
			{
				private String quizName;
				private int popUpMenuFlag;
				
				public EditQuizBtnListener()
				{
					popUpMenuFlag=0;
				}
				public EditQuizBtnListener(String quizName)
				{
					this.quizName = quizName;
					popUpMenuFlag=1;
				}	
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// read object from file
					if(popUpMenuFlag==0)
						quizName = view.getQuizzes().getItemAt(view.getQuizzes().getSelectedIndex());
					String quizFile = quizName+".ser";
					String path;
					try {
						path = coursesFiles.get(view.getCoursesIdsEdit().getSelectedIndex()).getCourseFolder().getCanonicalPath()+"/Quizzes/"+quizName+"/"+quizFile;
						FileInputStream fis;
						fis = new FileInputStream(path);
						ObjectInputStream ois = new ObjectInputStream(fis);
						QuizObjectEntity result = (QuizObjectEntity) ois.readObject();
						ois.close();		
						QuizCreationView quizCreationView = new QuizCreationView();
						QuizCreationController quizCreationController = new QuizCreationController(quizCreationView,result,view);			
						quizCreationView.getQuizName().setText(quizName);
						QuizCreationController.saveFlag=1;
						if(popUpMenuFlag==0)
						menuController.editQuizdialog.setVisible(false);
						MainFrameController.view.changeContentPane(quizCreationView);			
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}

			class CreateQuizBtnListener implements ActionListener
			{
					private String quizName;
					private String courseName;
			
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						quizName = view.getNewQuizName().getText();
						courseName = (String)view.getCoursesIds().getSelectedItem();
						createNewQuiz(quizName, courseName);
						menuController.newQuizDialog.setVisible(false);
					}
			}
			public void createNewQuiz(String quizName,String courseName)
			{
				File quizFolder;
				File quizFormFolder;
				File studentsFilesFolder;
				
				if(quizName.isEmpty())
					JOptionPane.showMessageDialog(null,"This quiz name is empty, please choose another name","Alert",JOptionPane.ERROR_MESSAGE);
				else{
					try {
						quizFolder = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker/"+courseName+"/Quizzes/"+quizName);
						if(!quizFolder.exists())
						{
							quizFormFolder = new File(quizFolder.getCanonicalPath()+"/Form");
							studentsFilesFolder = new File(quizFolder.getCanonicalPath()+"/StudentsAnswers");
							QuizCreationView quizCreationView = new QuizCreationView();
							QuizEntity quizEntity = new QuizEntity(quizName,quizFolder,quizFormFolder,studentsFilesFolder,25);
							new QuizCreationController(quizCreationView,quizEntity,view);
							quizCreationView.getQuizName().setText(quizName);
							
							MainFrameController.view.changeContentPane(quizCreationView);
							QuizCreationController.saveFlag=1;
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
			class RemoveCourseBtnListener implements ActionListener
			{
				private String courseName;
				private int popUpMenuFlag;
				private File courseFolder;
				public RemoveCourseBtnListener()
				{
					popUpMenuFlag=0;
				}
				public RemoveCourseBtnListener(String courseName)
				{
					this.courseName = courseName;
					popUpMenuFlag=1;
				}	
				
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						if(popUpMenuFlag==0)
						{
							courseFolder = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker/"+(String)view.getRemoveCourses().getSelectedItem());
							coursesFiles.remove(view.getRemoveCourses().getSelectedIndex());
						}
						else
						{
							courseFolder = new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker/"+courseName);
							coursesFiles.remove(CourseEntity.getIndex(courseName));
						}
						removeFolder(courseFolder);
						coursesUpdate();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
			}

			class gradeQuizBtnListener implements ActionListener
			{
			
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					GradingWindowView gradingWindowView = new GradingWindowView();
					GradingWindowController gradingWindowController = new GradingWindowController(gradingWindowView);
					MainFrameController.view.changeContentPane(gradingWindowView);
					gradingWindowController.setPreviousView(view);
				}
				
			}
			class PopUpMenusController
			{
				private JPopupMenu newQuizPopupMenu;
				private JPopupMenu quizPopupMenu;
				private JPopupMenu coursePopupMenu;
				private JPopupMenu rootPopupMenu;
				private JPopupMenu studentsFolderPopupMenu;
				private JPopupMenu studentsFilePopupMenu;
			
				
				private JMenuItem createQuiz;
				private JMenuItem editQuiz;
				private JMenuItem removeQuiz;
				private JMenuItem removeCourse;
				private JMenuItem addCourse;
				private JMenuItem registerStudent;
				private JMenuItem removeStudent;
				
				public PopUpMenusController()
				{
					newQuizPopupMenu = new JPopupMenu();
					quizPopupMenu= new JPopupMenu();
					coursePopupMenu= new JPopupMenu();
					rootPopupMenu= new JPopupMenu();
					studentsFolderPopupMenu = new JPopupMenu();
					studentsFilePopupMenu = new JPopupMenu();
					
					createQuiz= new JMenuItem("create quiz");
					editQuiz = new JMenuItem("edit quiz");
					removeQuiz = new JMenuItem("remove quiz");
					removeCourse = new JMenuItem("remove course");
					addCourse = new JMenuItem("add course");
					registerStudent = new JMenuItem("register student");
					removeStudent = new JMenuItem("remove student");
				}
				public MouseAdapter treeMouseListener()
				{
					MouseAdapter adapter;
					return adapter = new MouseAdapter(){
						public void mousePressed(MouseEvent e) {
							if(e.getButton() == MouseEvent.BUTTON3)
							{
				                TreePath pathForLocation = view.getTree().getPathForLocation(e.getPoint().x, e.getPoint().y);
				                if(pathForLocation != null){
				                	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) pathForLocation.getLastPathComponent();
				                	String chosenFileName=pathForLocation.getLastPathComponent().toString();
				                	pathForLocation.getLastPathComponent();
				                	if(chosenFileName.equals("OnlineQuizChecker"))
				                    view.getTree().setComponentPopupMenu(rootPopupMenu());   
				                	else if(selectedNode.getParent().toString().equals("OnlineQuizChecker"))
					                    view.getTree().setComponentPopupMenu(removeCoursePopupMenu(chosenFileName)); 
				                	else if(selectedNode.getParent().getParent().toString().equals("OnlineQuizChecker"))
				                	{
				                		if(chosenFileName.equals("Students"))
				                			 view.getTree().setComponentPopupMenu(registerStudentPopupMenu());
				                		else if(chosenFileName.equals("Quizzes"))
				                			view.getTree().setComponentPopupMenu(newQuizPopupMenu(selectedNode.getParent().toString()));
					                    
				                	}
				                	else if(selectedNode.getParent().toString().equals("Students"))
				                		view.getTree().setComponentPopupMenu(removeStudentPopupMenu(chosenFileName
				                				,selectedNode.getParent().getParent().toString()));
				                	else if(selectedNode.getParent().toString().equals("Quizzes"))
				                		view.getTree().setComponentPopupMenu(quizPopupMenu(chosenFileName,selectedNode.getParent().toString())); 
				                } else
				                	view.getTree().setComponentPopupMenu(null);

				            }
						}
					};
					
				}
				private JPopupMenu quizPopupMenu(String chosenFileName,String courseName)
				{
				
					quizPopupMenu.remove(editQuiz);
				    quizPopupMenu.remove(removeQuiz);
					editQuiz = new JMenuItem("edit quiz"); 
					removeQuiz = new JMenuItem("remove quiz"); 
					
					editQuiz.addActionListener(new DialogsBtnsController.EditQuizBtnListener(chosenFileName));
					final String quizName = chosenFileName;
					final String quizCourseName = courseName;
					removeQuiz.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								removeFolder(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+quizCourseName+"/"+quizName));
								view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					quizPopupMenu.add(editQuiz);
					quizPopupMenu.add(removeQuiz);
					return quizPopupMenu;
				}
				private JPopupMenu removeCoursePopupMenu(String chosenFileName)
				{
					coursePopupMenu.remove(removeCourse);
					removeCourse = new JMenuItem("remove course"); 	
					removeCourse.addActionListener(new RemoveCourseBtnListener(chosenFileName));
					coursePopupMenu.add(removeCourse);
					return coursePopupMenu;
				}
				private JPopupMenu rootPopupMenu()
				{
					rootPopupMenu.add(addCourse);
					addCourse.addActionListener(new AddCourseListener());
					return rootPopupMenu;
				}
				private JPopupMenu registerStudentPopupMenu()
				{
					studentsFolderPopupMenu.add(registerStudent);
					return studentsFolderPopupMenu;
				}
				private JPopupMenu removeStudentPopupMenu(String chosenFileName,String studentCourse)
				{
					studentsFilePopupMenu.remove(removeStudent);
					removeStudent = new JMenuItem("remove student"); 	
					removeStudent.addActionListener(new removeStudentBtnListener(chosenFileName,studentCourse));
					studentsFilePopupMenu.add(removeStudent);
					return studentsFilePopupMenu;
				}
				private JPopupMenu newQuizPopupMenu(String courseName)
				{
					final String courseN= courseName;
					newQuizPopupMenu.remove(createQuiz);
					createQuiz = new JMenuItem("create quiz");
					createQuiz.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String quizName = (String)JOptionPane.showInputDialog(
				                    MainFrameController.view,
				                    "Creating new quiz in course : "+courseN+"\n"+
				                    "Please, choose quiz name:",
				                    "New Quiz Dialog",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    null,
				                    "");
							createNewQuiz(quizName, courseN);
							
						}
					});
					newQuizPopupMenu.add(createQuiz);
					return newQuizPopupMenu;
				}
			}
			public PopUpMenusController getPopUpMenusController() {
				return popUpMenusController;
			}
			
		
		}
	}
	
	class removeStudentCourseCBAddItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox temp = (JComboBox)e.getSource();
			view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsAddItemListener);
			view.loadStudents(temp.getSelectedIndex());
			view.getRemoveStudentsIds().addItemListener(removeStudentsIdsAddItemListener);
		}
		
	}
	
	class removeStudentsIdsCBAddItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox temp = (JComboBox)e.getSource();
			try {
				view.loadStudentNameToRemoveLbl(coursesFiles.
						get(view.getRemoveStudentCourseCB().getSelectedIndex()).
						getCourseFolder().getCanonicalPath()
						+"/Students/"+(String) temp.getSelectedItem()+".ser");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class coursesIdsEditAddItemListener implements ItemListener
	{
	
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox temp = (JComboBox)e.getSource();
			
			view.loadQuizzesToEditCB(getQuizzesFolder(temp.getSelectedIndex()));
		}
		
	}
	class courseIdGradeItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox temp = (JComboBox)e.getSource();
			
			view.loadQuizzesToGradeCB(getQuizzesFolder(temp.getSelectedIndex()));
		}
		
	}
	public void removeFolder(File file)
	{
		if(file.isDirectory())
			for(File child: file.listFiles())
				removeFolder(child);
		file.delete();
	}
	public File[] getQuizzesFolder(int selectedIndex) {

		File [] courseFiles = coursesFiles.get(selectedIndex).getCourseFolder().listFiles();
		for(int i=0;i<courseFiles.length;i++)
			if(courseFiles[i].getName().equals("Quizzes"))
				return courseFiles[i].listFiles();
		return null;
	}
	public void coursesUpdate()
	{
		view.getCoursesIds().removeAllItems();
		view.getRemoveCourses().removeAllItems();
		view.getCoursesIdsEdit().removeItemListener(idsEditAddItemListener);
		view.getCoursesIdsEdit().removeAllItems();
		view.coursesIdsEditAddItemListener(idsEditAddItemListener);
		//
		view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsAddItemListener);
		view.getRemoveStudentCourseCB().removeItemListener(removeStudentCourseAddItemListener);
		view.getRemoveStudentCourseCB().removeAllItems();		
		//
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
		{	String item = InitialWindowController.coursesFiles.get(i).getCourseFolderName();
			view.getCoursesIds().addItem(item);		
			view.getRemoveCourses().addItem(item);
			view.getCoursesIdsEdit().addItem(item);
			view.getRemoveStudentCourseCB().addItem(item);
		}
		view.getRemoveStudentsIds().addItemListener(removeStudentsIdsAddItemListener);
		view.getRemoveStudentCourseCB().addItemListener(removeStudentCourseAddItemListener);
		try {
			view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*public void coursesUpdate(){
		view.getCoursesIdsEdit().removeItemListener(idsEditAddItemListener);
		view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsAddItemListener);
		view.getRemoveStudentCourseCB().removeItemListener(removeStudentCourseAddItemListener);
		
		view.coursesIdsEditAddItemListener(idsEditAddItemListener);
		view.getRemoveStudentsIds().addItemListener(removeStudentsIdsAddItemListener);
		view.getRemoveStudentCourseCB().addItemListener(removeStudentCourseAddItemListener);
		
		Vector<String> coursesIds = new Vector<String>();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			coursesIds.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(coursesIds);
		view.setComboBoxesModel(model);
		
		try {
			view.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/*public void loadQuizzesToComboBox(int courseIndex)
	{
		
		view.quizzes.removeAllItems();
		for(File child: coursesFiles.get(courseIndex).getCourseFolder().listFiles())
			view.quizzes.addItem(child.getName());
		view.getEditQuizDialogPanel().revalidate();
	}*/
	
}
