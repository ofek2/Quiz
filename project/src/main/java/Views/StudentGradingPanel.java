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

/**
 * The Class StudentGradingPanel.
 * This class holds the items of a student in a row in the {@link GradingWindowView}
 */
public class StudentGradingPanel extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl studentid. */
	private JLabel lblStudentid;
	
	/** The grade btn. */
	private JButton gradeBtn;
	
	/** The lbl grade. */
	private JLabel lblGrade;
	
	/** The Constant notGraded. */
	public final static String notGraded="Not yet graded";
	
	/**
	 * Instantiates a new student grading panel.
	 *
	 * @param studentId the student id
	 */
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
	
	/**
	 * Grade btn add action listener.
	 *
	 * @param listener the listener
	 */
	public void gradeBtnAddActionListener(ActionListener listener)
	{
		gradeBtn.addActionListener(listener);
	}
	
	/**
	 * Sets the grade lbl.
	 *
	 * @param text the new grade lbl
	 */
	public void setGradeLbl(String text)
	{
		lblGrade.setText(text);
	}
	
	/**
	 * Gets the lbl studentid.
	 *
	 * @return the lbl studentid
	 */
	public JLabel getLblStudentid() {
		return lblStudentid;
	}
	
	/**
	 * Sets the lbl studentid.
	 *
	 * @param lblStudentid the new lbl studentid
	 */
	public void setLblStudentid(JLabel lblStudentid) {
		this.lblStudentid = lblStudentid;
	}
	
	/**
	 * Gets the grade btn.
	 *
	 * @return the grade btn
	 */
	public JButton getGradeBtn() {
		return gradeBtn;
	}
	
	/**
	 * Sets the grade btn.
	 *
	 * @param gradeBtn the new grade btn
	 */
	public void setGradeBtn(JButton gradeBtn) {
		this.gradeBtn = gradeBtn;
	}
	
	/**
	 * Gets the lbl grade.
	 *
	 * @return the lbl grade
	 */
	public JLabel getLblGrade() {
		return lblGrade;
	}
	
	/**
	 * Sets the lbl grade.
	 *
	 * @param lblGrade the new lbl grade
	 */
	public void setLblGrade(JLabel lblGrade) {
		this.lblGrade = lblGrade;
	}
	
}
