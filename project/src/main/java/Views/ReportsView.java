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
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import Controllers.InitialWindowController;
import Controllers.MainFrameController;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuItem;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class ReportsView extends ViewPanel{
	private JComboBox<String>reportsStudentsIds;
	private JLabel lblReportsStudentId;
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
	private JMenu mnHelpMenu;
	public ReportsView() {
		setBackground(Color.WHITE);
//		setPreferredSize(new Dimension(1000, 700));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
		
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(100000, 30));
		menuBar.setMinimumSize(new Dimension(0, 30));
		menuBar.setMaximumSize(new Dimension(100000, 30));
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		add(menuBar);
		
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
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
		
		JPanel headPanel = new JPanel();
		headPanel.setOpaque(false);
		headPanel.setMaximumSize(new Dimension(32767, 40));
		headPanel.setPreferredSize(new Dimension(10, 40));
		add(headPanel);
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		courseLabel = new JLabel("");
		courseLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		headPanel.add(courseLabel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel reportsTablePanel = new JPanel();
		reportsTablePanel.setOpaque(false);
		reportsTablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.add(reportsTablePanel);
		reportsTablePanel.setLayout(new BoxLayout(reportsTablePanel, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		reportsTablePanel.add(scrollPane);
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 20, 10, 20), null));
		settingsPanel.setOpaque(false);
		mainPanel.add(settingsPanel);
		//Loading courses ids
		Vector<String> vec = new Vector<String>();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			vec.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		System.out.println(InitialWindowController.coursesFiles.size());
		
		
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		
		JPanel coursePanel = new JPanel();
		settingsPanel.add(coursePanel);
		coursePanel.setOpaque(false);
		coursePanel.setBorder(new TitledBorder(null, "Course", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14), null));
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
		
		JPanel courseTitlePanel = new JPanel();
		courseTitlePanel.setOpaque(false);
		courseTitlePanel.setPreferredSize(new Dimension(10, 50));
		courseTitlePanel.setMaximumSize(new Dimension(32767, 50));
		coursePanel.add(courseTitlePanel);
		courseTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblReportsCourseId = new JLabel("Course ID:");
		courseTitlePanel.add(lblReportsCourseId);
		
		reportsCourses = new JComboBox<String>();
		reportsCourses.setPreferredSize(new Dimension(200, 20));
		
		for(String item:vec)
			reportsCourses.addItem(item);
		if(InitialWindowController.coursesFiles.size()>0)
			reportsCourses.setSelectedIndex(0);
		
		courseTitlePanel.add(reportsCourses);
		//
		JPanel produceRepBtnPanel = new JPanel();
		produceRepBtnPanel.setMaximumSize(new Dimension(32767, 40));
		produceRepBtnPanel.setPreferredSize(new Dimension(10, 30));
		produceRepBtnPanel.setOpaque(false);
		coursePanel.add(produceRepBtnPanel);
		
		btnReportsShowGrades = new JButton("Produce report");
		produceRepBtnPanel.add(btnReportsShowGrades);
		
		JPanel studentPanel = new JPanel();
		settingsPanel.add(studentPanel);
		studentPanel.setOpaque(false);
		studentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search Specific Student", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14), new Color(0, 0, 0)));
		studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
		
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
		settingsPanel.add(xcelExportPanel);
		xcelExportPanel.setBorder(new TitledBorder(null, "Export", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14), null));
		xcelExportPanel.setOpaque(false);
		xcelExportPanel.setMaximumSize(new Dimension(32767, 100));
		xcelExportPanel.setMinimumSize(new Dimension(10, 50));
		FlowLayout flowLayout = (FlowLayout) xcelExportPanel.getLayout();
		flowLayout.setVgap(20);
		
		btnExportExcelFile = new JButton("Export excel file");
		xcelExportPanel.add(btnExportExcelFile);
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
	public void setViewPortForScrollPane(CTable table2) {
		// TODO Auto-generated method stub
		scrollPane.setViewportView(table2);
	}
	public void addHelpActionListener(ActionListener listener)
	{
		mnHelpMenu.getItem(0).addActionListener(listener);
	}
	
}
