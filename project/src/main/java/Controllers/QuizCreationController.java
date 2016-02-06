package Controllers;

import java.awt.Color;
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
		addQpanel();
	}
	public void addQpanel()
	{
		qPanel qPview = new qPanel();
		new qPanelController(qPview,view);
		view.panel.add(qPview);
		view.panel.revalidate();
		if(view.panel.getComponentCount()%2 == 0)
			qPview.setBackground(Color.getHSBColor(0.55f, 0.69f, 1));
		else
			qPview.setBackground(Color.getHSBColor(0.0711f, 0.9916f, 1));
		view.panel.remove(view.addBtn);
		view.panel.add(view.addBtn);
		view.panel.revalidate();
	}
	class addBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			addQpanel();
		}
		
	}

}
