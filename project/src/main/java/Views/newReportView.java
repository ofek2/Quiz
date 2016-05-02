package Views;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import Controllers.InitialWindowController;
import Controllers.MainFrameController;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;

public class newReportView extends JPanel{
	private JComboBox<String>reportsStudentsIds;
	private JLabel lblReportsStudentId;
	private JLabel lblSearchStudent;
	private JButton btnReportsShowGrades;
	private JComboBox<String> reportsCourses;
	private JLabel lblReportsCourseId;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JButton btnSearchStudent;
	private JButton btnExportExcelFile;
	private JButton [] titleButtons;
	public CTable table = null;
	private JLabel courseLabel;
	public newReportView() {
		setPreferredSize(new Dimension(1000, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
		
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(100000, 30));
		menuBar.setMinimumSize(new Dimension(0, 30));
		menuBar.setMaximumSize(new Dimension(100000, 30));
		add(menuBar);
		
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JPanel headPanel = new JPanel();
		headPanel.setMaximumSize(new Dimension(32767, 40));
		headPanel.setPreferredSize(new Dimension(10, 40));
		add(headPanel);
		
		courseLabel = new JLabel("");
		headPanel.add(courseLabel);
		
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel reportsTablePanel = new JPanel();
		reportsTablePanel.setBorder(new EmptyBorder(10, 10, 0, 0));
		mainPanel.add(reportsTablePanel);
		reportsTablePanel.setLayout(new BoxLayout(reportsTablePanel, BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		
		reportsTablePanel.add(scrollPane);
		
		JPanel settingsPanel = new JPanel();
		mainPanel.add(settingsPanel);
		
		JInternalFrame internalFrame = new JInternalFrame("Reports Settings");
		internalFrame.getContentPane().setBackground(Color.WHITE);
		internalFrame.setPreferredSize(new Dimension(320, 320));
		settingsPanel.add(internalFrame);
		internalFrame.getContentPane().setLayout(new BoxLayout(internalFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel coursePanel = new JPanel();
		coursePanel.setOpaque(false);
		coursePanel.setBorder(new TitledBorder(null, "Course", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame.getContentPane().add(coursePanel);
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
		
		JPanel courseTitlePanel = new JPanel();
		courseTitlePanel.setOpaque(false);
		courseTitlePanel.setPreferredSize(new Dimension(10, 50));
		courseTitlePanel.setMaximumSize(new Dimension(32767, 50));
		coursePanel.add(courseTitlePanel);
		courseTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblReportsCourseId = new JLabel("Course ID:");
		courseTitlePanel.add(lblReportsCourseId);
		//Loading courses ids
		Vector<String> vec = new Vector<String>();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			vec.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		
		reportsCourses = new JComboBox<String>();
		reportsCourses.setPreferredSize(new Dimension(200, 20));
		
		for(String item:vec)
			reportsCourses.addItem(item);
		if(InitialWindowController.coursesFiles.size()>0)
			reportsCourses.setSelectedIndex(0);
		
		courseTitlePanel.add(reportsCourses);
		//
		JPanel produceRepBtnPanel = new JPanel();
		produceRepBtnPanel.setMaximumSize(new Dimension(32767, 30));
		produceRepBtnPanel.setPreferredSize(new Dimension(10, 30));
		produceRepBtnPanel.setOpaque(false);
		coursePanel.add(produceRepBtnPanel);
		
		btnReportsShowGrades = new JButton("Produce report");
		produceRepBtnPanel.add(btnReportsShowGrades);
		
		JPanel studentPanel = new JPanel();
		studentPanel.setOpaque(false);
		studentPanel.setBorder(new TitledBorder(null, "Student", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame.getContentPane().add(studentPanel);
		studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
		
		JPanel studentTitlePanel = new JPanel();
		studentTitlePanel.setMaximumSize(new Dimension(32767, 20));
		studentTitlePanel.setOpaque(false);
		studentPanel.add(studentTitlePanel);
		
		lblSearchStudent = new JLabel("Search Student");
		lblSearchStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentTitlePanel.add(lblSearchStudent);
		
		JPanel produceStudRepPanel = new JPanel();
		produceStudRepPanel.setPreferredSize(new Dimension(10, 50));
		produceStudRepPanel.setOpaque(false);
		produceStudRepPanel.setMaximumSize(new Dimension(32767, 50));
		studentPanel.add(produceStudRepPanel);
		produceStudRepPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblReportsStudentId = new JLabel("Student ID:");
		produceStudRepPanel.add(lblReportsStudentId);
		
		reportsStudentsIds = new JComboBox();
		reportsStudentsIds.setPreferredSize(new Dimension(200, 20));
		produceStudRepPanel.add(reportsStudentsIds);
		
		JPanel studentSearchBtnPanel = new JPanel();
		studentSearchBtnPanel.setMaximumSize(new Dimension(32767, 40));
		studentSearchBtnPanel.setPreferredSize(new Dimension(10, 30));
		studentSearchBtnPanel.setOpaque(false);
		studentPanel.add(studentSearchBtnPanel);
		
		btnSearchStudent = new JButton("Search");
		btnSearchStudent.setPreferredSize(new Dimension(105, 23));
		studentSearchBtnPanel.add(btnSearchStudent);
		
		JPanel xcelExportPanel = new JPanel();
		xcelExportPanel.setBorder(new TitledBorder(null, "Export", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		xcelExportPanel.setOpaque(false);
		xcelExportPanel.setMaximumSize(new Dimension(32767, 50));
		xcelExportPanel.setMinimumSize(new Dimension(10, 50));
		FlowLayout flowLayout = (FlowLayout) xcelExportPanel.getLayout();
		flowLayout.setVgap(20);
		internalFrame.getContentPane().add(xcelExportPanel);
		
		btnExportExcelFile = new JButton("Export excel file");
		xcelExportPanel.add(btnExportExcelFile);
		internalFrame.setVisible(true);
	}
//	public JPanel getReportsInfoPanel() {
//		return reportsInfoPanel;
//	}
//	public void setReportsInfoPanel(JPanel reportsInfoPanel) {
//		this.reportsInfoPanel = reportsInfoPanel;
//	}
	public JComboBox<String> getReportsCourses() {
		return reportsCourses;
	}
	public void setReportsCourses(JComboBox<String> reportsCourses) {
		this.reportsCourses = reportsCourses;
	}
	public JComboBox<String> getReportsStudentsIds() {
		return reportsStudentsIds;
	}
	public void setReportsStudentsIds(JComboBox<String> reportsStudentsIds) {
		this.reportsStudentsIds = reportsStudentsIds;
	}	
	public JButton[] getTitleButtons() {
		return titleButtons;
	}

//	public void setTitleButtons(int size,ArrayList<String> quizzesNames) {
//		titleButtons = new JButton[size];
//		for (int i = 0; i < titleButtons.length; i++) {
//			titleButtons[i] = new JButton(quizzesNames.get(i));
//		}
////		if (table!=null) {
////			System.out.println("12");
////			table.removeAllItems();
////			scrollPane.setViewportView(table);
//////			revalidate();
////		}
////		else{
//		CTable table = new CTable(titleButtons);
//		ArrayList<Object> labels = new ArrayList<>();
////		for(int i =0;i<5;i++)
//		for(int i =0;i<size+1;i++)
//		labels.add(new JLabel("shit"));
//		table.add(new RepRow(labels, 1));
//		scrollPane.setViewportView(table);
////		revalidate();
////		}
//	}

	public void setTitleButtons(JButton[] titleButtons) {
		this.titleButtons = titleButtons;
	}
	
	public CTable getTable() {
		return table;
	}

	public void setTable(CTable table) {
		this.table = table;
	}
//	public void removeAllItems()
//	{
//		for (int i = 0; i < table.getRows().size(); i++) {
//			table.remove((Component)table.getRows().get(0));
//		}
//		
//	}
	
	public JLabel getCourseLabel() {
		return courseLabel;
	}

	public void setCourseLabel(JLabel courseLabel) {
		this.courseLabel = courseLabel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void btnReportsShowGradesAddListener(ActionListener listener)
	{
		btnReportsShowGrades.addActionListener(listener);
	}
	public void btnSearchStudentAddListener(ActionListener listener)
	{
		btnSearchStudent.addActionListener(listener);
	}
	public void btnExportExcelFileAddListener(ActionListener listener)
	{
		btnExportExcelFile.addActionListener(listener);
	}
	public void mntmExitAddListener(ActionListener listener)
	{
		mnFile.getItem(0).addActionListener(listener);
	}
	
}
