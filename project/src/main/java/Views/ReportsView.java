package Views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import Controllers.InitialWindowController;
import Controllers.MainFrameController;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;



public class ReportsView extends ViewPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel reportsInfoPanel;
	private JLabel lblReportsCourseId;
	private JComboBox<String> reportsCourses;
	private JButton btnReportsShowGrades;
	private JLabel lblSearchStudent;
	private JLabel lblReportsStudentId;
	private JComboBox<String> reportsStudentsIds;
	private JButton btnSearchStudent;
	private JSeparator separator1;
	private JSeparator separator2;
	private JButton btnExportExcelFile;
	private JButton btnBack;
	public CTable table = null;
	public JScrollPane scrollPane;
	private JButton [] titleButtons;
	private JPanel emptyJpanel;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JLabel courseLabel;
	private int width= (int) ((MainFrameController.view.getContentPane().getWidth()-20)/4);
	private int height= (int) ((MainFrameController.view.getContentPane().getHeight())/2.5);
	private int reportsInfoPanelStartX = MainFrameController.view.getContentPane().getWidth()-20-width;
	private int reportsInfoPanelComponentsStartX = (int)(width/4);
	public ReportsView()
	{
		setLayout(null);
		
		
		reportsInfoPanel = new JPanel();
		reportsInfoPanel.setBackground(Color.LIGHT_GRAY);
		reportsInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(reportsInfoPanel);
		reportsInfoPanel.setLayout(null);
		reportsInfoPanel.setBounds(reportsInfoPanelStartX
				,35, width, height);
		
		lblReportsCourseId = new JLabel("Course id:");
		lblReportsCourseId.setBounds((int)(width/5)-20, 40, 80, 14);
		reportsInfoPanel.add(lblReportsCourseId);
		
		Vector<String> vec = new Vector<String>();
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			vec.add(InitialWindowController.coursesFiles.get(i).getCourseFolderName());
		
		reportsCourses = new JComboBox<String>();
		reportsCourses.setBounds((int)(width/5)+60, 40, 160, 20);
		for(String item:vec)
			reportsCourses.addItem(item);
		if(InitialWindowController.coursesFiles.size()>0)
			reportsCourses.setSelectedIndex(0);
		reportsInfoPanel.add(reportsCourses);
		
		btnReportsShowGrades = new JButton("Produce report");
		btnReportsShowGrades.setBounds((int)(width/3), 80, 130, 23);
		reportsInfoPanel.add(btnReportsShowGrades);
		
		separator1 = new JSeparator();
		separator1.setBounds((int)(width/5)-40, 110, 280, 2);
		reportsInfoPanel.add(separator1);
		
		lblSearchStudent = new JLabel("Search student");
		lblSearchStudent.setBounds((int)(width/3), 120, 100, 14);
		reportsInfoPanel.add(lblSearchStudent);
		
		lblReportsStudentId = new JLabel("Student id:");
		lblReportsStudentId.setBounds((int)(width/5)-20, 160, 80, 14);
		reportsInfoPanel.add(lblReportsStudentId);
		
		reportsStudentsIds = new JComboBox<String>();
		reportsStudentsIds.addItem("");
		reportsStudentsIds.setBounds((int)(width/5)+60, 160, 160, 20);
		reportsInfoPanel.add(reportsStudentsIds);
		
		btnSearchStudent = new JButton("Search");
		btnSearchStudent.setBounds((int)(width/3), 200, 130, 23);
		reportsInfoPanel.add(btnSearchStudent);
		
		separator2 = new JSeparator();
		separator2.setBounds((int)(width/5)-40, 240, 280, 2);
		reportsInfoPanel.add(separator2);
		
		btnExportExcelFile = new JButton("Export excel file");
		btnExportExcelFile.setBounds((int)(width/3), 280, 130, 23);
		reportsInfoPanel.add(btnExportExcelFile);
		
//		btnBack = new JButton("Back");
//		btnBack.setBounds((int)(width/1.5), 340, 79, 23);
//		reportsInfoPanel.add(btnBack);
		courseLabel = new JLabel();
		courseLabel.setBounds(reportsInfoPanelStartX-220,35, 200, 23);
		add(courseLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,35, MainFrameController.view.getContentPane().getWidth()/2, MainFrameController.view.getContentPane().getHeight()-70);
				
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		//scrollPane.setBounds(50,70,300,250);
		add(scrollPane);
//		JButton [] titleButtons = {new JButton("Quiz1"),new JButton("Quiz2"),new JButton("Quiz3"),new JButton("AVG")};
//		table = new CTable(titleButtons);
//		ArrayList<Object> labels = new ArrayList<>();
//		for(int i =0;i<5;i++)
//		for(int i =0;i<2;i++)
//		labels.add(new JLabel("shit"));
//		table.add(new RepRow(labels, 1));
//		scrollPane.setViewportView(table);
	}
	
	public JPanel getReportsInfoPanel() {
		return reportsInfoPanel;
	}
	public void setReportsInfoPanel(JPanel reportsInfoPanel) {
		this.reportsInfoPanel = reportsInfoPanel;
	}
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
