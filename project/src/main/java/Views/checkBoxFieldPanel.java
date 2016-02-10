package Views;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class checkBoxFieldPanel extends JPanel{
	private JCheckBox checkBox;
	private JTextField textField;
	private JTextField textField_1;
	private JButton plusBtn;
	private JButton minusBtn;
	private int answerNumber;
	private JLabel aNumberLbl;
	public checkBoxFieldPanel() {
		super();
		setLayout(null);
		setPreferredSize(new Dimension(260, 35));
		setMaximumSize(new Dimension(520, 35));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(21, 7, 21, 23);
		add(chckbxNewCheckBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(48, 7, 126, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		plusBtn = new JButton("+");
		plusBtn.setMargin(new Insets(0, 0, 0, 0));
		plusBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		plusBtn.setBounds(181, 6, 37, 23);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setMargin(new Insets(0, 0, 0, 0));
		minusBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		minusBtn.setBounds(219, 6, 37, 23);
		add(minusBtn);
		
		aNumberLbl = new JLabel("1.");
		aNumberLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		aNumberLbl.setBounds(10, 12, 46, 14);
		add(aNumberLbl);
		
	}
	public JLabel getaNumberLbl() {
		return aNumberLbl;
	}
	public void setaNumberLbl(String string) {
		this.aNumberLbl.setText(string);
	}
	public void plusBtnAddListener(ActionListener listener)
	{
		plusBtn.addActionListener(listener);
	}
	public void minusBtnAddListener(ActionListener listener)
	{
		minusBtn.addActionListener(listener);
	}
	public JButton getPlusBtn() {
		return plusBtn;
	}
	public JButton getMinusBtn() {
		return minusBtn;
	}
	public int getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
}
