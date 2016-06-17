package Views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JComboBox;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import Controllers.InitialWindowController;
import Entities.StudentEntity;
import Utilities.MyTree;
import Utilities.ObjectFileManager;

/**
 * The Class InitialWindowView.
 * This class is a boundary controlled by {@link InitialWindowController}.
 */
public class InitialWindowView extends ViewPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The mn quiz mng menu. */
	private JMenu mnQuizMngMenu;
	
	/** The menu bar. */
	private JMenuBar menuBar;
	
	/** The mn course management. */
	private JMenu mnCourseManagement;

	/** The new quiz dialog panel. */
	private JPanel newQuizDialogPanel;
	
	/** The courses ids. */
	private JComboBox<String> coursesIds;
	
	/** The new quiz name. */
	private JTextField newQuizName;
	
	/** The create new quiz button. */
	private JButton createNewQuizBtn;

	/** The edit quiz dialog panel. */
	private JPanel editQuizDialogPanel;
	
	/** The courses ids edit. */
	private JComboBox<String> coursesIdsEdit;
	
	/** The quizzes. */
	public JComboBox<String> quizzes;
	
	/** The edit quiz button. */
	private JButton editQuizBtn;

	/** The new course id. */
	private JTextField newCourseId;
	
	/** The new course dialog panel. */
	private JPanel newCourseDialogPanel;
	
	/** The create new course button. */
	private JButton createNewCourseBtn;
	
	/** The new course name. */
	private JTextField newCourseName;

	/** The remove course dialog panel. */
	private JPanel removeCourseDialogPanel;
	
	/** The remove courses. */
	private JComboBox<String> removeCourses;
	
	/** The remove course button. */
	private JButton removeCourseBtn;

	/** The tree. */
	private JTree tree;
	
	/** The register student dialog panel. */
	private JPanel registerStudentDialogPanel;
	
	/** The register student course cb. */
	private JComboBox<String> registerStudentCourseCB;
	
	/** The student id. */
	private JTextField studentId;
	
	/** The student name. */
	private JTextField studentName;
	
	/** The student email. */
	private JTextField studentEmail;
	
	/** The register student button. */
	private JButton registerStudentBtn;

	/** The remove student dialog panel. */
	private JPanel removeStudentDialogPanel;
	
	/** The remove student course cb. */
	private JComboBox<String> removeStudentCourseCB;
	
	/** The remove students ids. */
	private JComboBox<String> removeStudentsIds;
	
	/** The chosen remove student name label. */
	private JLabel chosenRemoveStudentNameLbl;
	
	/** The remove student button. */
	private JButton removeStudentBtn;

	/** The grade quiz dialog panel. */
	private JPanel gradeQuizDialogPanel;
	
	/** The course id grade cb. */
	private JComboBox<String> courseIdGradeCB;
	
	/** The quizzes to grade. */
	public JComboBox<String> quizzesToGrade;
	
	/** The grade button. */
	private JButton gradeBtn;

	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The mn help menu. */
	private JMenu mnHelpMenu;
	/**
	 * Create the panel.
	 */
	public InitialWindowView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(100000, 30));
		menuBar.setMinimumSize(new Dimension(0, 30));
		menuBar.setMaximumSize(new Dimension(100000, 30));
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		add(menuBar);

		mnCourseManagement = new JMenu("Course Management");
		menuBar.add(mnCourseManagement);

		JMenuItem mntmAddCourse = new JMenuItem("Add Course");
		mnCourseManagement.add(mntmAddCourse);

		JMenuItem mntmRemoveCourse = new JMenuItem("Remove Course");
		mnCourseManagement.add(mntmRemoveCourse);

		JMenuItem mntmRegisterStudent = new JMenuItem("Register Student");
		mnCourseManagement.add(mntmRegisterStudent);

		JMenuItem mntmRemoveStudent = new JMenuItem("Remove Student");
		mnCourseManagement.add(mntmRemoveStudent);
		
		JMenuItem mntmSave = new JMenuItem("Save Files");
		mnCourseManagement.add(mntmSave);

		JSeparator separator1 = new JSeparator();
		separator1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator1.setBackground(Color.LIGHT_GRAY);
		separator1.setMaximumSize(new Dimension(2, 100));
		separator1.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(separator1);

		mnQuizMngMenu = new JMenu("Quiz Management");
		mnQuizMngMenu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar.add(mnQuizMngMenu);

		JMenuItem mntmNewQuiz = new JMenuItem("New Quiz");
		mnQuizMngMenu.add(mntmNewQuiz);

		JMenuItem mntmEditQuiz = new JMenuItem("Edit Quiz");
		mnQuizMngMenu.add(mntmEditQuiz);

		JMenuItem mntmGradeQuiz = new JMenuItem("Grade Quiz");
		mnQuizMngMenu.add(mntmGradeQuiz);

		JMenuItem mntmReports = new JMenuItem("Reports");
		mnQuizMngMenu.add(mntmReports);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator2.setBackground(Color.LIGHT_GRAY);
		separator2.setMaximumSize(new Dimension(2, 100));
		separator2.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(separator2);

		mnHelpMenu = new JMenu("Help");
		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mnHelpMenu.add(mntmHelpContents);

		menuBar.add(mnHelpMenu);
		
		MyTree myTree = new MyTree();
		tree = myTree.makeUI();
