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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javafx.scene.shape.Box;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.Cursor;

public class QuizCreationView extends ViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton addBtn;
	public JPanel panel;
	private JPanel headPanel;
	private JLabel quizName;
	private JLabel courseName;
	private JMenuBar menuBar;
	private JMenu fileMenu;

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

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

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

	
	public void addBtnAddListener(ActionListener listener) {
		addBtn.addActionListener(listener);
	}


	public void addFileMenuListeners(ActionListener[] listener) {
		for (int i = 0; i < listener.length; i++) {
			fileMenu.getItem(i).addActionListener(listener[i]);
		}
	}

	public JLabel getQuizName() {
		return quizName;
	}

	public JLabel getCourseName() {
		return courseName;
	}

}
