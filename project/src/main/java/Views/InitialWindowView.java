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
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Cursor;

public class InitialWindowView extends ViewPanel {
	private JMenu mnQuizMngMenu;
	private JMenuBar menuBar;
	private JMenu mnCourseManagement;

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
		
		JTree tree = new JTree();
		tree.setBounds(0, 30, 800, 570);
		add(tree);
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
}
