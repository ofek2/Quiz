package Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import Views.MultipleChoicePanel;
import Views.checkBoxFieldPanel;

/**
 * The Class MultipleChoicePanelController.
 * This class controls the MultupleChoicePanel events.
 */
public class MultipleChoicePanelController implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The view. */
	public MultipleChoicePanel view;
	
	/** The checkBoxField controllers. */
	public ArrayList<checkBoxFieldController> cBfControllers;
	
	/**
	 * Instantiates a new multiple choice panel controller.
	 *
	 * @param view the view
	 */
	public MultipleChoicePanelController(MultipleChoicePanel view)
	{
		this.view=view;
		cBfControllers = new ArrayList<checkBoxFieldController>();
		addCheckBoxField(0);
//		qPanelController.setcheckboxFieldActionListeners(this.view);
		
	}
	
	/**
	 * Adds a check box field.
	 *
	 * @param index the index
	 */
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
