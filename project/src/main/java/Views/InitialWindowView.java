package Views;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Cursor;

import javax.swing.JTextField;

import java.awt.Label;

import javax.swing.JComboBox;

import java.awt.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;

import Controllers.InitialWindowController;
import Controllers.MainFrameController;
import Controllers.qPanelController;
import Entities.Constants;
import Entities.StudentEntity;

public class InitialWindowView extends ViewPanel {
	private JMenu mnQuizMngMenu;
	private JMenuBar menuBar;
	private JMenu mnCourseManagement;
	
	private JPanel newQuizDialogPanel;
	private JComboBox<String> coursesIds;
	private JTextField newQuizName;
	private JButton createNewQuizBtn;
	
	private JPanel editQuizDialogPanel;
	private JComboBox<String> coursesIdsEdit;
	public JComboBox<String> quizzes;
	private JButton editQuizBtn;
	
	private JTextField newCourseId;
	private JPanel newCourseDialogPanel;
	private JButton createNewCourseBtn;
	private JTextField newCourseName;
	
	private JPanel removeCourseDialogPanel;
	private JComboBox<String> removeCourses;
	private JButton removeCourseBtn;
	
	private JTree tree;
	private JPanel registerStudentDialogPanel;
	private JComboBox<String> registerStudentCourseCB;
	private JTextField studentId;
	private JTextField studentName;
	private JTextField studentEmail;
	private JButton registerStudentBtn;
	
	
	private JPanel removeStudentDialogPanel;
	private JComboBox<String> removeStudentCourseCB;
	private JComboBox<String> removeStudentsIds;
	private JLabel chosenRemoveStudentNameLbl;
	private JButton removeStudentBtn;
	/**
	 * Create the panel.
	 */
	public InitialWindowView() {
		setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);
		
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
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setMaximumSize(new Dimension(2, 100));
		separator.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(separator);
		
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
		
