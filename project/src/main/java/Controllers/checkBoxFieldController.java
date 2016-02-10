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
			
			
			addCheckBoxField();
			parentView.panel.revalidate();
		}
		public void addCheckBoxField() {
			checkBoxFieldPanel checkBoxFieldPanel = new checkBoxFieldPanel();
			checkBoxFieldController checkBoxFieldController= new checkBoxFieldController(checkBoxFieldPanel, parentView);
			parentView.panel.add(checkBoxFieldPanel);
			MultipleChoicePanelController.cBfControllers.add(checkBoxFieldController);
			view.getMinusBtn().setVisible(true);
			view.getPlusBtn().setVisible(false);
			
			
			checkBoxFieldPanel.setAnswerNumber(MultipleChoicePanelController.cBfControllers.size());
			checkBoxFieldPanel.setaNumberLbl(MultipleChoicePanelController.cBfControllers.size()+".");
		}
	}
	class minusBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			parentView.panel.remove(view);
			MultipleChoicePanelController.cBfControllers.remove(view.getAnswerNumber()-1);
			rebuildAnswersPanel();
			
			
		/*	if(temp.getAnswerNumber()==1)
			temp.getMinusBtn().setVisible(false);
			temp.getPlusBtn().setVisible(true);*/
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
		}
		public void rebuildAnswersPanel()
		{
			
			for(int i=0;i<MultipleChoicePanelController.cBfControllers.size();i++)
			{
			
				
				MultipleChoicePanelController.cBfControllers.get(i).view.setAnswerNumber(i+1);
				MultipleChoicePanelController.cBfControllers.get(i).view.setaNumberLbl((i+1)+".");
				if(i==MultipleChoicePanelController.cBfControllers.size()-1)
				{
					MultipleChoicePanelController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					MultipleChoicePanelController.cBfControllers.get(i).view.getMinusBtn().setVisible(true);
				}
				if(i==0 && MultipleChoicePanelController.cBfControllers.size()==1)
				{
					MultipleChoicePanelController.cBfControllers.get(i).view.getPlusBtn().setVisible(true);
					MultipleChoicePanelController.cBfControllers.get(i).view.getMinusBtn().setVisible(false);
				}
				
			}
		}
	}
}
