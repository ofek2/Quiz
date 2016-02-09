package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Views.checkBoxFieldPanel;

public class checkBoxFieldController {
	private checkBoxFieldPanel view;
	private JPanel parentView;
	
	public checkBoxFieldController(checkBoxFieldPanel view, JPanel parentView) {
		this.view = view;
		this.parentView = parentView;
	}
	class plusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class minusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
