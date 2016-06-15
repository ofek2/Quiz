package Views;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controllers.QuizCreationController;
import javafx.scene.shape.Box;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Cursor;

/**
 * The Class QuizCreationView.
 * This class is used for creating a quiz.
 * This class is a boundary controlled by {@link QuizCreationController}.
 */
public class QuizCreationView extends ViewPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The add button. */
	public JButton addBtn;
	
	/** The panel. */
	public JPanel panel;
	
	/** The head panel. */
	private JPanel headPanel;
	
	/** The quiz name. */
	private JLabel quizName;
	
	/** The course name. */
	private JLabel courseName;
	
	/** The menu bar. */
	private JMenuBar menuBar;
	
	/** The file menu. */
	private JMenu fileMenu;
	
	/** The help menu. */
	private JMenu mnHelpMenu;

	/**
	 * Instantiates a new quiz creation view.
	 */
	// private JSpinner percentageFromFgrade;
	public QuizCreationView() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setPreferredSize(new Dimension(100000, 30));
		menuBar.setMaximumSize(new Dimension(100000, 30));
		menuBar.setMinimumSize(new Dimension(0, 30));
		add(menuBar);

		fileMenu = new JMenu("File");
		fileMenu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar.add(fileMenu);

		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		JMenuItem previewItem = new JMenuItem("Preview");
		fileMenu.add(previewItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator1.setBackground(Color.LIGHT_GRAY);
		separator1.setMaximumSize(new Dimension(2, 100));
		separator1.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(separator1);
		
		mnHelpMenu = new JMenu("Help");
		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mnHelpMenu.add(mntmHelpContents);

		menuBar.add(mnHelpMenu);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		JScrollPane jsp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(16);

		add(jsp);

		headPanel = new JPanel();
		headPanel.setMaximumSize(new Dimension(700, 50));
		TitledBorder title = BorderFactory.createTitledBorder("Quiz Info");
		title.setTitleJustification(TitledBorder.CENTER);
		headPanel.setBorder(title);
		headPanel.setOpaque(false);
		headPanel.setLayout(new GridLayout(1, 4));

		JLabel courseLbl = new JLabel("Course Name: ");
		courseLbl.setFont(new Font("Arial", Font.BOLD, 17));
		courseLbl.setHorizontalAlignment(JLabel.CENTER);
		headPanel.add(courseLbl);
		courseName = new JLabel("course");
		courseName.setFont(new Font("Arial", Font.PLAIN, 17));
		courseName.setHorizontalAlignment(JLabel.LEFT);
		headPanel.add(courseName);

		JLabel quizLbl = new JLabel("Quiz Name: ");
		quizLbl.setFont(new Font("Arial", Font.BOLD, 17));
		quizLbl.setHorizontalAlignment(JLabel.CENTER);
		headPanel.add(quizLbl);
		quizName = new JLabel("test");
		quizName.setFont(new Font("Arial", Font.PLAIN, 17));
		quizName.setHorizontalAlignment(JLabel.LEFT);
		headPanel.add(quizName);
		// headPanel.add(percentage);
		// percentageFromFgrade = new JSpinner(new
		// SpinnerNumberModel(0.25,0,1,.01));
		// JSpinner.NumberEditor editor = new
		// JSpinner.NumberEditor(percentageFromFgrade,"0%");
		// percentageFromFgrade.setEditor(editor);
		//
		// headPanel.add(percentageFromFgrade);

		// JLabel percentage = new JLabel("Quiz Weight: ");
		// percentage.setFont(new Font("Arial", Font.BOLD, 17));
		// percentage.setHorizontalAlignment(JLabel.CENTER);
		// headPanel.add(percentage);
		// percentageFromFgrade = new JSpinner(new
		// SpinnerNumberModel(0.25,0,1,.01));
		// JSpinner.NumberEditor editor = new
		// JSpinner.NumberEditor(percentageFromFgrade,"0%");
		// percentageFromFgrade.setEditor(editor);
		//
		// headPanel.add(percentageFromFgrade);
		panel.add(headPanel);

		addBtn = new JButton("Add question");
		addBtn.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(addBtn);
		addBtn.setAlignmentY(0.0f);
		setVisible(true);
	}

	
	/**
	 * Adds the btn add listener.
	 *
	 * @param listener the listener
	 */
	public void addBtnAddListener(ActionListener listener) {
		addBtn.addActionListener(listener);
	}


	/**
	 * Adds the file menu listeners.
	 *
	 * @param listener the listener
	 */
	public void addFileMenuListeners(ActionListener[] listener) {
		for (int i = 0; i < listener.length; i++) {
			fileMenu.getItem(i).addActionListener(listener[i]);
		}
	}

	/**
	 * Gets the quiz name.
	 *
	 * @return the quiz name
	 */
	public JLabel getQuizName() {
		return quizName;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public JLabel getCourseName() {
		return courseName;
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
}