//			tree = new JTree(myTree.makeModel(new File(".").getCanonicalPath() + "/OnlineQuizChecker"));
		//tree.setFont(new Font("Arial", Font.PLAIN, 24));
		//tree.setRowHeight(35);
		//tree.setBounds(0, 30, MainFrameController.view.getWidth(), MainFrameController.view.getHeight());
		scrollPane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);
		Vector<String> vec = new Vector<String>();
		for (int i = 0; i < InitialWindowController.coursesFiles.size(); i++)
			vec.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());


		// Dialogs Initializing
		newQuizDialogInit(vec);
		
		editQuizDialogInit(vec);

		newCourseDialogInit();
		
		removeCourseDialogInit(vec);
		
		registerStudentDialogInit(vec);
		
		removeStudentDialogInit(vec);
		
		gradeQuizDialogInit(vec);
	}

	/**
	 * New quiz dialog init.
	 *
	 * @param vec the vec
	 */
	private void newQuizDialogInit(Vector<String> vec) {
		// TODO Auto-generated method stub
		newQuizDialogPanel = new JPanel();
		newQuizDialogPanel.setLayout(null);
		//newQuizDialogPanel.setBackground();
		newQuizDialogPanel.setSize(300, 220);
		newQuizDialogPanel.setVisible(true);

		Label label = new Label("Course Id:");
		label.setBounds(13, 28, 80, 19);
		newQuizDialogPanel.add(label);

		coursesIds = new JComboBox<String>();
		for (String item : vec)
			coursesIds.addItem(item);
		coursesIds.setBounds(100, 27, 180, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			coursesIds.setSelectedIndex(0);
		newQuizDialogPanel.add(coursesIds);

		Label label1 = new Label("Quiz Name:");
		label1.setBounds(13, 70, 80, 19);
		newQuizDialogPanel.add(label1);

		newQuizName = new JTextField();
		newQuizName.setBounds(100, 70, 180, 20);
		newQuizDialogPanel.add(newQuizName);
		newQuizName.setColumns(10);

		createNewQuizBtn = new JButton("Create Quiz");
		createNewQuizBtn.setBounds(
				newQuizDialogPanel.getSize().width / 2 - createNewQuizBtn.getPreferredSize().width / 2, 121,
				createNewQuizBtn.getPreferredSize().width, createNewQuizBtn.getPreferredSize().height);
		newQuizDialogPanel.add(createNewQuizBtn);

	}

	/**
	 * Edits the quiz dialog init.
	 *
	 * @param vec the vec
	 */
	private void editQuizDialogInit(Vector<String> vec) {
		// TODO Auto-generated method stub
		editQuizDialogPanel = new JPanel();
		editQuizDialogPanel.setLayout(null);
		editQuizDialogPanel.setSize(300, 220);
	
		Label label2 = new Label("Course Id:");
		label2.setBounds(13, 28, 80, 19);
		editQuizDialogPanel.add(label2);
	
		coursesIdsEdit = new JComboBox<String>();
		for (String item : vec)
			coursesIdsEdit.addItem(item);
		coursesIdsEdit.setBounds(100, 27, 180, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			coursesIdsEdit.setSelectedIndex(0);
		editQuizDialogPanel.add(coursesIdsEdit);
	
		Label label3 = new Label("Quiz Name:");
		label3.setBounds(13, 70, 80, 19);
		newQuizDialogPanel.add(label3);
		editQuizDialogPanel.add(label3);
	
		quizzes = new JComboBox<String>();
		quizzes.setBounds(100, 69, 180, 20);
		if (InitialWindowController.coursesFiles.size() > 0) {
			for (File child : InitialWindowController.coursesFiles.get(0).getCourseFolder().listFiles())
				quizzes.addItem(child.getName());
		} else
			quizzes.addItem("");
		editQuizDialogPanel.add(quizzes);
	
		editQuizBtn = new JButton("Edit Quiz");
		editQuizBtn.setBounds(editQuizDialogPanel.getSize().width / 2 - editQuizBtn.getPreferredSize().width / 2, 121,
				editQuizBtn.getPreferredSize().width, editQuizBtn.getPreferredSize().height);
		editQuizDialogPanel.add(editQuizBtn);
		editQuizDialogPanel.setVisible(true);
	}

	/**
	 * New course dialog init.
	 */
	private void newCourseDialogInit() {
		newCourseDialogPanel = new JPanel();
		newCourseDialogPanel.setLayout(null);
		newCourseDialogPanel.setSize(300, 220);

		JLabel lblCourseId = new JLabel("Course Id:");
		lblCourseId.setBounds(13, 28, 80, 19);
		newCourseDialogPanel.add(lblCourseId);

		newCourseId = new JTextField();
		newCourseId.setBounds(100, 27, 180, 20);
		newCourseDialogPanel.add(newCourseId);
		newCourseId.setColumns(10);

		JLabel lblNewLabel = new JLabel("Course Name:");
		lblNewLabel.setBounds(13, 58, 84, 14);
		newCourseDialogPanel.add(lblNewLabel);

		newCourseName = new JTextField();
		newCourseName.setBounds(100, 59, 180, 20);
		newCourseDialogPanel.add(newCourseName);
		newCourseName.setColumns(10);

		createNewCourseBtn = new JButton("Create Course");
		createNewCourseBtn.setBounds(
				newQuizDialogPanel.getSize().width / 2 - createNewCourseBtn.getPreferredSize().width / 2, 121,
				createNewCourseBtn.getPreferredSize().width, createNewCourseBtn.getPreferredSize().height);
		newCourseDialogPanel.add(createNewCourseBtn);

	}

	/**
	 * Removes the course dialog init.
	 *
	 * @param vec the vec
	 */
	private void removeCourseDialogInit(Vector<String> vec) {
		removeCourseDialogPanel = new JPanel();
		removeCourseDialogPanel.setLayout(null);
		removeCourseDialogPanel.setSize(300, 150);

		JLabel lblCourse = new JLabel("Course Id:");
		lblCourse.setBounds(13, 20, 80, 19);
		removeCourseDialogPanel.add(lblCourse);

		removeCourses = new JComboBox<String>();
		for (String item : vec)
			removeCourses.addItem(item);
		removeCourses.setBounds(100, 20, 186, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			removeCourses.setSelectedIndex(0);
		removeCourseDialogPanel.add(removeCourses);

		removeCourseBtn = new JButton("remove course");
		removeCourseBtn.setBounds(
				removeCourseDialogPanel.getSize().width / 2 - removeCourseBtn.getPreferredSize().width / 2, 60,
				removeCourseBtn.getPreferredSize().width, removeCourseBtn.getPreferredSize().height);
		removeCourseDialogPanel.add(removeCourseBtn);

	}

	/**
	 * Register student dialog init.
	 *
	 * @param vec the vec
	 */
	private void registerStudentDialogInit(Vector<String> vec) {
		registerStudentDialogPanel = new JPanel();
		registerStudentDialogPanel.setLayout(null);
		registerStudentDialogPanel.setSize(270, 300);

		JLabel lblCourse1 = new JLabel("Course Id:");
		lblCourse1.setBounds(4, 40, 100, 20);
		registerStudentDialogPanel.add(lblCourse1);

		registerStudentCourseCB = new JComboBox<String>();
		for (String item : vec)
			registerStudentCourseCB.addItem(item);
		registerStudentCourseCB.setBounds(105, 40, 150, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			registerStudentCourseCB.setSelectedIndex(0);
		registerStudentDialogPanel.add(registerStudentCourseCB);

		JLabel studentIdLbl = new JLabel("Student's Id:");
		studentIdLbl.setBounds(4, 90, 100, 20);
		registerStudentDialogPanel.add(studentIdLbl);

		studentId = new JTextField();
		studentId.setBounds(105, 90, 150, 20);
		registerStudentDialogPanel.add(studentId);
		studentId.setColumns(10);

		JLabel studentNameLbl = new JLabel("Student's name:");
		studentNameLbl.setBounds(4, 140, 100, 20);
		registerStudentDialogPanel.add(studentNameLbl);

		studentName = new JTextField();
		studentName.setBounds(105, 140, 150, 20);
		registerStudentDialogPanel.add(studentName);
		studentName.setColumns(10);

		JLabel studentEmailLbl = new JLabel("Student's email:");
		studentEmailLbl.setBounds(4, 190, 100, 20);
		registerStudentDialogPanel.add(studentEmailLbl);

		studentEmail = new JTextField();
		studentEmail.setBounds(105, 190, 150, 20);
		registerStudentDialogPanel.add(studentEmail);
		studentEmail.setColumns(10);

		registerStudentBtn = new JButton("Register");
		registerStudentBtn.setBounds(125 - registerStudentBtn.getPreferredSize().width / 2, 220,
				registerStudentBtn.getPreferredSize().width, registerStudentBtn.getPreferredSize().height);
		registerStudentDialogPanel.add(registerStudentBtn);

	}

	/**
	 * Removes the student dialog init.
	 *
	 * @param vec the vec
	 */
	private void removeStudentDialogInit(Vector<String> vec) {
		removeStudentDialogPanel = new JPanel();
		removeStudentDialogPanel.setLayout(null);
		removeStudentDialogPanel.setSize(270, 300);

		JLabel lblRemoveStudentCourse = new JLabel("Course Id:");
		lblRemoveStudentCourse.setBounds(4, 20, 80, 20);
		removeStudentDialogPanel.add(lblRemoveStudentCourse);

		removeStudentCourseCB = new JComboBox<String>();
		for (String item : vec)
			removeStudentCourseCB.addItem(item);
		removeStudentCourseCB.setBounds(105, 20, 150, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			removeStudentCourseCB.setSelectedIndex(0);
		removeStudentDialogPanel.add(removeStudentCourseCB);

		JLabel removeStudentIdLbl = new JLabel("Student's Id:");
		removeStudentIdLbl.setBounds(4, 70, 80, 20);
		removeStudentDialogPanel.add(removeStudentIdLbl);

		JLabel removeStudentNameLbl = new JLabel("Student's name:");
		removeStudentNameLbl.setBounds(4, 120, 100, 20);
		removeStudentDialogPanel.add(removeStudentNameLbl);

		removeStudentsIds = new JComboBox<String>();
		removeStudentsIds.setBounds(105, 70, 150, 20);

		chosenRemoveStudentNameLbl = new JLabel("");
		chosenRemoveStudentNameLbl.setBounds(105, 120, 150, 20);

		if (InitialWindowController.coursesFiles.size() > 0)
			loadStudents(removeStudentCourseCB.getSelectedIndex());
		else
			removeStudentsIds.addItem("");
		removeStudentDialogPanel.add(removeStudentsIds);
		removeStudentDialogPanel.add(chosenRemoveStudentNameLbl);

		removeStudentBtn = new JButton("Remove");
		removeStudentBtn.setBounds(125 - removeStudentBtn.getPreferredSize().width / 2, 220,
				removeStudentBtn.getPreferredSize().width, removeStudentBtn.getPreferredSize().height);
		removeStudentDialogPanel.add(removeStudentBtn);

	}

	/**
	 * Grade quiz dialog init.
	 *
	 * @param vec the vec
	 */
	private void gradeQuizDialogInit(Vector<String> vec) {
		gradeQuizDialogPanel = new JPanel();
		gradeQuizDialogPanel.setLayout(null);
		gradeQuizDialogPanel.setSize(300, 220);

		JLabel coursesIdsGradeLbl = new JLabel("Course Id:");
		coursesIdsGradeLbl.setBounds(13, 28, 80, 19);
		gradeQuizDialogPanel.add(coursesIdsGradeLbl);

		courseIdGradeCB = new JComboBox<String>();
		for (String item : vec)
			courseIdGradeCB.addItem(item);
		courseIdGradeCB.setBounds(100, 27, 180, 20);
		if (InitialWindowController.coursesFiles.size() > 0)
			courseIdGradeCB.setSelectedIndex(0);
		gradeQuizDialogPanel.add(courseIdGradeCB);

		Label quizNameLbl = new Label("Quiz Name:");
		quizNameLbl.setBounds(13, 70, 80, 19);
		gradeQuizDialogPanel.add(quizNameLbl);

		quizzesToGrade = new JComboBox<String>();
		quizzesToGrade.setBounds(100, 69, 180, 20);
		if (InitialWindowController.coursesFiles.size() > 0) {
			for (File child : InitialWindowController.coursesFiles.get(0).getCourseFolder().listFiles())
				quizzesToGrade.addItem(child.getName());
		} else
			quizzesToGrade.addItem("");
		gradeQuizDialogPanel.add(quizzesToGrade);

		gradeBtn = new JButton("Grade Quiz");
		gradeBtn.setBounds(gradeQuizDialogPanel.getSize().width / 2 - gradeBtn.getPreferredSize().width / 2, 121,
				gradeBtn.getPreferredSize().width, gradeBtn.getPreferredSize().height);
		gradeQuizDialogPanel.add(gradeBtn);
		gradeQuizDialogPanel.setVisible(true);
	}

	/**
	 * Load students.
	 *
	 * @param index the index
	 */
	public void loadStudents(int index) {
		File studentsFolder;
		try {
			removeStudentsIds.removeAllItems();
			studentsFolder = new File(
					InitialWindowController.coursesFiles.get(index).getCourseFolder().getCanonicalPath() + "/Students");
			for (File child : studentsFolder.listFiles())
				removeStudentsIds.addItem((String) child.getName().subSequence(0, child.getName().length() - 4));
			if (studentsFolder.listFiles().length > 0)
				loadStudentNameToRemoveLbl(studentsFolder.listFiles()[0].getPath());
			else {
				removeStudentsIds.addItem("");
				chosenRemoveStudentNameLbl.setText("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load student name to remove lbl.
	 *
	 * @param path the path
	 */
	public void loadStudentNameToRemoveLbl(String path) {

		StudentEntity result = (StudentEntity) ObjectFileManager.loadObject(path);
		chosenRemoveStudentNameLbl.setText(result.getStudentName());
		
	}

	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public JTextField getStudentId() {
		return studentId;
	}

	/**
	 * Gets the student name.
	 *
	 * @return the student name
	 */
	public JTextField getStudentName() {
		return studentName;
	}

	/**
	 * Gets the student email.
	 *
	 * @return the student email
	 */
	public JTextField getStudentEmail() {
		return studentEmail;
	}

	/**
	 * Adds the quiz management listeners.
	 *
	 * @param listener the listener
	 */
	public void addQuizManagementListeners(ActionListener[] listener) {
		for (int i = 0; i < listener.length; i++) {
			mnQuizMngMenu.getItem(i).addActionListener(listener[i]);
		}
	}

	/**
	 * Adds the course management listeners.
	 *
	 * @param listener the listener
	 */
	public void addCourseManagementListeners(ActionListener[] listener) {
		for (int i = 0; i < listener.length; i++) {
			mnCourseManagement.getItem(i).addActionListener(listener[i]);
		}
	}

	/**
	 * Register student btn add listener.
	 *
	 * @param listener the listener
	 */
	public void registerStudentBtnAddListener(ActionListener listener) {
		registerStudentBtn.addActionListener(listener);
	}

	/**
	 * Creates the quiz btn add listener.
	 *
	 * @param listener the listener
	 */
	public void createQuizBtnAddListener(ActionListener listener) {
		createNewQuizBtn.addActionListener(listener);
	}

	/**
	 * Creates the course btn add listener.
	 *
	 * @param listener the listener
	 */
	public void createCourseBtnAddListener(ActionListener listener) {
		createNewCourseBtn.addActionListener(listener);
	}

	/**
	 * Removes the course btn add listener.
	 *
	 * @param listener the listener
	 */
	public void removeCourseBtnAddListener(ActionListener listener) {
		removeCourseBtn.addActionListener(listener);
	}

	/**
	 * Edits the quiz btn add listener.
	 *
	 * @param listener the listener
	 */
	public void editQuizBtnAddListener(ActionListener listener) {
		editQuizBtn.addActionListener(listener);
	}

	/**
	 * Removes the student btn add listener.
	 *
	 * @param listener the listener
	 */
	public void removeStudentBtnAddListener(ActionListener listener) {
		removeStudentBtn.addActionListener(listener);
	}

	/**
	 * Courses ids edit add item listener.
	 *
	 * @param listener the listener
	 */
	public void coursesIdsEditAddItemListener(ItemListener listener) {
		coursesIdsEdit.addItemListener(listener);
	}

	/**
	 * Removes the student course cb add item listener.
	 *
	 * @param listener the listener
	 */
	public void removeStudentCourseCBAddItemListener(ItemListener listener) {
		removeStudentCourseCB.addItemListener(listener);
	}

	/**
	 * Removes the students ids cb add item listener.
	 *
	 * @param listener the listener
	 */
	public void removeStudentsIdsCBAddItemListener(ItemListener listener) {
		removeStudentsIds.addItemListener(listener);
	}

	/**
	 * Grade quiz btn add listener.
	 *
	 * @param listener the listener
	 */
	public void gradeQuizBtnAddListener(ActionListener listener) {
		gradeBtn.addActionListener(listener);
	}

	/**
	 * Course id grade add item listener.
	 *
	 * @param listener the listener
	 */
	public void courseIdGradeAddItemListener(ItemListener listener) {
		courseIdGradeCB.addItemListener(listener);
	}
	
	/**
	 * Adds the help action listener.
	 *
	 * @param listener the listener
	 */
	public void addHelpActionListener(ActionListener listener)
	{
		mnHelpMenu.getItem(0).addActionListener(listener);
	}
	
	/**
	 * Gets the new quiz dialog panel.
	 *
	 * @return the new quiz dialog panel
	 */
	public JPanel getNewQuizDialogPanel() {
		return newQuizDialogPanel;
	}

	/**
	 * Gets the edits the quiz dialog panel.
	 *
	 * @return the edits the quiz dialog panel
	 */
	public JPanel getEditQuizDialogPanel() {
		return editQuizDialogPanel;
	}

	/**
	 * Gets the new course dialog panel.
	 *
	 * @return the new course dialog panel
	 */
	public JPanel getNewCourseDialogPanel() {
		return newCourseDialogPanel;
	}

	/**
	 * Gets the removes the course dialog panel.
	 *
	 * @return the removes the course dialog panel
	 */
	public JPanel getRemoveCourseDialogPanel() {
		return removeCourseDialogPanel;
	}

	/**
	 * Gets the removes the student dialog panel.
	 *
	 * @return the removes the student dialog panel
	 */
	public JPanel getRemoveStudentDialogPanel() {
		return removeStudentDialogPanel;
	}

	/**
	 * Gets the register student dialog panel.
	 *
	 * @return the register student dialog panel
	 */
	public JPanel getRegisterStudentDialogPanel() {
		return registerStudentDialogPanel;
	}

	/**
	 * Gets the grade quiz dialog panel.
	 *
	 * @return the grade quiz dialog panel
	 */
	public JPanel getGradeQuizDialogPanel() {
		return gradeQuizDialogPanel;
	}

	/**
	 * Gets the register student course cb.
	 *
	 * @return the register student course cb
	 */
	public JComboBox<String> getRegisterStudentCourseCB() {
		return registerStudentCourseCB;
	}

	/**
	 * Gets the removes the student course cb.
	 *
	 * @return the removes the student course cb
	 */
	public JComboBox<String> getRemoveStudentCourseCB() {
		return removeStudentCourseCB;
	}

	/**
	 * Gets the removes the students ids.
	 *
	 * @return the removes the students ids
	 */
	public JComboBox<String> getRemoveStudentsIds() {
		return removeStudentsIds;
	}

	/**
	 * Gets the course id grade cb.
	 *
	 * @return the course id grade cb
	 */
	public JComboBox<String> getCourseIdGradeCB() {
		return courseIdGradeCB;
	}

	/**
	 * Gets the tree.
	 *
	 * @return the tree
	 */
	public JTree getTree() {
		return tree;
	}

	/**
	 * Sets the tree.
	 *
	 * @param tree the new tree
	 */
	public void setTree(JTree tree) {

		MouseListener[] mouseListener = this.tree.getMouseListeners();
		MyTree myTree = new MyTree();
		this.tree = myTree.makeUI();
		//this.tree = tree;
//		this.tree.setFont(new Font("Arial", Font.PLAIN, 24));
//		this.tree.setRowHeight(35);
		if (mouseListener.length > 1)
			this.tree.addMouseListener((MouseAdapter) mouseListener[1]);
		scrollPane.setViewportView(this.tree);
		expandAllNodes(this.tree, 0, this.tree.getRowCount());
//		this.tree.setBounds(0, 30, MainFrameController.view.getWidth(), MainFrameController.view.getHeight());
		scrollPane.revalidate();
		revalidate();
	}
	
	/**
	 * Expand all nodes.
	 *
	 * @param tree the tree
	 * @param startingIndex the starting index
	 * @param rowCount the row count
	 */
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	
	/**
	 * Gets the courses ids edit.
	 *
	 * @return the courses ids edit
	 */
	public JComboBox<String> getCoursesIdsEdit() {
		return coursesIdsEdit;
	}

	/**
	 * Sets the courses ids edit.
	 *
	 * @param coursesIdsEdit the new courses ids edit
	 */
	public void setCoursesIdsEdit(JComboBox<String> coursesIdsEdit) {
		this.coursesIdsEdit = coursesIdsEdit;
	}

	/**
	 * Gets the quizzes.
	 *
	 * @return the quizzes
	 */
	public JComboBox<String> getQuizzes() {
		return quizzes;
	}

	/**
	 * Sets the quizzes.
	 *
	 * @param quizzes the new quizzes
	 */
	public void setQuizzes(JComboBox<String> quizzes) {
		this.quizzes = quizzes;
	}

	/**
	 * Gets the quizzes to grade.
	 *
	 * @return the quizzes to grade
	 */
	public JComboBox<String> getQuizzesToGrade() {
		return quizzesToGrade;
	}

	/**
	 * Sets the quizzes to grade.
	 *
	 * @param quizzesToGrade the new quizzes to grade
	 */
	public void setQuizzesToGrade(JComboBox<String> quizzesToGrade) {
		this.quizzesToGrade = quizzesToGrade;
	}

	/**
	 * Gets the new quiz name.
	 *
	 * @return the new quiz name
	 */
	public JTextField getNewQuizName() {
		return newQuizName;
	}

	/**
	 * Gets the new course id.
	 *
	 * @return the new course id
	 */
	public JTextField getNewCourseId() {
		return newCourseId;
	}

	/**
	 * Gets the new course name.
	 *
	 * @return the new course name
	 */
	public JTextField getNewCourseName() {
		return newCourseName;
	}

	/**
	 * Gets the courses ids.
	 *
	 * @return the courses ids
	 */
	public JComboBox<String> getCoursesIds() {
		return coursesIds;
	}

	/**
	 * Gets the removes the courses.
	 *
	 * @return the removes the courses
	 */
	public JComboBox<String> getRemoveCourses() {
		return removeCourses;
	}

	/**
	 * Files tree.
	 *
	 * @param file the file
	 * @return the default mutable tree node
	 */
	public static DefaultMutableTreeNode filesTree(File file) {
		String fileName = file.getName();
		DefaultMutableTreeNode treeNode;
		if (fileName.endsWith(".ser"))
			treeNode = new DefaultMutableTreeNode(fileName.subSequence(0, fileName.length() - 4));
		else
			treeNode = new DefaultMutableTreeNode(fileName);
		if (file.isDirectory())
			for (File child : file.listFiles()) {
				if (child.isDirectory() || file.getName().equals("Students"))
					treeNode.add(filesTree(child));
			}
		return treeNode;
	}

	/**
	 * Gets the creates the new quiz btn.
	 *
	 * @return the creates the new quiz btn
	 */
	public JButton getCreateNewQuizBtn() {
		return createNewQuizBtn;
	}

	/**
	 * Gets the edits the quiz btn.
	 *
	 * @return the edits the quiz btn
	 */
	public JButton getEditQuizBtn() {
		return editQuizBtn;
	}

	/**
	 * Gets the creates the new course btn.
	 *
	 * @return the creates the new course btn
	 */
	public JButton getCreateNewCourseBtn() {
		return createNewCourseBtn;
	}

	/**
	 * Gets the removes the course btn.
	 *
	 * @return the removes the course btn
	 */
	public JButton getRemoveCourseBtn() {
		return removeCourseBtn;
	}

	/**
	 * Gets the register student btn.
	 *
	 * @return the register student btn
	 */
	public JButton getRegisterStudentBtn() {
		return registerStudentBtn;
	}

	/**
	 * Gets the chosen remove student name lbl.
	 *
	 * @return the chosen remove student name lbl
	 */
	public JLabel getChosenRemoveStudentNameLbl() {
		return chosenRemoveStudentNameLbl;
	}

	/**
	 * Gets the removes the student btn.
	 *
	 * @return the removes the student btn
	 */
	public JButton getRemoveStudentBtn() {
		return removeStudentBtn;
	}

	/**
	 * Gets the grade btn.
	 *
	 * @return the grade btn
	 */
	public JButton getGradeBtn() {
		return gradeBtn;
	}

	/**
	 * Load quizzes to edit cb.
	 *
	 * @param coursesFiles the courses files
	 */
	public void loadQuizzesToEditCB(File[] coursesFiles) {

		quizzes.removeAllItems();
		if (coursesFiles != null)
			for (File child : coursesFiles)
				quizzes.addItem(child.getName());
		editQuizDialogPanel.revalidate();
	}

	/**
	 * Load quizzes to grade cb.
	 *
	 * @param coursesFiles the courses files
	 */
	public void loadQuizzesToGradeCB(File[] coursesFiles) {

		quizzesToGrade.removeAllItems();
		if (coursesFiles != null)
			for (File child : coursesFiles)
				quizzesToGrade.addItem(child.getName());
		gradeQuizDialogPanel.revalidate();
	}

}
