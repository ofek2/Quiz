package Views;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import Controllers.MainFrameController;
import Controllers.MultipleChoicePanelController;
import Controllers.qPanelController;
import Entities.Constants;
import javafx.scene.shape.Box;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JInternalFrame;
import java.awt.ScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.Cursor;



public class QuizCreationView extends ViewPanel {
	public JButton addBtn;
	public JPanel panel;
	private JPanel headPanel;
	private JLabel quizName;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	
	public QuizCreationView() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setPreferredSize(new Dimension(100000, 30));
		menuBar.setMaximumSize(new Dimension(100000, 30));
		add(menuBar);
		
		fileMenu = new JMenu("File");
		fileMenu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar.add(fileMenu);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBounds(0,30,MainFrameController.view.getWidth(), MainFrameController.view.getHeight());
		JScrollPane jsp=new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(MainFrameController.view.getWidth(),MainFrameController.view.getHeight()-40));
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		
		add(jsp);
	
		headPanel= new JPanel();
		headPanel.setMaximumSize(new Dimension(700,50));
		TitledBorder title = BorderFactory.createTitledBorder("Quiz Info");
		title.setTitleJustification(TitledBorder.CENTER);
		headPanel.setBorder(title);
		headPanel.setOpaque(false);
		headPanel.setLayout(new GridLayout(1, 4));
		
		JLabel quizLbl = new JLabel("Quiz Name: ");
		
		quizLbl.setFont(new Font("Arial", Font.BOLD, 17));
		quizLbl.setHorizontalAlignment(JLabel.CENTER);
	
		headPanel.add(quizLbl);
		quizName = new JLabel("test");
		quizName.setFont(new Font("Arial", Font.PLAIN, 17));
		quizName.setHorizontalAlignment(JLabel.LEFT);
		headPanel.add(quizName);
		
		JLabel percentage = new JLabel("Quiz Weight: ");
		percentage.setFont(new Font("Arial", Font.BOLD, 17));
		percentage.setHorizontalAlignment(JLabel.CENTER);
		headPanel.add(percentage);
		JSpinner percentageFromFgrade = new JSpinner(new SpinnerNumberModel(0,0,1,.01));   
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(percentageFromFgrade,"0%");  
		percentageFromFgrade.setEditor(editor);
		
		headPanel.add(percentageFromFgrade);
		panel.add(headPanel);
		
		addBtn = new JButton("Add question");
		addBtn.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(addBtn);
		addBtn.setAlignmentY(0.0f);
		setVisible(true);
	}
	
	/*private String[] buildSpinnerModel() {
		String [] str = new String[100];
		for(int i=0;i<100;i++)
		{
			str[i]=(i+1)+"%";
		}
		return str;
	}*/

	public void addBtnAddListener(ActionListener listener){
		addBtn.addActionListener(listener);
	}
}
