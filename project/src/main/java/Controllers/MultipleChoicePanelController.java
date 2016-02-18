package Controllers;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

import Views.MultipleChoicePanel;
import Views.checkBoxFieldPanel;

public class MultipleChoicePanelController implements Serializable{
	public MultipleChoicePanel view;
	public ArrayList<checkBoxFieldController> cBfControllers;
	public MultipleChoicePanelController(MultipleChoicePanel view)
	{
		this.view=view;
		cBfControllers = new ArrayList<checkBoxFieldController>();
		addCheckBoxField(0);
//		qPanelController.setcheckboxFieldActionListeners(this.view);
		
	}
	public void addCheckBoxField(int index) {
		checkBoxFieldPanel checkBoxFieldPanel = new checkBoxFieldPanel();
		checkBoxFieldController checkBoxFieldController= new checkBoxFieldController(checkBoxFieldPanel, this);
		checkBoxFieldPanel.getMinusBtn().setVisible(false);
		
		view.panel.add(checkBoxFieldPanel);
		cBfControllers.add(index, checkBoxFieldController);
		checkBoxFieldPanel.setAnswerNumber(index+1);
//		checkBoxFieldPanel.getAnswerTextOption().addKeyListener(qPanelController.textItemListener);
	}
	
}
