package Views;

import javax.swing.ComboBoxModel;
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

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Cursor;

import javax.swing.JTextField;

import java.awt.Label;

import javax.swing.JComboBox;

import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;

import Controllers.InitialWindowController;
import Controllers.MainFrameController;
import Controllers.qPanelController;
import Entities.Constants;

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
		
		newQuizDialogPanel = new JPanel();
		newQuizDialogPanel.setLayout(null);
		newQuizDialogPanel.setBackground(Color.RED);
		newQuizDialogPanel.setSize(300, 220);
		newQuizDialogPanel.setVisible(true);
		
				
		
		Label label = new Label("Course Code:");
		label.setBounds(13, 28, 80, 19);
		newQuizDialogPanel.add(label);
		
		coursesIds = new JComboBox<String>();
		coursesIds.setBounds(100, 27, 180, 20);
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			coursesIds.addItem(InitialWindowController.coursesFiles.get(i).getCourseFolderName());		
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
		
		editQuizDialogPanel = new JPanel();
		editQuizDialogPanel.setLayout(null);
		editQuizDialogPanel.setSize(300,220);
		editQuizDialogPanel.setBackground(Color.RED);
		
		Label label2 = new Label("Course Code:");
		label2.setBounds(13, 28, 80, 19);
		editQuizDialogPanel.add(label2);
		
		coursesIdsEdit = new JComboBox<String>();
		coursesIdsEdit.setBounds(100, 27, 180, 20);
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			coursesIdsEdit.addItem(InitialWindowController.coursesFiles.get(i).getCourseFolderName());		
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
		
		newCourseDialogPanel = new JPanel();
		newCourseDialogPanel.setLayout(null);
		newCourseDialogPanel.setBackground(Color.CYAN);
		newCourseDialogPanel.setSize(220,220);
		
		
		JLabel lblCourseId = new JLabel("Course Id:");
		lblCourseId.setBounds(10, 45, 70, 14);
		newCourseDialogPanel.add(lblCourseId);
		
		newCourseId = new JTextField();
		newCourseId.setBounds(93, 42, 86, 20);
		newCourseDialogPanel.add(newCourseId);
		newCourseId.setColumns(10);
		
		createNewCourseBtn = new JButton("Create Course");
		createNewCourseBtn.setBounds(newQuizDialogPanel.getSize().width/2-createNewCourseBtn.getPreferredSize().width/2, 121, createNewCourseBtn.getPreferredSize().width, createNewCourseBtn.getPreferredSize().height);
		newCourseDialogPanel.add(createNewCourseBtn);
		
		JLabel lblNewLabel = new JLabel("Course Name:");
		lblNewLabel.setBounds(10, 81, 84, 14);
		newCourseDialogPanel.add(lblNewLabel);
		
		newCourseName = new JTextField();
		newCourseName.setBounds(93, 78, 86, 20);
		newCourseDialogPanel.add(newCourseName);
		newCourseName.setColumns(10);
		
		removeCourseDialogPanel = new JPanel();
		removeCourseDialogPanel.setLayout(null);
		removeCourseDialogPanel.setBackground(Color.lightGray);
		removeCourseDialogPanel.setSize(220,220);
		
		
		removeCourses = new JComboBox<String>();
		removeCourses.setBounds(100, 27, 86, 20);
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			removeCourses.addItem(InitialWindowController.coursesFiles.get(i).getCourseFolderName());		
		removeCourses.setSelectedIndex(0);
		removeCourseDialogPanel.add(removeCourses);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setBounds(4, 39, 46, 14);
		removeCourseDialogPanel.add(lblCourse);
		
		removeCourseBtn = new JButton("remove course");
		removeCourseBtn.setBounds(42, 120, 109, 23);
		removeCourseDialogPanel.add(removeCourseBtn);
		
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
	public void coursesIdsEditAddItemListener(ItemListener listener)
	{
		coursesIdsEdit.addItemListener(listener);
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
	
	public JTree getTree() {
		return tree;
	}
	public void setTree(JTree tree) {
		remove(this.tree);
		this.tree = tree;
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
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(file.getName());
		if(file.isDirectory())
			for(File child: file.listFiles())
			{
				if(child.isDirectory())
				treeNode.add(filesTree(child));
			}
		return treeNode;
	}
	
}
