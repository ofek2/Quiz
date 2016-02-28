package Views;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class StudentGradingPanel extends JPanel{
	private JLabel lblStudentid;
	private JLabel lblStudentname;
	private JButton gradeBtn;
	private JLabel lblGrade;
	public StudentGradingPanel(String studentId)
//			,String studentName)
	{
		setLayout(new GridLayout(0, 3, 0, 0));
		setMaximumSize(new Dimension(10000, 40));
		lblStudentid = new JLabel(studentId);
		lblStudentid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblStudentid.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStudentid);
		
//		lblStudentname = new JLabel(studentName);
//		lblStudentname.setBorder(new LineBorder(new Color(0, 0, 0)));
//		lblStudentname.setHorizontalAlignment(SwingConstants.CENTER);
//		add(lblStudentname);
		
		lblGrade = new JLabel("Not yet graded");
		lblGrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblGrade);
		
		gradeBtn = new JButton("Grade");
		gradeBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(gradeBtn);
		
	}
	public void gradeBtnAddActionListener(ActionListener listener)
	{
		gradeBtn.addActionListener(listener);
	}
	public void setGradeLbl(String text)
	{
		lblGrade.setText(text);
	}
}
