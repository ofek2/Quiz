package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class StudentGradingPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblStudentid;
	
	private JButton gradeBtn;
	private JLabel lblGrade;
	
	public final static String notGraded="Not yet graded";
	public StudentGradingPanel(String studentId)
	{
		setLayout(new GridLayout(0, 3, 0, 0));
		setMaximumSize(new Dimension(10000, 40));
		lblStudentid = new JLabel(studentId);
		lblStudentid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblStudentid.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStudentid);
		
		lblGrade = new JLabel(notGraded);
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
	public JLabel getLblStudentid() {
		return lblStudentid;
	}
	public void setLblStudentid(JLabel lblStudentid) {
		this.lblStudentid = lblStudentid;
	}
	
	public JButton getGradeBtn() {
		return gradeBtn;
	}
	public void setGradeBtn(JButton gradeBtn) {
		this.gradeBtn = gradeBtn;
	}
	public JLabel getLblGrade() {
		return lblGrade;
	}
	public void setLblGrade(JLabel lblGrade) {
		this.lblGrade = lblGrade;
	}
	
}
