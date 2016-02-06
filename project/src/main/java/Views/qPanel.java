package Views;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class qPanel extends ViewPanel {
	private JLabel questionLbl;
	private JComboBox<String> inputTypeCb;
	private JComboBox<String> questionTypeCb;
	private JButton btnRemove;
	private JTextField scoreField;
	private JLabel lblScore;
	private JPanel questionPanel;
	private JPanel qAsText;
	private JButton browseBtn;
	private JTextArea textArea;
	private int questionNumber;
	/**
	 * Create the panel.
	 */
	public qPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setPreferredSize(new Dimension(725, 250));
		setMaximumSize(new Dimension(725,250));
		
		setLayout(null);
		
		questionLbl = new JLabel("Question "+questionNumber);
		questionLbl.setBounds(8, 8, 95, 27);
		questionLbl.setFont(new Font("Serif", Font.BOLD, 20));
		add(questionLbl);
		
		btnRemove = new JButton("X");
		btnRemove.setFont(new Font("Serif", Font.BOLD, 10));
		btnRemove.setBounds(668, 9, 47, 34);
		add(btnRemove);
		
		JLabel enterQuestionLbl = new JLabel("Enter question:");
		enterQuestionLbl.setBounds(8, 67, 95, 18);
		enterQuestionLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(enterQuestionLbl);
		
		
		JLabel questionTypeLbl = new JLabel("Question type:");
		questionTypeLbl.setBounds(8, 118, 95, 18);
		questionTypeLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(questionTypeLbl);
		
		inputTypeCb = new JComboBox<String>();
		inputTypeCb.setBounds(134, 66, 160, 20);
		inputTypeCb.addItem("As a text");
		inputTypeCb.addItem("As an image");
		inputTypeCb.setSelectedIndex(0);
		inputTypeCb.setMaximumSize(inputTypeCb.getPreferredSize());
		add(inputTypeCb);
		
		
		questionTypeCb = new JComboBox<String>();
		questionTypeCb.setBounds(134, 117, 160, 20);
		questionTypeCb.addItem("Multiple Choice");
		questionTypeCb.addItem("Free text");
		questionTypeCb.setSelectedIndex(0);
		questionTypeCb.setMaximumSize(questionTypeCb.getPreferredSize());
		add(questionTypeCb);
		setMinimumSize(new Dimension(800, 250));
		
		setAlignmentY(0.0f);
		
		lblScore = new JLabel("Score:");
		lblScore.setBounds(134, 18, 52, 14);
		add(lblScore);
		
		scoreField = new JTextField();
		scoreField.setBounds(179, 15, 52, 20);
		add(scoreField);
		scoreField.setColumns(10);
		
		questionPanel = new JPanel();
		questionPanel.setOpaque(false);

		questionPanel.setBounds(321, 52, 376, 95);
		add(questionPanel);
		questionPanel.setLayout(new CardLayout(0, 0));
		
		qAsText = new JPanel();
		questionPanel.add(qAsText, "As a text");
		qAsText.setLayout(null);
		qAsText.setOpaque(false);
		
		JLabel qLabel = new JLabel("Enter question:");
		qLabel.setBounds(10, 14, 117, 14);
		qAsText.add(qLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable listening");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNewCheckBox.setBounds(287, 10, 97, 23);
		chckbxNewCheckBox.setOpaque(false);
		qAsText.add(chckbxNewCheckBox);
		
		JPanel qAsImg = new JPanel();
		questionPanel.add(qAsImg, "As an image");
		qAsImg.setLayout(null);
		qAsImg.setOpaque(false);
		browseBtn = new JButton("Browse..");
		browseBtn.setBounds(131, 11, 89, 23);
		qAsImg.add(browseBtn);
		
		JLabel browseLabel = new JLabel("Browse an image:");
		browseLabel.setBounds(10, 15, 104, 14);
		qAsImg.add(browseLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 61, 175, 75);
		add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	
		
	
		
	}
	public void removeBtnAddListener(ActionListener listener)
	{
		btnRemove.addActionListener(listener);
	}
	public void qTypeCBaddItemListener(ItemListener listener)
	{
		inputTypeCb.addItemListener(listener);
	}
	public JPanel getQuestionPanel()
	{
		return questionPanel;
	}
	public JLabel getQuestionLbl() {
		return questionLbl;
	}
	public void setQuestionLbl(JLabel questionLbl) {
		this.questionLbl = questionLbl;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
}
