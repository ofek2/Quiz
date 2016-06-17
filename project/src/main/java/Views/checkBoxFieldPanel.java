package Views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The Class checkBoxFieldPanel.
 * This class consists of a JCheckBox ,JTextField and "+","-" buttons.
 * Used for adding choices when creating a multiple choices question.
 */
public class checkBoxFieldPanel extends JPanel implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The answer check box. */
	private JCheckBox answerCheckBox;
	
	/** The answer text option. */
	private JTextField answerTextOption;
	
	/** The plus button. */
	private JButton plusBtn;
	
	/** The minus button. */
	private JButton minusBtn;
	
	/** The answer number. */
	private int answerNumber;
	
	/** The a number label. */
	private JLabel aNumberLbl;
	
	/**
	 * Instantiates a new check box field panel.
	 */
	public checkBoxFieldPanel() {
		super();
		setPreferredSize(new Dimension(407, 30));
		setMaximumSize(new Dimension(500, 30));
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
		plusBtn.setBackground(Color.WHITE);
		GridBagConstraints gbc_plusBtn = new GridBagConstraints();
		gbc_plusBtn.fill = GridBagConstraints.BOTH;
		gbc_plusBtn.gridx = 3;
		gbc_plusBtn.gridy = 0;
		add(plusBtn, gbc_plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setMargin(new Insets(0, 0, 0, 0));
		minusBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		minusBtn.setBackground(Color.WHITE);
		GridBagConstraints gbc_minusBtn = new GridBagConstraints();
		gbc_minusBtn.fill = GridBagConstraints.BOTH;
		gbc_minusBtn.gridx = 4;
		gbc_minusBtn.gridy = 0;
		add(minusBtn, gbc_minusBtn);
//		qPanelController.setcheckboxFieldActionListeners(this);
		setOpaque(false);
		
	}
	
	/**
	 * Gets the answer check box.
	 *
	 * @return the answer check box
	 */
	public JCheckBox getAnswerCheckBox() {
		return answerCheckBox;
	}
	
	/**
	 * Sets the answer check box.
	 *
	 * @param checkBox the new answer check box
	 */
	public void setAnswerCheckBox(JCheckBox checkBox) {
		this.answerCheckBox = checkBox;
	}
	
	/**
	 * Gets the answer text option.
	 *
	 * @return the answer text option
	 */
	public JTextField getAnswerTextOption() {
		return answerTextOption;
	}
	
	/**
	 * Sets the answer text option.
	 *
	 * @param textField the new answer text option
	 */
	public void setAnswerTextOption(JTextField textField) {
		this.answerTextOption = textField;
	}
	
	/**
	 * Sets the plus button.
	 *
	 * @param plusBtn the new plus button
	 */
	public void setPlusBtn(JButton plusBtn) {
		this.plusBtn = plusBtn;
	}
	
	/**
	 * Sets the minus button.
	 *
	 * @param minusBtn the new minus button
	 */
	public void setMinusBtn(JButton minusBtn) {
		this.minusBtn = minusBtn;
	}
	
	/**
	 * Sets the a number label.
	 *
	 * @param aNumberLbl the new a number label
	 */
	public void setaNumberLbl(JLabel aNumberLbl) {
		this.aNumberLbl = aNumberLbl;
	}
	
	/**
	 * Gets the a number label.
	 *
	 * @return the a number label
	 */
	public JLabel getaNumberLbl() {
		return aNumberLbl;
	}
	
	/**
	 * Sets the a number label.
	 *
	 * @param string the new a number label
	 */
	public void setaNumberLbl(String string) {
		this.aNumberLbl.setText(string);
	}
	
	/**
	 * Plus button add listener.
	 *
	 * @param listener the listener
	 */
	public void plusBtnAddListener(ActionListener listener)
	{
		plusBtn.addActionListener(listener);
	}
	
	/**
	 * Minus button add listener.
	 *
	 * @param listener the listener
	 */
	public void minusBtnAddListener(ActionListener listener)
	{
		minusBtn.addActionListener(listener);
	}
	
	/**
	 * Adds the answer text option key listener.
	 *
	 * @param listener the listener
	 */
	public void addAnswerTextOptionKeyListener(KeyListener listener)
	{
		answerTextOption.addKeyListener(listener);
	}
	
	/**
	 * Adds the checkbox new check box.
	 *
	 * @param listener the listener
	 */
	public void addAnswerCheckBoxListener(ActionListener listener)
	{
		answerCheckBox.addActionListener(listener);
	}
	
	/**
	 * Gets the plus button.
	 *
	 * @return the plus button
	 */
	public JButton getPlusBtn() {
		return plusBtn;
	}
	
	/**
	 * Gets the minus button.
	 *
	 * @return the minus button
	 */
	public JButton getMinusBtn() {
		return minusBtn;
	}
	
	/**
	 * Gets the answer number.
	 *
	 * @return the answer number
	 */
	public int getAnswerNumber() {
		return answerNumber;
	}
	
	/**
	 * Sets the answer number.
	 *
	 * @param answerNumber the new answer number
	 */
	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}
}
