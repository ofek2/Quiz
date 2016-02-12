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
	private qPanelController qPanelController;
	protected static ArrayList<qPanelController> qPanels;
	
	public QuizCreationController(QuizCreationView view,QuizEntity entity) {
	
		this.view = view;
		this.entity = entity;
		this.view.addBtnAddListener(new addBtnListener());
		qPanels = new ArrayList<qPanelController>();
		addQpanel();
	}
	public void addQpanel()
	{
		qPanel qPview = new qPanel();
		qPanelController = new qPanelController(qPview,view,entity);
		view.panel.add(qPview);
		view.panel.revalidate();
		
		
		view.panel.remove(view.addBtn);
		view.panel.add(view.addBtn);
		
		qPview.setQuestionNumber(qPanels.size()+1);
		qPanels.add(qPanelController);
		QuizCreationController.qPanels.get(qPanels.size()-1).getQuestionPanel().getQuestionLbl().setText("Question"+(qPanels.size()));
		if((qPview.getQuestionNumber()-1)%2 == 0)
			qPview.setBackground(Color.getHSBColor(0.55f, 0.69f, 1));
		else
			qPview.setBackground(Color.getHSBColor(0.0711f, 0.9916f, 1));
		view.panel.revalidate();
	}
	class addBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			addQpanel();
		}
		
	}

}
