package Views;

import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controllers.MainFrameController;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class checkBoxFieldPanel extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox answerCheckBox;
	private JTextField answerTextOption;
	private JButton plusBtn;
	private JButton minusBtn;
	private int answerNumber;
	private JLabel aNumberLbl;
	
	public checkBoxFieldPanel() {
		super();
		int width=MainFrameController.view.getWidth()-20;

		setPreferredSize(new Dimension(407, 30));
		setMaximumSize(new Dimension(500, 30));
		int panelWidth=getPreferredSize().width;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{29, 0, 228, 29, 29, 0};
		gridBagLayout.rowHeights = new int[]{23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		aNumberLbl = new JLabel("1.");
		aNumberLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_aNumberLbl = new GridBagConstraints();
		gbc_aNumberLbl.fill = GridBagConstraints.VERTICAL;
		gbc_aNumberLbl.insets = new Insets(0, 0, 0, 5);
		gbc_aNumberLbl.gridx = 0;
		gbc_aNumberLbl.gridy = 0;
		add(aNumberLbl, gbc_aNumberLbl);
		
		answerCheckBox = new JCheckBox("");
		answerCheckBox.setOpaque(false);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.VERTICAL;
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.EAST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 0;
		add(answerCheckBox, gbc_chckbxNewCheckBox);
		
		answerTextOption = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(answerTextOption, gbc_textField);
		
		
		plusBtn = new JButton("+");
		plusBtn.setMargin(new Insets(0, 0, 0, 0));
		plusBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		GridBagConstraints gbc_plusBtn = new GridBagConstraints();
		gbc_plusBtn.fill = GridBagConstraints.BOTH;
		gbc_plusBtn.gridx = 3;
		gbc_plusBtn.gridy = 0;
		add(plusBtn, gbc_plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setMargin(new Insets(0, 0, 0, 0));
		minusBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_minusBtn = new GridBagConstraints();
		gbc_minusBtn.fill = GridBagConstraints.BOTH;
		gbc_minusBtn.gridx = 4;
		gbc_minusBtn.gridy = 0;
		add(minusBtn, gbc_minusBtn);
//		qPanelController.setcheckboxFieldActionListeners(this);
		setOpaque(false);
		
	}
	public JCheckBox getAnswerCheckBox() {
		return answerCheckBox;
	}
	public void setAnswerCheckBox(JCheckBox checkBox) {
		this.answerCheckBox = checkBox;
	}
	public JTextField getAnswerTextOption() {
		return answerTextOption;
	}
	public void setAnswerTextOption(JTextField textField) {
		this.answerTextOption = textField;
	}
	public void setPlusBtn(JButton plusBtn) {
		this.plusBtn = plusBtn;
	}
	public void setMinusBtn(JButton minusBtn) {
		this.minusBtn = minusBtn;
	}
	public void setaNumberLbl(JLabel aNumberLbl) {
		this.aNumberLbl = aNumberLbl;
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
	public void addAnswerTextOptionKeyListener(KeyListener listener)
	{
		answerTextOption.addKeyListener(listener);
	}
	public void addChckbxNewCheckBox(ActionListener listener)
	{
		answerCheckBox.addActionListener(listener);
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
