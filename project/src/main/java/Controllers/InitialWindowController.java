package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import com.google.common.io.Files;
import Entities.Constants;
import Entities.CourseEntity;
import Entities.QuizEntity;
import Entities.QuizObjectEntity;
import Entities.StudentEntity;
import Utilities.ObjectFileManager;
import Views.CoursesCheckingFrame;
import Views.CustomDialog;
import Views.GradingWindowView;
import Views.HelpFrame;
import Views.InitialWindowView;
import Views.MainFrameView;
import Views.QuizCreationView;
import Views.ReportsView;

/**
 * The Class InitialWindowController.
 * This class controls the {@link InitialWindowView} events.
 * This class is used for managing the user's (a lecturer) courses, quizzes, students and students' grades.
 */
public class InitialWindowController {
	
	/** The view. */
	private InitialWindowView view;

	/** The menu controller. */
	private MenuController menuController;
	
	/** The courses files. */
	public static ArrayList<CourseEntity> coursesFiles;
	
	/** The ids edit add item listener. */
	private coursesIdsEditItemListener idsEditItemListener;
	
	/** The remove student course add item listener. */
	private removeStudentCourseCBItemListener removeStudentCourseItemListener;
	
	/** The remove students ids add item listener. */
	private removeStudentsIdsCBItemListener removeStudentsIdsItemListener;
	
	/** The course id grade item listener. */
	private courseIdGradeItemListener courseIdGradeItemListener;
	
	/** The register student dialog. */
	public JDialog registerStudentDialog;
	
	/** The remove student dialog. */
	public JDialog removeStudentDialog;
	
	/** The window listener. */
	public static windowListener windowListener;
	
	/** The editing previous student id. */
	public String editingPreviousStudentId = "";

	/**
	 * Instantiates a new initial window controller.
	 *
	 * @param view the view
	 */
	public InitialWindowController(InitialWindowView view) {

		this.view = view;
		menuController = new MenuController();
		windowListener = new windowListener();
		MainFrameController.view.removeWindowListener(MainFrameView.windowListener);
		MainFrameController.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MainFrameController.view.addWindowListener(windowListener);
		addListeners();
	}

	/**
	 * Adds the listeners.
	 */
	private void addListeners() {

		idsEditItemListener = new coursesIdsEditItemListener();
		removeStudentCourseItemListener = new removeStudentCourseCBItemListener();
		removeStudentsIdsItemListener = new removeStudentsIdsCBItemListener();
		courseIdGradeItemListener = new courseIdGradeItemListener();
		view.coursesIdsEditAddItemListener(idsEditItemListener);
		view.removeStudentCourseCBAddItemListener(removeStudentCourseItemListener);
		view.removeStudentsIdsCBAddItemListener(removeStudentsIdsItemListener);
		view.courseIdGradeAddItemListener(courseIdGradeItemListener);
		view.getTree().addMouseListener(menuController.dialogsBtnsController.popUpMenusController.treeMouseListener());

	}

	/**
	 * The Class MenuController.
	 * This class controls the events of the menu bar in InitialWindowView.
	 */
	// Initial Window Menu Action Listeners
	class MenuController {
		
		/** The new quiz dialog. */
		public JDialog newQuizDialog;
		
		/** The edit quizdialog. */
		public JDialog editQuizdialog;
		
		/** The new course dialog. */
		public JDialog newCourseDialog;
		
		/** The remove course dialog. */
		public JDialog removeCourseDialog;
		
		/** The grade quiz dialog. */
		public JDialog gradeQuizDialog;

		/** The dialogs btns controller. */
		private DialogsBtnsController dialogsBtnsController;

		/**
		 * Instantiates a new menu controller.
		 */
		public MenuController() {

			ActionListener[] quizMngmntListeners = { new NewQuizListener(), new EditQuizListener(),
					new GradeQuizListener(), new ReportsListener() };
			ActionListener[] courseMngmntListeners = { new AddCourseListener(), new RemoveCourseListener(),
					new RegisterStudentListener("",""), new RemoveStudentListener() , new SaveFilesListener()};
			view.addQuizManagementListeners(quizMngmntListeners);
			view.addCourseManagementListeners(courseMngmntListeners);
			view.addHelpActionListener(new HelpListener());
			dialogsBtnsController = new DialogsBtnsController();
		}
		
		/**
		 * The listener interface for receiving help events.
		 * The class that is interested in processing a help
		 * event implements this interface, and the object created
		 * with that class is registered with a component using the
		 * component's <code>addHelpListener<code> method. When
		 * the help event occurs, that object's appropriate
		 * method is invoked.
		 *
		 * @see HelpEvent
		 */
		class HelpListener implements ActionListener
		{

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new helpFrameController(new HelpFrame(Constants.HELP_MAIN,Constants.HELP_MAIN_AMOUNT));
			}
			
		}
		
		/**
		 * Listener for new quiz menu option events.
		 *
		 * @see NewQuizEvent
		 */
		class NewQuizListener implements ActionListener {
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses, please create a new course first", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				newQuizDialog = new JDialog(MainFrameController.view, "New Quiz Dialog");

