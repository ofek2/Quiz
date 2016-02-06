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

public class qPanel extends ViewPanel {
	private JLabel questionLbl;
	private JComboBox<String> inputTypeCb;
	private JComboBox<String> questionTypeCb;
	private JButton btnRemove;
	public Random random;
	
	/**
	 * Create the panel.
	 */
	public qPanel() {
		random = new Random();
		int num= random.nextInt(100);
		setBackground(Color.getHSBColor(num, num, num));
		setSize(800, 250);
		setMaximumSize(new Dimension(800,250));
		layoutSettings();
		
		questionLbl = new JLabel("Question 1.");
		questionLbl.setFont(new Font("Serif", Font.BOLD, 20));
		add(questionLbl, "2, 2");
		
		btnRemove = new JButton("X");
		add(btnRemove, "50, 2");
		
		JLabel enterQuestionLbl = new JLabel("Enter question:");
		enterQuestionLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(enterQuestionLbl, "2, 6");
		
		
		JLabel questionTypeLbl = new JLabel("Question type:");
		questionTypeLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(questionTypeLbl, "2, 10");
		
		inputTypeCb = new JComboBox<String>();
		inputTypeCb.addItem("As a text");
		inputTypeCb.addItem("As an image");
		inputTypeCb.addItem("As a sound");
		inputTypeCb.setSelectedIndex(0);
		inputTypeCb.setMaximumSize(inputTypeCb.getPreferredSize());
		add(inputTypeCb, "6, 6, fill, default");
		
		
		questionTypeCb = new JComboBox<String>();
		questionTypeCb.addItem("Multiple Choice");
		questionTypeCb.addItem("Free text");
		questionTypeCb.setSelectedIndex(0);
		questionTypeCb.setMaximumSize(questionTypeCb.getPreferredSize());
		add(questionTypeCb, "6, 10, fill, default");
		setMinimumSize(new Dimension(800, 250));
		
		setAlignmentY(0.0f);
	}
	public void removeBtnAddListener(ActionListener listener)
	{
		btnRemove.addActionListener(listener);
	}
	private void layoutSettings() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
	}

}
