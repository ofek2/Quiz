package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import project.CustomTable;
import Controllers.InitialWindowController;
import Controllers.MainFrameController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;



public class ReportsView extends ViewPanel {
	
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
	public JTable table;
	public JScrollPane scrollPane;
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
				,20, width, height);
		
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
		
		btnBack = new JButton("Back");
		btnBack.setBounds((int)(width/1.5), 340, 79, 23);
		reportsInfoPanel.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(MainFrameController.view.getContentPane().getWidth()/4,70, MainFrameController.view.getContentPane().getWidth()/2, MainFrameController.view.getContentPane().getHeight()-100);
		//scrollPane.setBounds(50,70,300,250);
		add(scrollPane);
		
//		table = new CustomTable();
		scrollPane.setViewportView(table);
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
	public void btnBackAddListener(ActionListener listener)
	{
		btnBack.addActionListener(listener);
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable customTable) {
		table = customTable;
		scrollPane.setViewportView(table);
	}
}
