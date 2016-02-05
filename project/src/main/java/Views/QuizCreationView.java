package Views;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import Controllers.MainFrameController;
import Controllers.qPanelController;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JInternalFrame;
import java.awt.ScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.Component;



public class QuizCreationView extends ViewPanel {
	public JButton addBtn;
	public JPanel panel;
	public QuizCreationView() {
		super();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JScrollPane jsp=new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(750,550));
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		add(jsp);
		setVisible(true);
		
		addBtn = new JButton("Add question");
		addBtn.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(addBtn);
		addBtn.setAlignmentY(0.0f);

	}
	
	public void addBtnAddListener(ActionListener listener){
		addBtn.addActionListener(listener);
	}
}
