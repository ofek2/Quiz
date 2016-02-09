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

public class checkBoxFieldPanel extends JPanel{
	private JCheckBox checkBox;
	private JTextField textField;
	private JTextField textField_1;
	private JButton plusBtn;
	private JButton minusBtn;
	public checkBoxFieldPanel() {
		super();
		setLayout(null);
		setPreferredSize(new Dimension(241,35));
		setMaximumSize(new Dimension(241,35));
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(6, 7, 21, 23);
		add(chckbxNewCheckBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 7, 126, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		plusBtn = new JButton("+");
		plusBtn.setMargin(new Insets(0, 0, 0, 0));
		plusBtn.setFont(new Font("Serif", Font.PLAIN, 8));
		plusBtn.setBounds(166, 6, 37, 23);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setMargin(new Insets(0, 0, 0, 0));
		minusBtn.setFont(new Font("Arial", Font.PLAIN, 8));
		minusBtn.setBounds(204, 6, 37, 23);
		add(minusBtn);
		
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
	

	
}
