package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Views.MultipleChoicePanel;
import Views.checkBoxFieldPanel;

public class checkBoxFieldController {
	private checkBoxFieldPanel view;
	private MultipleChoicePanel parentView;
	
	public checkBoxFieldController(checkBoxFieldPanel view, MultipleChoicePanel parentView) {
		this.view = view;
		this.parentView = parentView;
		this.view.plusBtnAddListener(new plusBtnListener());
		this.view.minusBtnAddListener(new minusBtnListener());
	}
	class plusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			addCheckBoxField(view.getAnswerNumber());
			parentView.panel.revalidate();
		}
		public void addCheckBoxField(int index) {
			checkBoxFieldPanel checkBoxFieldPanel = new checkBoxFieldPanel();
			checkBoxFieldController checkBoxFieldController= new checkBoxFieldController(checkBoxFieldPanel, parentView);
			parentView.panel.add(checkBoxFieldPanel);
			view.getMinusBtn().setVisible(true);
			view.getPlusBtn().setVisible(false);
			MultipleChoicePanelController.cBfControllers.add(index, checkBoxFieldController);
			checkBoxFieldPanel.setAnswerNumber(index+1);
		}
	}
	class minusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			checkBoxFieldPanel temp;
			parentView.panel.remove(view);
			MultipleChoicePanelController.cBfControllers.remove(view.getAnswerNumber()-1);
			temp= (checkBoxFieldPanel)(parentView.panel.getComponent(parentView.panel.getComponentCount()-1));
			if(temp.getAnswerNumber()==1)
			temp.getMinusBtn().setVisible(false);
			temp.getPlusBtn().setVisible(true);
			parentView.panel.revalidate();
			//MultipleChoicePanelController.cBfControllers.remove(index)
		}
		
	}
}
