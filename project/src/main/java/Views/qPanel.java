package Views;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;

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

public class qPanel extends ViewPanel {
	private JLabel questionLbl;
	private JComboBox<String> inputTypeCb;
	private JComboBox<String> questionTypeCb;
	private JButton btnRemove;
	public Random random;
	private JTextField scoreField;
	private JLabel lblScore;
	
	/**
	 * Create the panel.
	 */
	public qPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setPreferredSize(new Dimension(725, 250));
		setMaximumSize(new Dimension(725,250));
		
		setLayout(null);
		
		questionLbl = new JLabel("Question 1.");
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
		inputTypeCb.addItem("As a sound");
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
	}
	public void removeBtnAddListener(ActionListener listener)
	{
		btnRemove.addActionListener(listener);
	}
	
}
