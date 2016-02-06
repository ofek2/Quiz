package project;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class checkBoxFieldPanel extends JPanel{
	private JCheckBox checkBox;
	private JTextField textField;
	private JTextField textField_1;
	private JButton plusBtn;
	private JButton button;
	public checkBoxFieldPanel() {
		super();
		setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(6, 7, 21, 23);
		add(chckbxNewCheckBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 7, 126, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		plusBtn = new JButton("+");
		plusBtn.setFont(new Font("Arial", Font.PLAIN, 8));
		plusBtn.setBounds(166, 6, 37, 23);
		add(plusBtn);
		
		button = new JButton("-");
		button.setFont(new Font("Arial", Font.PLAIN, 8));
		button.setBounds(204, 6, 37, 23);
		add(button);
		
	}
	public JCheckBox getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(JCheckBox checkBox) {
		this.checkBox = checkBox;
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
