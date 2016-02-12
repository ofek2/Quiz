package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Views.MultipleChoicePanel;
import Views.checkBoxFieldPanel;

public class checkBoxFieldController {
	public checkBoxFieldPanel view;
	private MultipleChoicePanelController parentController;
	
	public checkBoxFieldController(checkBoxFieldPanel view, MultipleChoicePanelController parentController) {
		this.view = view;
		this.parentController = parentController;
		this.view.plusBtnAddListener(new plusBtnListener());
		this.view.minusBtnAddListener(new minusBtnListener());
	}
	class plusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			addCheckBoxField();
			parentController.view.panel.revalidate();
		}
		public void addCheckBoxField() {
			checkBoxFieldPanel checkBoxFieldPanel = new checkBoxFieldPanel();
			checkBoxFieldController checkBoxFieldController= new checkBoxFieldController(checkBoxFieldPanel, parentController);
			parentController.view.panel.add(checkBoxFieldPanel);
			parentController.cBfControllers.add(checkBoxFieldController);
			view.getMinusBtn().setVisible(true);
			view.getPlusBtn().setVisible(false);
			
			
			checkBoxFieldPanel.setAnswerNumber(parentController.cBfControllers.size());
			checkBoxFieldPanel.setaNumberLbl(parentController.cBfControllers.size()+".");
		}
	}
	class minusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			parentController.view.panel.remove(view);
			parentController.cBfControllers.remove(view.getAnswerNumber()-1);
			rebuildAnswersPanel();
			
			
		/*	if(temp.getAnswerNumber()==1)
			temp.getMinusBtn().setVisible(false);
			temp.getPlusBtn().setVisible(true);*/
			parentController.view.panel.revalidate();
			MainFrameController.view.repaint();
		}
		public void rebuildAnswersPanel()
		{
			
			for(int i=0;i<parentController.cBfControllers.size();i++)
			{
			
				
				parentController.cBfControllers.get(i).view.setAnswerNumber(i+1);
				parentController.cBfControllers.get(i).view.setaNumberLbl((i+1)+".");
				if(i==parentController.cBfControllers.size()-1)
				{
					parentController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					parentController.cBfControllers.get(i).view.getMinusBtn().setVisible(true);
				}
				if(i==0 && parentController.cBfControllers.size()==1)
				{
					parentController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					parentController.cBfControllers.get(i).view.getMinusBtn().setVisible(false);
				}
				
			}
		}
	}
}