		try {
			tree = new JTree(filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker")));
			tree.setBounds(0, 30, MainFrameController.view.getWidth(),MainFrameController.view.getHeight());
			add(tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<String> vec = new Vector<String>();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			vec.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		DefaultComboBoxModel<String> coursesIdsModel = new DefaultComboBoxModel<String>(vec);
		
		//New quiz dialog
		newQuizDialogPanel = new JPanel();
		newQuizDialogPanel.setLayout(null);
		newQuizDialogPanel.setBackground(Color.RED);
		newQuizDialogPanel.setSize(300, 220);
		newQuizDialogPanel.setVisible(true);
		
		Label label = new Label("Course Id:");
		label.setBounds(13, 28, 80, 19);
		newQuizDialogPanel.add(label);
		
		coursesIds = new JComboBox<String>(coursesIdsModel);
		coursesIds.setBounds(100, 27, 180, 20);
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
		createNewQuizBtn.setBounds(newQuizDialogPanel.getSize().width/2-createNewQuizBtn.getPreferredSize().width/2, 121, createNewQuizBtn.getPreferredSize().width, createNewQuizBtn.getPreferredSize().height);
		newQuizDialogPanel.add(createNewQuizBtn);
		
		//Edit quiz dialog
		editQuizDialogPanel = new JPanel();
		editQuizDialogPanel.setLayout(null);
		editQuizDialogPanel.setSize(300,220);
		editQuizDialogPanel.setBackground(Color.RED);
		
		Label label2 = new Label("Course Id:");
		label2.setBounds(13, 28, 80, 19);
		editQuizDialogPanel.add(label2);
		
		coursesIdsEdit = new JComboBox<String>(coursesIdsModel);
		coursesIdsEdit.setBounds(100, 27, 180, 20);
		coursesIdsEdit.setSelectedIndex(0);
		editQuizDialogPanel.add(coursesIdsEdit);
		
		Label label3 = new Label("Quiz Name:");
		label3.setBounds(13, 70, 80, 19);
		newQuizDialogPanel.add(label3);
		editQuizDialogPanel.add(label3);
		
		quizzes = new JComboBox<String>();
		quizzes.setBounds(100, 69, 180, 20);
		if(InitialWindowController.coursesFiles.size()>0)
		{
		for(File child: InitialWindowController.coursesFiles.get(0).getCourseFolder().listFiles())
			quizzes.addItem(child.getName());
		}
		else
			quizzes.addItem("");
		editQuizDialogPanel.add(quizzes);
		
		editQuizBtn = new JButton("Edit Quiz");
		editQuizBtn.setBounds(editQuizDialogPanel.getSize().width/2-editQuizBtn.getPreferredSize().width/2, 121, editQuizBtn.getPreferredSize().width, editQuizBtn.getPreferredSize().height);
		editQuizDialogPanel.add(editQuizBtn);
		editQuizDialogPanel.setVisible(true);
		
		//New course dialog
		newCourseDialogPanel = new JPanel();
		newCourseDialogPanel.setLayout(null);
		newCourseDialogPanel.setBackground(Color.CYAN);
		newCourseDialogPanel.setSize(300,220);
		
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
		createNewCourseBtn.setBounds(newQuizDialogPanel.getSize().width/2-createNewCourseBtn.getPreferredSize().width/2, 121, createNewCourseBtn.getPreferredSize().width, createNewCourseBtn.getPreferredSize().height);
		newCourseDialogPanel.add(createNewCourseBtn);
		
		//Remove course dialog
		removeCourseDialogPanel = new JPanel();
		removeCourseDialogPanel.setLayout(null);
		removeCourseDialogPanel.setBackground(Color.lightGray);
		removeCourseDialogPanel.setSize(300,150);
		
		JLabel lblCourse = new JLabel("Course Id:");
		lblCourse.setBounds(13, 20, 80, 19);
		removeCourseDialogPanel.add(lblCourse);
		
		removeCourses = new JComboBox<String>(coursesIdsModel);
		removeCourses.setBounds(100, 20, 186, 20);
		removeCourses.setSelectedIndex(0);
		removeCourseDialogPanel.add(removeCourses);
		
		removeCourseBtn = new JButton("remove course");
		removeCourseBtn.setBounds(removeCourseDialogPanel.getSize().width/2-removeCourseBtn.getPreferredSize().width/2, 60, removeCourseBtn.getPreferredSize().width, removeCourseBtn.getPreferredSize().height);
		removeCourseDialogPanel.add(removeCourseBtn);
		
		
		//Register Student
		
		registerStudentDialogPanel = new JPanel();
		registerStudentDialogPanel.setLayout(null);
		registerStudentDialogPanel.setBackground(Color.lightGray);
		registerStudentDialogPanel.setSize(270,300);
		
		JLabel lblCourse1 = new JLabel("Course Id:");
		lblCourse1.setBounds(4, 40, 100, 20);
		registerStudentDialogPanel.add(lblCourse1);
		
		registerStudentCourseCB = new JComboBox<String>(coursesIdsModel);
		registerStudentCourseCB.setBounds(105, 40, 150, 20);
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
		registerStudentBtn.setBounds(125-registerStudentBtn.getPreferredSize().width/2,220,
				registerStudentBtn.getPreferredSize().width,
				registerStudentBtn.getPreferredSize().height);	
		registerStudentDialogPanel.add(registerStudentBtn);
		
		removeStudentDialogPanel = new JPanel();
		removeStudentDialogPanel.setLayout(null);
		removeStudentDialogPanel.setBackground(Color.lightGray);
		removeStudentDialogPanel.setSize(270,300);
		
		JLabel lblRemoveStudentCourse = new JLabel("Course Id:");
		lblRemoveStudentCourse.setBounds(4, 20, 80, 20);
		removeStudentDialogPanel.add(lblRemoveStudentCourse);
		
		removeStudentCourseCB = new JComboBox<String>(coursesIdsModel);
		removeStudentCourseCB.setBounds(105, 20, 150, 20);
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
		
		if(InitialWindowController.coursesFiles.size()>0)
				loadStudents(removeStudentCourseCB.getSelectedIndex());
		else
			removeStudentsIds.addItem("");
		removeStudentDialogPanel.add(removeStudentsIds);
		removeStudentDialogPanel.add(chosenRemoveStudentNameLbl);
		
		removeStudentBtn = new JButton("Remove");
		removeStudentBtn.setBounds(125-removeStudentBtn.getPreferredSize().width/2,220,
				removeStudentBtn.getPreferredSize().width,
				removeStudentBtn.getPreferredSize().height);	
		removeStudentDialogPanel.add(removeStudentBtn);
		
		
	}
	public void loadStudents(int index)
	{
		File studentsFolder;
		try {
			removeStudentsIds.removeAllItems();
			studentsFolder = new File(InitialWindowController.
					coursesFiles.get(index).getCourseFolder().getCanonicalPath()+"/Students");
			for(File child: studentsFolder.listFiles())
				removeStudentsIds.addItem((String) child.getName()
						.subSequence(0,child.getName().length()-4));
			if(studentsFolder.listFiles().length>0)
				loadStudentNameToRemoveLbl(studentsFolder.listFiles()[0].getPath());
//				chosenRemoveStudentNameLbl.setText(loadStudentNameToRemoveLbl
//						(studentsFolder.listFiles()[0].getPath()));
			else
			{
				removeStudentsIds.addItem("");
				chosenRemoveStudentNameLbl.setText("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	public void loadStudentNameToRemoveLbl(String path)
	{
	FileInputStream fis;
	try {
		fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		StudentEntity result = (StudentEntity) ois.readObject();
		ois.close();
		chosenRemoveStudentNameLbl.setText(result.getStudentName());
//		return result.getStudentName();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	return "";
	}
	
	public JTextField getStudentId() {
		return studentId;
	}
	public JTextField getStudentName() {
		return studentName;
	}
	public JTextField getStudentEmail() {
		return studentEmail;
	}
	public void addQuizManagementListeners(ActionListener[] listener)
	{
		for (int i = 0; i < listener.length; i++) {
			mnQuizMngMenu.getItem(i).addActionListener(listener[i]);
		}
	}
	public void addCourseManagementListeners(ActionListener[] listener)
	{
		for (int i = 0; i < listener.length; i++) {
			mnCourseManagement.getItem(i).addActionListener(listener[i]);
		}
	}
	public void registerStudentBtnAddListener(ActionListener listener)
	{
		registerStudentBtn.addActionListener(listener);
	}
	public void createQuizBtnAddListener(ActionListener listener)
	{
		createNewQuizBtn.addActionListener(listener);
	}
	public void createCourseBtnAddListener(ActionListener listener)
	{
		createNewCourseBtn.addActionListener(listener);
	}
	public void removeCourseBtnAddListener(ActionListener listener)
	{
		removeCourseBtn.addActionListener(listener);
	}
	public void editQuizBtnAddListener(ActionListener listener)
	{
		editQuizBtn.addActionListener(listener);
	}
	public void removeStudentBtnAddListener(ActionListener listener)
	{
		removeStudentBtn.addActionListener(listener);
	}
	public void coursesIdsEditAddItemListener(ItemListener listener)
	{
		coursesIdsEdit.addItemListener(listener);
	}
	public void removeStudentCourseCBAddItemListener(ItemListener listener)
	{
		removeStudentCourseCB.addItemListener(listener);
	}
	public void removeStudentsIdsCBAddItemListener(ItemListener listener)
	{
		removeStudentsIds.addItemListener(listener);
	}
	public JPanel getNewQuizDialogPanel() {
		return newQuizDialogPanel;
	}
	public JPanel getEditQuizDialogPanel(){
		return editQuizDialogPanel;
	}
	public JPanel getNewCourseDialogPanel() {
		return newCourseDialogPanel;
	}
	public JPanel getRemoveCourseDialogPanel() {
		return removeCourseDialogPanel;
	}
	public JPanel getRemoveStudentDialogPanel() {
		return removeStudentDialogPanel;
	}
	public JPanel getRegisterStudentDialogPanel() {
		return registerStudentDialogPanel;
	}
	public JComboBox<String> getRegisterStudentCourseCB() {
		return registerStudentCourseCB;
	}
	public JComboBox<String> getRemoveStudentCourseCB() {
		return removeStudentCourseCB;
	}
	public JComboBox<String> getRemoveStudentsIds() {
		return removeStudentsIds;
	}
	public JTree getTree() {
		return tree;
	}
	public void setTree(JTree tree) {
		MouseListener[] mouseListener = this.tree.getMouseListeners();
		remove(this.tree);
		this.tree = tree;
		if(mouseListener.length>1)
		this.tree.addMouseListener((MouseAdapter) mouseListener[1]);
		add(this.tree);
		this.tree.setBounds(0, 30, MainFrameController.view.getWidth(),MainFrameController.view.getHeight());
		revalidate();		
	}
	
	public JComboBox<String> getCoursesIdsEdit() {
		return coursesIdsEdit;
	}
	public void setCoursesIdsEdit(JComboBox<String> coursesIdsEdit) {
		this.coursesIdsEdit = coursesIdsEdit;
	}
	public JComboBox<String> getQuizzes() {
		return quizzes;
	}
	public void setQuizzes(JComboBox<String> quizzes) {
		this.quizzes = quizzes;
	}
	public JTextField getNewQuizName() {
		return newQuizName;
	}
	public JTextField getNewCourseId() {
		return newCourseId;
	}
	public JTextField getNewCourseName() {
		return newCourseName;
	}
	public JComboBox getCoursesIds() {
		return coursesIds;
	}
	public JComboBox getRemoveCourses() {
		return removeCourses;
	}
	public static DefaultMutableTreeNode filesTree(File file)
	{
		String fileName = file.getName();
		DefaultMutableTreeNode treeNode;
		if(fileName.endsWith(".ser"))
			treeNode = new DefaultMutableTreeNode(fileName.subSequence(0,fileName.length()-4));
		else
			treeNode = new DefaultMutableTreeNode(fileName);
		if(file.isDirectory())
			for(File child: file.listFiles())
			{
				if(child.isDirectory()||file.getName().equals("Students"))
				treeNode.add(filesTree(child));
			}
		return treeNode;
	}
	
}
