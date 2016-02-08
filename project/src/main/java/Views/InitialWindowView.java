package Views;

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
import javax.swing.JLabel;
import javax.swing.JButton;

public class InitialWindowView extends ViewPanel {
	private JMenu mnQuizMngMenu;
	private JMenuBar menuBar;
	private JMenu mnCourseManagement;
	private JPanel newQuizDialogPanel;
	private JComboBox courseCode;
	private JTextField newQuizName;
	private Button createNewQuiz;
	private JTree tree;
	private JTextField newCourseId;
	private JPanel newCourseDialogPanel;
	private Button createNewCourse;
	/**
	 * Create the panel.
	 */
	public InitialWindowView() {
		setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 800, 30);
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
		
		tree = new JTree();
		tree.setBounds(0, 30, 800, 570);
		add(tree);
		
		newQuizDialogPanel = new JPanel();
		newQuizDialogPanel.setBackground(Color.RED);
		newQuizDialogPanel.setBounds(137, 193, 200, 168);
		newQuizDialogPanel.setVisible(false);
		tree.add(newQuizDialogPanel);
		newQuizDialogPanel.setLayout(null);
		
//		
		
		Label label = new Label("Course Code:");
		label.setBounds(13, 28, 80, 19);
		newQuizDialogPanel.add(label);
		
		courseCode = new JComboBox();
		courseCode.setBounds(100, 27, 86, 20);
		courseCode.addItem("-");
		courseCode.addItem("1");
		courseCode.addItem("2");
		courseCode.addItem("3");
		courseCode.setSelectedIndex(0);
		newQuizDialogPanel.add(courseCode);
		
		Label label_1 = new Label("Quiz Name:");
		label_1.setBounds(13, 70, 80, 19);
		newQuizDialogPanel.add(label_1);
		
		newQuizName = new JTextField();
		newQuizName.setBounds(100, 70, 86, 20);
		newQuizDialogPanel.add(newQuizName);
		newQuizName.setColumns(10);
		
		createNewQuiz = new Button("Create Quiz");
		createNewQuiz.setBounds(66, 121, 70, 19);
		newQuizDialogPanel.add(createNewQuiz);
		
		newCourseDialogPanel = new JPanel();
		tree.add(newCourseDialogPanel);
		newCourseDialogPanel.setBackground(Color.CYAN);
		newCourseDialogPanel.setBounds(406, 193, 200, 168);
		newCourseDialogPanel.setLayout(null);
		
		JLabel lblCourseId = new JLabel("Course Id:");
		lblCourseId.setBounds(10, 45, 70, 14);
		newCourseDialogPanel.add(lblCourseId);
		
		newCourseId = new JTextField();
		newCourseId.setBounds(79, 42, 86, 20);
		newCourseDialogPanel.add(newCourseId);
		newCourseId.setColumns(10);
		
		createNewCourse = new Button("Create Course");
		createNewCourse.setBounds(35, 121, 130, 23);
		newCourseDialogPanel.add(createNewCourse);
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
	public void addCourseCodeListener(ItemListener listener)
	{
		courseCode.addItemListener(listener);
	}
	public void createCourseBtnAddListener(ActionListener listener)
	{
		createNewQuiz.addActionListener(listener);
	}
	
	public JPanel getNewQuizDialogPanel() {
		return newQuizDialogPanel;
	}
	public JTree getTree() {
		return tree;
	}
	public JTextField getNewQuizName() {
		return newQuizName;
	}
}
