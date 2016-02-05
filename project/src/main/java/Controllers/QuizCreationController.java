package Controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Entities.QuizEntity;
import Views.MainFrameView;
import Views.QuizCreationView;
import Views.qPanel;

public class QuizCreationController {
	private QuizCreationView view;
	private QuizEntity entity;
	
	public QuizCreationController(QuizCreationView view,QuizEntity entity) {
	
		this.view = view;
		this.entity = entity;
		
		this.view.addBtnAddListener(new addBtnListener());
	}
	
	class addBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			qPanel qPview = new qPanel();
			new qPanelController(qPview);
			view.panel.add(qPview);
			view.panel.revalidate();

			view.panel.remove(view.addBtn);
			view.panel.add(view.addBtn);
			
		}
		
	}

}
