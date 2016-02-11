package Views;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.MainFrameController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class checkBoxFieldPanel extends JPanel{
	private JCheckBox checkBox;
	private JTextField textField;
	private JButton plusBtn;
	private JButton minusBtn;
	private int answerNumber;
	private JLabel aNumberLbl;
	
	public checkBoxFieldPanel() {
		super();
		setLayout(null);
		int width=MainFrameController.view.getWidth()-20;

		setPreferredSize(new Dimension(width*6/16, 35));
		setMaximumSize(new Dimension(width*12/16, 35));
		int panelWidth=getPreferredSize().width;
		aNumberLbl = new JLabel("1.");
		aNumberLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		aNumberLbl.setSize(17, 14);
		aNumberLbl.setLocation(10,9);
		add(aNumberLbl);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setSize(17,14);
		chckbxNewCheckBox.setLocation(22,9);
		add(chckbxNewCheckBox);
		
		textField = new JTextField();
		textField.setSize(panelWidth*11/16, 20);
		textField.setLocation(chckbxNewCheckBox.getX()+chckbxNewCheckBox.getWidth()+2,9);
		add(textField);
		
		
		plusBtn = new JButton("+");
		plusBtn.setMargin(new Insets(0, 0, 0, 0));
		plusBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		plusBtn.setSize(29, 23);
		plusBtn.setLocation(textField.getX()+textField.getWidth()+2,9);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setMargin(new Insets(0, 0, 0, 0));
		minusBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		minusBtn.setSize(29, 23);
		minusBtn.setLocation(plusBtn.getX()+plusBtn.getWidth()+2,9);
		add(minusBtn);
		
	
		
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
