package Views;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Controllers.checkBoxFieldController;

public class MultipleChoicePanel extends JPanel {
	
	public JPanel panel;
	public MultipleChoicePanel(){
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JScrollPane jsp=new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(317,162));
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		add(jsp);
		JLabel choicesLbl = new JLabel("Choices:");
		choicesLbl.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(choicesLbl);
		
	}
}