				newQuizDialog.setSize(300, 220);
				newQuizDialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				newQuizDialog.setVisible(true);
				newQuizDialog.setResizable(false);
				newQuizDialog.getContentPane().add(view.getNewQuizDialogPanel());
				}
			}

		}
		
		/**
		 * Listener for edit quiz menu option events.
		 *
		 * @see EditQuizEvent
		 */
		class EditQuizListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses, please create a new course first", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				editQuizdialog = new JDialog(MainFrameController.view, "Edit Quiz Dialog");

				editQuizdialog.setSize(300, 220);
				editQuizdialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				editQuizdialog.setVisible(true);
				editQuizdialog.setResizable(false);
				editQuizdialog.getContentPane().add(view.getEditQuizDialogPanel());
				int courseIndex = view.getCoursesIdsEdit().getSelectedIndex();
				view.loadQuizzesToEditCB(getQuizzesFolder(courseIndex));
				}
			}

		}

		/**
		 * Listener for grade quiz menu option events.
		 *
		 * @see GradeQuizEvent
		 */
		class GradeQuizListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses, please create a new course first.", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				gradeQuizDialog = new JDialog(MainFrameController.view, "Grade Quiz Dialog");

				gradeQuizDialog.setSize(300, 220);
				gradeQuizDialog.setLocationRelativeTo(MainFrameController.view.getContentPane());
				gradeQuizDialog.setVisible(true);
				gradeQuizDialog.setResizable(false);
				gradeQuizDialog.getContentPane().add(view.getGradeQuizDialogPanel());

				int courseIndex = view.getCourseIdGradeCB().getSelectedIndex();
				view.loadQuizzesToGradeCB(getQuizzesFolder(courseIndex));
				}
			}

		}

		/**
		 * Listener for reports menu option events.
		 *
		 * @see ReportsEvent
		 */
		class ReportsListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ReportsView reportsView = new ReportsView();
				MainFrameController.view.changeContentPane(reportsView);
			}

		}
		
		/**
		 * Listener for save files menu option events.
		 *
		 * @see SaveFilesEvent
		 */
		class SaveFilesListener implements ActionListener
		{

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CoursesCheckingFrame ccf = new CoursesCheckingFrame(coursesFiles);
				if(ccf.check())
				{
					CustomDialog dialog = new CustomDialog("<html><body>Please wait while your files are being <br>uploaded to your dropbox account</body></html>");
					dialog.setTitle("Alert");
					dialog.setVisible(true);
					SwingWorker<Void, Void> recursiveDeleteDropboxFolder = new removeFromDropbox(dialog,Constants.SAVE);
					recursiveDeleteDropboxFolder.execute();
				}
			}
			
		}
		
		/**
		 * Listener for add course menu option events.
		 *
		 * @see AddCourseEvent
		 */
		class AddCourseListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				newCourseDialog = new JDialog(MainFrameController.view, "New Course Dialog");
				newCourseDialog.setSize(300, 220);
				newCourseDialog.setLocationRelativeTo(MainFrameController.view);
				newCourseDialog.setVisible(true);
				newCourseDialog.setResizable(false);
				newCourseDialog.getContentPane().add(view.getNewCourseDialogPanel());
			}

		}

		/**
		 * Listener for remove course menu option events.
		 *
		 * @see RemoveCourseEvent
		 */
		class RemoveCourseListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses, please create a new course first.", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				removeCourseDialog = new JDialog(MainFrameController.view, "Remove Course Dialog");
				removeCourseDialog.setSize(300, 150);
				removeCourseDialog.setLocationRelativeTo(MainFrameController.view);
				removeCourseDialog.setVisible(true);
				removeCourseDialog.setResizable(false);
				removeCourseDialog.getContentPane().add(view.getRemoveCourseDialogPanel());
				}
			}

		}

		/**
		 * Listener for register student menu option events.
		 *
		 * @see RegisterStudentEvent
		 */
		class RegisterStudentListener implements ActionListener {
			
			/** The in edit mode. */
			private boolean inEditMode;
			
			/** The student course. */
			private String studentCourse;
			
			/**
			 * Instantiates a new register student listener.
			 *
			 * @param chosenFileName the chosen file name
			 * @param studentCourse the student course
			 */
			public RegisterStudentListener(String chosenFileName,
					String studentCourse) {
				this.studentCourse = studentCourse;
				// TODO Auto-generated constructor stub
				if (!chosenFileName.equals("")&&!studentCourse.equals("")) {
					StudentEntity result;
					result = (StudentEntity) ObjectFileManager.loadObject
							((Constants.ROOTPATH
							+ studentCourse + "/Students/" + chosenFileName + ".ser"));
					view.getRegisterStudentCourseCB().setSelectedItem(
							result.getStudentCourse());					
					view.getStudentId().setText(result.getStudentId());
					view.getStudentName().setText(result.getStudentName());
					view.getStudentEmail().setText(result.getStudentEmail());		
					inEditMode = true;
					editingPreviousStudentId = result.getStudentId();
				}
				else
				{
					inEditMode = false;
				}
			}

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses to register a student to", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				if (inEditMode)
				{
					registerStudentDialog = new JDialog(MainFrameController.view, "Edit Student Dialog");
					view.getRegisterStudentCourseCB().setEditable(false);
					view.getRegisterStudentCourseCB().setEnabled(false);
				}
				else
				{
					if (!studentCourse.equals("")) {
						view.getRegisterStudentCourseCB().setSelectedItem(studentCourse);
						view.getRegisterStudentCourseCB().setEditable(false);
						view.getRegisterStudentCourseCB().setEnabled(false);
					}
					else
					{
						view.getRegisterStudentCourseCB().setSelectedIndex(0);
						view.getRegisterStudentCourseCB().setEditable(true);
						view.getRegisterStudentCourseCB().setEnabled(true);
					}
					view.getStudentId().setText("");
					view.getStudentName().setText("");
					view.getStudentEmail().setText("");
					registerStudentDialog = new JDialog(MainFrameController.view, "Register Student Dialog");
				}
				registerStudentDialog.setSize(270, 300);
				registerStudentDialog.setLocationRelativeTo(MainFrameController.view);
				registerStudentDialog.setVisible(true);
				registerStudentDialog.setResizable(false);
				registerStudentDialog.getContentPane().add(view.getRegisterStudentDialogPanel());		
				}
			}
		}

		/**
		 * Listener for remove student menu option events.
		 *
		 * @see RemoveStudentEvent
		 */
		class RemoveStudentListener implements ActionListener {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(InitialWindowController.coursesFiles.size()==0)
					JOptionPane.showMessageDialog(null, "There are no courses to choose from", "Alert",
							JOptionPane.ERROR_MESSAGE);
				else
				{
				removeStudentDialog = new JDialog(MainFrameController.view, "Remove Student Dialog");
				removeStudentDialog.setSize(270, 300);
				removeStudentDialog.setLocationRelativeTo(MainFrameController.view);
				removeStudentDialog.setVisible(true);
				removeStudentDialog.setResizable(false);
				removeStudentDialog.getContentPane().add(view.getRemoveStudentDialogPanel());
				view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsItemListener);
				view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
				view.getRemoveStudentsIds().addItemListener(removeStudentsIdsItemListener);
				}
			}

		}

		/**
		 * The Class DialogsBtnsController.
		 * This class handles the InitialWindowView dialogs buttons events.
		 */
		class DialogsBtnsController {
			
			/** The pop up menus controller. */
			private PopUpMenusController popUpMenusController;

			/**
			 * Instantiates a new dialogs btns controller.
			 */
			public DialogsBtnsController() {
				view.createQuizBtnAddListener(new CreateQuizBtnListener());
				view.editQuizBtnAddListener(new EditQuizBtnListener());
				view.createCourseBtnAddListener(new CreateCourseBtnListener());
				view.removeCourseBtnAddListener(new RemoveCourseBtnListener());
				view.registerStudentBtnAddListener(new registerStudentBtnListener());
				view.removeStudentBtnAddListener(new removeStudentBtnListener());
				view.gradeQuizBtnAddListener(new gradeQuizBtnListener());
				popUpMenusController = new PopUpMenusController();
			}

			/**
			 * Listener for remove student button click event.
			 *
			 * @see removeStudentBtnEvent
			 */
			class removeStudentBtnListener implements ActionListener {
				
				/** The student id. */
				private String studentId;
				
				/** The student course. */
				private String studentCourse;
				
				/** The pop up menu flag. */
				private int popUpMenuFlag;
				
				/** The student file. */
				private File studentFile;
				
				/**
				 * Instantiates a new removes the student btn listener.
				 */
				public removeStudentBtnListener() {
					popUpMenuFlag = 0;
				}

				/**
				 * Instantiates a new removes the student btn listener.
				 *
				 * @param studentId the student id
				 * @param studentCourse the student course
				 */
				public removeStudentBtnListener(String studentId, String studentCourse) {
					this.studentId = studentId;
					this.studentCourse = studentCourse;
					popUpMenuFlag = 1;
				}

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						if (((String) view.getRemoveStudentsIds().getSelectedItem()).equals(""))
							JOptionPane.showMessageDialog(null,
									"This student does not exists,Please choose another one", "Alert",
									JOptionPane.ERROR_MESSAGE);
						else {
							if (popUpMenuFlag == 1)
								studentFile = new File(Constants.ROOTPATH
										+ studentCourse + "/Students/" + studentId + ".ser");
							if (popUpMenuFlag == 0)
								studentFile = new File(Constants.ROOTPATH
										+ (String) view.getRemoveStudentCourseCB().getSelectedItem() + "/Students/"
										+ (String) view.getRemoveStudentsIds().getSelectedItem() + ".ser");					
							File Quizzes = new File(Constants.ROOTPATH + studentCourse + "/Quizzes");
							boolean studentCanBeDeleted = true;
							if(Quizzes.isDirectory())
							{
									for(int i=0;i<Quizzes.listFiles().length;i++)
									{
										// check if the student any quiz
										if(Quizzes.listFiles()[i].isDirectory())
										{
											File quiz = Quizzes.listFiles()[i];
											
											 // check if the student took this quiz already.
											if(new File(quiz.getCanonicalPath()+"/StudentsAnswers/"+studentId).exists())
												studentCanBeDeleted=false;
										}
											
									}
							}
							if(studentCanBeDeleted){
							studentFile.delete();
							view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsItemListener);
							view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
							view.getRemoveStudentsIds().addItemListener(removeStudentsIdsItemListener);
							view.setTree(new JTree(InitialWindowView
									.filesTree(new File(new File(".").getCanonicalPath() + "/"+Constants.APP_NAME))));
							JOptionPane.showMessageDialog(null, "The student removed successfully");
						
							}
							else
								JOptionPane.showMessageDialog(null, "You can't remove this student because he already took one of your quizzes","Warning!",JOptionPane.WARNING_MESSAGE);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			/**
			 * Listener for register student button click event.
			 *
			 * @see registerStudentBtnEvent
			 */
			class registerStudentBtnListener implements ActionListener {
				
				/** The over write. */
				private int overWrite = JOptionPane.YES_OPTION;
				
				/** The existing student. */
				private StudentEntity existingStudent;
				
				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						if (view.getStudentId().getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Please enter the student's id", "Alert",
									JOptionPane.ERROR_MESSAGE);
						else if (Integer.parseInt(view.getStudentId().getText()) <= 0)
							JOptionPane.showMessageDialog(null, "The student's id must be larger from 0", "Alert",
									JOptionPane.ERROR_MESSAGE);
						else if (view.getStudentName().getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Please enter the student's name", "Alert",
									JOptionPane.ERROR_MESSAGE);
						else if (view.getStudentEmail().getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Please enter the student's email", "Alert",
									JOptionPane.ERROR_MESSAGE);
						else {
							/// check if the email is correct

							String courseName = (String) view.getRegisterStudentCourseCB().getSelectedItem();
							int courseIndexInCoursesArray = CourseEntity.getIndex(courseName);
							String coursePath = coursesFiles.get(courseIndexInCoursesArray).getCourseFolder()
									.getCanonicalPath();
							String studentId = view.getStudentId().getText();
							if (new File(coursePath + "/Students/" + studentId + ".ser").exists())
								overWrite = JOptionPane.showConfirmDialog(null,
										"This student is already exists and his data will be overwritten, \n do you want to keep the application progress?",
										"Alert", JOptionPane.YES_NO_OPTION);
							if (overWrite == JOptionPane.YES_OPTION) {
								StudentEntity studentEntity = new StudentEntity(courseName, studentId,
										view.getStudentName().getText(), view.getStudentEmail().getText());
								if(registerStudentDialog.getTitle().equals("Edit Student Dialog"))
								{
									existingStudent = (StudentEntity) ObjectFileManager.loadObject
											(coursePath + "/Students/" + editingPreviousStudentId + ".ser");
									studentEntity.setQuizzesScores(existingStudent.getQuizzesScores());
									if(!studentId.equals(editingPreviousStudentId))
										new File(coursePath + "/Students/" + editingPreviousStudentId + ".ser").delete();
									registerStudentDialog.dispose();
								}
								FileOutputStream fos = new FileOutputStream(
										coursePath + "/Students/" + studentId + ".ser");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(studentEntity);
								oos.close();
								JOptionPane.showMessageDialog(null, "The student registered successfully");
								view.setTree(new JTree(InitialWindowView
										.filesTree(new File(new File(".").getCanonicalPath() + "/"+Constants.APP_NAME))));
								view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsItemListener);
								view.loadStudents(view.getRemoveStudentCourseCB().getSelectedIndex());
								view.getRemoveStudentsIds().addItemListener(removeStudentsIdsItemListener);
							} else if (overWrite == JOptionPane.NO_OPTION || overWrite == JOptionPane.CLOSED_OPTION)
								overWrite = JOptionPane.YES_OPTION;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Please enter integers only in the student's id", "Alert",
								JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException e2) {
						JOptionPane.showMessageDialog(null, "Please enter integers only in the student id", "Alert",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			/**
			 * Listener for create course button click event.
			 *
			 * @see CreateCourseBtnEvent
			 */
			class CreateCourseBtnListener implements ActionListener {
				
				/** The course folder. */
				private File courseFolder;
				
				/** The course id. */
				private String courseId;
				
				/** The course name. */
				private String courseName;
				
				/** The course entity. */
				private CourseEntity courseEntity;
				
				/** The students folder. */
				private File studentsFolder;
				
				/** The quizzes folder. */
				private File quizzesFolder;

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					courseName = view.getNewCourseName().getText();
					courseId = view.getNewCourseId().getText();

					if (courseId.isEmpty())
						JOptionPane.showMessageDialog(null, "This course id is empty, please choose another name",
								"Alert", JOptionPane.ERROR_MESSAGE);
					else if (courseName.isEmpty())
						JOptionPane.showMessageDialog(null, "This course name is empty, please choose another name",
								"Alert", JOptionPane.ERROR_MESSAGE);
					else {
						try {
							courseFolder = new File(new File(".").getCanonicalPath() + "/"+Constants.APP_NAME + "/"
									+ CourseEntity.getCourseFolderName(courseId, courseName));
							studentsFolder = new File(courseFolder.getCanonicalPath() + "/Students");
							quizzesFolder = new File(courseFolder.getCanonicalPath() + "/Quizzes");
							courseEntity = new CourseEntity(courseFolder, courseId, courseName);
							if (!courseEntity.CourseExist()) {
								courseFolder.mkdir();
								coursesFiles.add(courseEntity.checkPosition(), courseEntity);
								studentsFolder.mkdir();
								quizzesFolder.mkdir();
								coursesUpdate();
								newCourseDialog.dispose();

							} else {
								JOptionPane.showMessageDialog(null,
										"This course already exists, please choose another id", "Alert",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}

			}

			/**
			 * Listener for edit quiz button click event.
			 *
			 * @see EditQuizBtnEvent
			 */
			public class EditQuizBtnListener implements ActionListener {
				
				/** The quiz name. */
				private String quizName;
				
				/** The course name. */
				private String courseName;
				
				/** The pop up menu flag. */
				private int popUpMenuFlag;

				/**
				 * Instantiates a new edits the quiz btn listener.
				 */
				public EditQuizBtnListener() {
					popUpMenuFlag = 0;
				}

				/**
				 * Instantiates a new edits the quiz btn listener.
				 *
				 * @param courseName the course name
				 * @param quizName the quiz name
				 */
				public EditQuizBtnListener(String courseName,String quizName) {
					this.quizName = quizName;
					this.courseName = courseName;
					popUpMenuFlag = 1;
				}

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// read object from file
					String courseFolder = null;
					if (popUpMenuFlag == 0)
					{
						courseName = view.getCoursesIdsEdit().getItemAt(view.getCoursesIdsEdit().getSelectedIndex());
						quizName = view.getQuizzes().getItemAt(view.getQuizzes().getSelectedIndex());
						try {
							courseFolder = coursesFiles.get(view.getCoursesIdsEdit().getSelectedIndex()).getCourseFolder().getCanonicalPath();
							} catch (IOException e2) {e2.printStackTrace();
						}
						
						
					} else
						courseFolder = Constants.ROOTPATH + courseName;
						
					String quizFile = quizName + ".ser";
					String path;
					QuizEntity quizEntity = new QuizEntity(quizName, courseName);
					
					File temp = quizEntity.getStudentsAnswersFolder();
					if(!temp.exists()) // Quiz was not performed yet by students.
					{
						path = courseFolder + "/Quizzes/" + quizName + "/Form/" + quizFile;
						QuizObjectEntity result = (QuizObjectEntity) ObjectFileManager.loadObject(path);
						QuizCreationView quizCreationView = new QuizCreationView();
						new QuizCreationController(quizCreationView,
								result, view);
						quizCreationView.getQuizName().setText(quizName);
						quizCreationView.getCourseName().setText(courseName);
						QuizCreationController.saveFlag = 1;
						if (popUpMenuFlag == 0)
							menuController.editQuizdialog.dispose();
						MainFrameController.view.changeContentPane(quizCreationView);
					}
					else
						JOptionPane.showMessageDialog(null, "You can't edit a quiz that was already been taken by your students.", "Quiz performed",
								JOptionPane.WARNING_MESSAGE);
				}

			}

			/**
			 * Listener for create quiz button click event.
			 *
			 * @see CreateQuizBtnEvent
			 */
			class CreateQuizBtnListener implements ActionListener {
				
				/** The quiz name. */
				private String quizName;
				
				/** The course name. */
				private String courseName;

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					quizName = view.getNewQuizName().getText();
					courseName = (String) view.getCoursesIds().getSelectedItem();
					if(createNewQuiz(quizName, courseName))
						menuController.newQuizDialog.dispose();
					
				}
			}

			/**
			 * Creates the new quiz.
			 *
			 * @param quizName the quiz name
			 * @param courseName the course name
			 * @return true, if successful
			 */
			public boolean createNewQuiz(String quizName, String courseName) {
				File quizFolder;
				if (quizName.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "This quiz name is empty, please choose another name", "Alert",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
				else {
					try {
						quizFolder = new File(Constants.ROOTPATH + courseName
								+ "/Quizzes/" + quizName);
						if (!quizFolder.exists()) {
							QuizCreationView quizCreationView = new QuizCreationView();
							QuizEntity quizEntity = new QuizEntity(quizName, courseName);
							new QuizCreationController(quizCreationView, quizEntity, view);
							quizCreationView.getQuizName().setText(quizName);
							quizCreationView.getCourseName().setText(courseName);
							MainFrameController.view.changeContentPane(quizCreationView);
							QuizCreationController.saveFlag = 1;
							return true;
							
						} else {
							JOptionPane.showMessageDialog(null,
									"This quiz name already exists, please choose another name", "Alert",
									JOptionPane.ERROR_MESSAGE);
							return false;
						}

					} 				
					catch (NullPointerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return false;
			}

			/**
			 * Listener for remove course button click event.
			 *
			 * @see RemoveCourseBtnEvent
			 */
			class RemoveCourseBtnListener implements ActionListener {
				
				/** The course name. */
				private String courseName;
				
				/** The pop up menu flag. */
				private int popUpMenuFlag;
				
				/** The course folder. */
				private File courseFolder;

				/**
				 * Instantiates a new removes the course btn listener.
				 */
				public RemoveCourseBtnListener() {
					popUpMenuFlag = 0;
				}

				/**
				 * Instantiates a new removes the course btn listener.
				 *
				 * @param courseName the course name
				 */
				public RemoveCourseBtnListener(String courseName) {
					this.courseName = courseName;
					popUpMenuFlag = 1;
				}

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(JOptionPane.showConfirmDialog(MainFrameController.view, "Are you sure you want to delete this course?","Warning!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						if (popUpMenuFlag == 0) {
							courseFolder = new File(Constants.ROOTPATH
									+ (String) view.getRemoveCourses().getSelectedItem());
							coursesFiles.remove(view.getRemoveCourses().getSelectedIndex());
						} else {
							courseFolder = new File(
									Constants.ROOTPATH + courseName);
							coursesFiles.remove(CourseEntity.getIndex(courseName));
						}
						removeFolder(courseFolder);
						coursesUpdate();
					}
					
				}

			}

			/**
			 * Listener for grade quiz button click event.
			 *
			 * @see gradeQuizBtnEvent
			 */
			class gradeQuizBtnListener implements ActionListener {

				/* (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					try {
						ArrayList<String> studentsInQuiz = new ArrayList<String>();
						ArrayList<String> studentsQuizzesPaths = new ArrayList<String>();
						String courseName = (String) view.getCourseIdGradeCB().getSelectedItem();
						String quizName = (String) view.getQuizzesToGrade().getSelectedItem();
						String path = Constants.ROOTPATH + courseName
								+ "/Quizzes/" + quizName + "/StudentsAnswers";
						File studentsAnswersFolder = new File(path);
						if(studentsAnswersFolder!=null)
						if(studentsAnswersFolder.listFiles()!=null)
						if (studentsAnswersFolder.listFiles().length > 0)
							for (File child : studentsAnswersFolder.listFiles()) {
								studentsInQuiz.add(child.getName());
								studentsQuizzesPaths.add(child.getCanonicalPath());	
							}
						String quizFormPath =  Constants.ROOTPATH + courseName + "/Quizzes/" + quizName + "/Form/";
						String formPath = quizFormPath + quizName + "WithAnswers.html";
						addCorrectImageQuestionsToStudentQuizzes(studentsQuizzesPaths,quizFormPath);
						initiateGradingProcess(studentsInQuiz, studentsQuizzesPaths, formPath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			private void addCorrectImageQuestionsToStudentQuizzes(ArrayList<String> studentsQuizzesPaths,String quizFormPath){
				ArrayList<File> answers = new ArrayList<File>();
				String studentFolder;
				File formFolder = new File(quizFormPath);
				for(int i=0;i<formFolder.listFiles().length;i++)
					if(formFolder.listFiles()[i].getName().startsWith("Answer"))
						answers.add(formFolder.listFiles()[i]);
				for(int i=0;i<studentsQuizzesPaths.size();i++)
				{
					studentFolder = studentsQuizzesPaths.get(i);
					for(int j=0;j<answers.size();j++)
					{
						try {
							String fileExtension = Files.getFileExtension(answers.get(j).getCanonicalPath());				
							BufferedImage image = ImageIO.read(answers.get(j));
							File fileSave = new File(studentFolder+"/"+answers.get(j).getName());
							ImageIO.write(image,fileExtension , fileSave);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					
					}
				}
			}
		
			/**
			 * Initiate grading process.
			 *
			 * @param students the students
			 * @param studentsQuizzesPaths the students quizzes paths
			 * @param originalQuizFormPath the original quiz form path
			 */
			public void initiateGradingProcess(ArrayList<String> students, ArrayList<String> studentsQuizzesPaths,
					String originalQuizFormPath) {
				
				GradingWindowView gradingWindowView = new GradingWindowView();
				GradingWindowController gradingWindowController = new GradingWindowController(gradingWindowView);
				menuController.gradeQuizDialog.dispose();
				MainFrameController.view.changeContentPane(gradingWindowView);
				gradingWindowController.setPreviousView(view);
				
				gradingWindowController.loadStudentsToTable(students, studentsQuizzesPaths, originalQuizFormPath);
			}

			/**
			 * The Class PopUpMenusController.
			 * This class handles the pop up menus attached to the main JTree of 
			 * InitialWindowView.
			 */
			class PopUpMenusController {
				
				/** The new quiz popup menu. */
				private JPopupMenu newQuizPopupMenu;
				
				/** The quiz popup menu. */
				private JPopupMenu quizPopupMenu;
				
				/** The course popup menu. */
				private JPopupMenu coursePopupMenu;
				
				/** The root popup menu. */
				private JPopupMenu rootPopupMenu;
				
				/** The students folder popup menu. */
				private JPopupMenu studentsFolderPopupMenu;
				
				/** The students file popup menu. */
				private JPopupMenu studentsFilePopupMenu;

				/** The create quiz item. */
				private JMenuItem createQuiz;
				
				/** The edit quiz item. */
				private JMenuItem editQuiz;
				
				/** The remove quiz item. */
				private JMenuItem removeQuiz;
				
				/** The remove course item. */
				private JMenuItem removeCourse;
				
				/** The add course item. */
				private JMenuItem addCourse;
				
				/** The register student item. */
				private JMenuItem registerStudent;
				
				/** The edit student item. */
				private JMenuItem editStudent;
				
				/** The remove student item. */
				private JMenuItem removeStudent;

				/**
				 * Instantiates a new pop up menus controller.
				 */
				public PopUpMenusController() {
					newQuizPopupMenu = new JPopupMenu();
					quizPopupMenu = new JPopupMenu();
					coursePopupMenu = new JPopupMenu();
					rootPopupMenu = new JPopupMenu();
					studentsFolderPopupMenu = new JPopupMenu();
					studentsFilePopupMenu = new JPopupMenu();

					createQuiz = new JMenuItem("Create quiz");
					editQuiz = new JMenuItem("Edit quiz");
					removeQuiz = new JMenuItem("Remove quiz");
					removeCourse = new JMenuItem("Remove course");
					addCourse = new JMenuItem("Add course");
					registerStudent = new JMenuItem("Register student");
					editStudent = new JMenuItem("Edit student");
					removeStudent = new JMenuItem("Remove student");
				}

				/**
				 * Tree mouse listener.
				 *
				 * @return the mouse adapter
				 */
				public MouseAdapter treeMouseListener() {
					return new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							String rootName = "Courses";
							if (e.getButton() == MouseEvent.BUTTON3) {
								TreePath pathForLocation = view.getTree().getPathForLocation(e.getPoint().x,
										e.getPoint().y);
								if (pathForLocation != null) {
									DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) pathForLocation
											.getLastPathComponent();
									String chosenFileName = pathForLocation.getLastPathComponent().toString();
									pathForLocation.getLastPathComponent();
									if (chosenFileName.equals(rootName))
										view.getTree().setComponentPopupMenu(rootPopupMenu());
									else if (selectedNode.getParent().toString().equals(rootName))
										view.getTree().setComponentPopupMenu(removeCoursePopupMenu(chosenFileName));
									else if (selectedNode.getParent().getParent().toString()
											.equals(rootName)) {
										if (chosenFileName.equals("Students"))
											view.getTree().setComponentPopupMenu(registerStudentPopupMenu(selectedNode.getParent().toString()));
										else if (chosenFileName.equals("Quizzes"))
											view.getTree().setComponentPopupMenu(
													newQuizPopupMenu(selectedNode.getParent().toString()));

									} else if (selectedNode.getParent().toString().equals("Students"))
										view.getTree().setComponentPopupMenu(StudentPopupMenu(chosenFileName,
												selectedNode.getParent().getParent().toString()));
									else if (selectedNode.getParent().toString().equals("Quizzes"))
										view.getTree().setComponentPopupMenu(quizPopupMenu(chosenFileName,
												selectedNode.getParent().getParent().toString()));
									else
										view.getTree().setComponentPopupMenu(null);
								} else
									view.getTree().setComponentPopupMenu(null);

							}
						}
					};

				}

				/**
				 * Quiz popup menu.
				 *
				 * @param chosenFileName the chosen file name
				 * @param courseName the course name
				 * @return the j popup menu
				 */
				private JPopupMenu quizPopupMenu(String chosenFileName, String courseName) {

					quizPopupMenu.remove(editQuiz);
					quizPopupMenu.remove(removeQuiz);
					editQuiz = new JMenuItem("Edit quiz");
					removeQuiz = new JMenuItem("Remove quiz");
					editQuiz.addActionListener(new DialogsBtnsController.EditQuizBtnListener(courseName,chosenFileName));
					final String quizName = chosenFileName;
					final String quizCourseName = courseName;
					removeQuiz.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							new QuizEntity(quizName, quizCourseName);					
							if(JOptionPane.showConfirmDialog(MainFrameController.view, "Are you sure you want to delete this quiz?")== JOptionPane.YES_OPTION)
							{
								try {
								removeFolder(new File(Constants.ROOTPATH
										+ quizCourseName + "/Quizzes/" + quizName));
								updateStudentsEntityQuizzes(new File(Constants.ROOTPATH + courseName + "/Students"), quizName);
								view.setTree(new JTree(InitialWindowView
										.filesTree(new File(new File(".").getCanonicalPath() + "/"+Constants.APP_NAME))));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
						}
					});
					quizPopupMenu.add(editQuiz);
					quizPopupMenu.add(removeQuiz);
					return quizPopupMenu;
				}

				/**
				 * Removes the course popup menu.
				 *
				 * @param chosenFileName the chosen file name
				 * @return the j popup menu
				 */
				private JPopupMenu removeCoursePopupMenu(String chosenFileName) {
					coursePopupMenu.remove(removeCourse);
					removeCourse = new JMenuItem("Remove course");
					removeCourse.addActionListener(new RemoveCourseBtnListener(chosenFileName));
					coursePopupMenu.add(removeCourse);
					return coursePopupMenu;
				}

				/**
				 * Root popup menu.
				 *
				 * @return the j popup menu
				 */
				private JPopupMenu rootPopupMenu() {
					rootPopupMenu.add(addCourse);
					if(addCourse.getActionListeners().length==0)
					addCourse.addActionListener(new AddCourseListener());
					return rootPopupMenu;
				}

				/**
				 * Register student popup menu.
				 *
				 * @param studentCourse the student course
				 * @return the j popup menu
				 */
				private JPopupMenu registerStudentPopupMenu(String studentCourse) {
					studentsFolderPopupMenu.remove(registerStudent);
					registerStudent = new JMenuItem("Register student");					
					registerStudent.addActionListener(new RegisterStudentListener("",studentCourse));
					studentsFolderPopupMenu.add(registerStudent);
					return studentsFolderPopupMenu;
				}

				/**
				 * Student popup menu.
				 *
				 * @param chosenFileName the chosen file name
				 * @param studentCourse the student course
				 * @return the j popup menu
				 */
				private JPopupMenu StudentPopupMenu(String chosenFileName, String studentCourse) {
					studentsFilePopupMenu.remove(removeStudent);
					studentsFilePopupMenu.remove(editStudent);
					removeStudent = new JMenuItem("Remove student");
					editStudent = new JMenuItem("Edit student");
					removeStudent.addActionListener(new removeStudentBtnListener(chosenFileName, studentCourse));
					editStudent.addActionListener(new RegisterStudentListener(chosenFileName, studentCourse));
					studentsFilePopupMenu.add(removeStudent);
					studentsFilePopupMenu.add(editStudent);
					return studentsFilePopupMenu;
				}

				/**
				 * New quiz popup menu.
				 *
				 * @param courseName the course name
				 * @return the j popup menu
				 */
				private JPopupMenu newQuizPopupMenu(String courseName) {
					final String courseN = courseName;
					newQuizPopupMenu.remove(createQuiz);
					createQuiz = new JMenuItem("Create quiz");
					createQuiz.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String quizName = (String) JOptionPane.showInputDialog(MainFrameController.view,
									"Creating new quiz in course : " + courseN + "\n" + "Please, choose quiz name:",
									"New Quiz Dialog", JOptionPane.PLAIN_MESSAGE);
							
								while(quizName!=null && quizName.equals(""))
								{
									// showing an "empty quiz name" error dialog
									createNewQuiz(quizName, courseN); 
									quizName = (String) JOptionPane.showInputDialog(MainFrameController.view,
											"Creating new quiz in course : " + courseN + "\n" + "Please, choose quiz name:",
											"New Quiz Dialog", JOptionPane.PLAIN_MESSAGE);
								}
								//quiz creation initiation
								if(quizName != null) 
								createNewQuiz(quizName, courseN);
								
								// else quiz creation cancelled

						}
					});
					newQuizPopupMenu.add(createQuiz);
					return newQuizPopupMenu;
				}
			}

			/**
			 * Gets the pop up menus controller.
			 *
			 * @return the pop up menus controller
			 */
			public PopUpMenusController getPopUpMenusController() {
				return popUpMenusController;
			}

			/**
			 * Update students entity quizzes.
			 *
			 * @param file the file
			 * @param quizName the quiz name
			 */
			public void updateStudentsEntityQuizzes(File file, String quizName) {
				for (File child : file.listFiles()) {
					try {
						StudentEntity result = (StudentEntity) ObjectFileManager.loadObject(child.getCanonicalPath());
						result.removeQuiz(quizName);
						FileOutputStream fos = new FileOutputStream(child.getCanonicalPath());
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(result);
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Item Listener for the JComboBox that holds the courses ids in the removeStudentDialog.
	 * @see removeStudentCourseCBAddItemEvent
	 */
	class removeStudentCourseCBItemListener implements ItemListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> temp = (JComboBox<?>) e.getSource();
			view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsItemListener);
			view.loadStudents(temp.getSelectedIndex());
			view.getRemoveStudentsIds().addItemListener(removeStudentsIdsItemListener);
		}

	}

	/**
	 * Item Listener for the JComboBox that holds the students' ids in the removeStudentDialog.
	 * @see removeStudentsIdsCBAddItemEvent
	 */
	class removeStudentsIdsCBItemListener implements ItemListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> temp = (JComboBox<?>) e.getSource();
			try {	
				view.loadStudentNameToRemoveLbl(
						coursesFiles.get(view.getRemoveStudentCourseCB().getSelectedIndex()).getCourseFolder()
								.getCanonicalPath() + "/Students/" + (String) temp.getSelectedItem() + ".ser");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Item Listener for the JComboBox that holds the courses ids in the editQuizDialog.
	 * @see coursesIdsEditAddItemEvent
	 */
	class coursesIdsEditItemListener implements ItemListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> temp = (JComboBox<?>) e.getSource();

			view.loadQuizzesToEditCB(getQuizzesFolder(temp.getSelectedIndex()));

		}

	}

	/**
	 * Item Listener for the JComboBox that holds the courses ids in the gradeQuizDialog.
	 * @see courseIdGradeItemEvent
	 */
	class courseIdGradeItemListener implements ItemListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> temp = (JComboBox<?>) e.getSource();
			view.getCourseIdGradeCB().removeItemListener(courseIdGradeItemListener);
			view.loadQuizzesToGradeCB(getQuizzesFolder(temp.getSelectedIndex()));
			view.getCourseIdGradeCB().addItemListener(courseIdGradeItemListener);
		}

	}

	/**
	 * Removes the folder.
	 *
	 * @param file the file
	 */
	public void removeFolder(File file) {
		if (file.isDirectory())
			for (File child : file.listFiles())
				removeFolder(child);
		file.delete();
	}

	/**
	 * Gets the quizzes folder.
	 *
	 * @param selectedIndex the selected index
	 * @return the quizzes folder
	 */
	public File[] getQuizzesFolder(int selectedIndex) {

		File[] courseFiles = coursesFiles.get(selectedIndex).getCourseFolder().listFiles();
		if(courseFiles!=null)
		for (int i = 0; i < courseFiles.length; i++)
			if (courseFiles[i].getName().equals("Quizzes"))
				return courseFiles[i].listFiles();
		return null;
	}

	/**
	 * Courses update.
	 */
	public void coursesUpdate() {
		view.getCoursesIds().removeAllItems();
		view.getRemoveCourses().removeAllItems();
		view.getCoursesIdsEdit().removeItemListener(idsEditItemListener);
		view.getCoursesIdsEdit().removeAllItems();
		view.getCourseIdGradeCB().removeItemListener(courseIdGradeItemListener);
		view.getCourseIdGradeCB().removeAllItems();

		view.getRegisterStudentCourseCB().removeAllItems();
		view.getCourseIdGradeCB().removeAllItems();

		view.getCourseIdGradeCB().addItemListener(courseIdGradeItemListener);
		view.coursesIdsEditAddItemListener(idsEditItemListener);
		

		view.getRemoveStudentsIds().removeItemListener(removeStudentsIdsItemListener);
		view.getRemoveStudentCourseCB().removeItemListener(removeStudentCourseItemListener);
		view.getRemoveStudentCourseCB().removeAllItems();
	
		for (int i = 0; i < InitialWindowController.coursesFiles.size(); i++) {
			String item = InitialWindowController.coursesFiles.get(i).getCourseFolderName();
			view.getCoursesIds().addItem(item);
			view.getRemoveCourses().addItem(item);
			view.getCoursesIdsEdit().addItem(item);
			view.getRemoveStudentCourseCB().addItem(item);
			view.getRegisterStudentCourseCB().addItem(item);
			view.getCourseIdGradeCB().addItem(item);
		}
		view.getRemoveStudentsIds().addItemListener(removeStudentsIdsItemListener);
		view.getRemoveStudentCourseCB().addItemListener(removeStudentCourseItemListener);

		try {
			view.setTree(new JTree(
					InitialWindowView.filesTree(new File(new File(".").getCanonicalPath() + "/"+Constants.APP_NAME))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Listener for the main frame buttons ("x" button ).
	 * @see windowEvent
	 */
	class windowListener extends WindowAdapter implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			CoursesCheckingFrame ccf = new CoursesCheckingFrame(coursesFiles);
			if(ccf.check())
			{
				if(JOptionPane.showConfirmDialog(MainFrameController.view, "Do you want to save your data before exiting?","Warning!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					CustomDialog dialog = new CustomDialog("<html><body>Please wait while your files are being <br>uploaded to your dropbox account</body></html>");
					dialog.setTitle("Alert");
					dialog.setVisible(true);
					SwingWorker<Void, Void> recursiveDeleteDropboxFolder = new removeFromDropbox(dialog,Constants.SAVE_AND_EXIT);
					recursiveDeleteDropboxFolder.execute();
				}
				else
					System.exit(0);
			}
		}
	}

}
