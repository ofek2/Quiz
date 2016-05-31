package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.HelpFrame;

/**
 * The Class helpFrameController.
 * This class controls the {@link HelpFrame} events
 */
public class helpFrameController {
	
	/** The view. */
	private HelpFrame view;
	
	/** The position of a picture viewed currently. */
	private int pos;
	
	/**
	 * Instantiates a new help frame controller.
	 *
	 * @param view the view
	 */
	public helpFrameController(HelpFrame view)
	{
		this.view = view;
		pos = 0;
		this.view.addPrevBtnListener(new prevBtnListener());
		this.view.addNextBtnListener(new nextBtnListener());
	}
	
	/**
	 * The listener interface for receiving prevBtn events.
	 * The class that is interested in processing a prevBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addprevBtnListener<code> method. When
	 * the prevBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see prevBtnEvent
	 */
	class prevBtnListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(pos>0)
			{
				pos--;
				view.setImage(pos);
			}
		}
		
	}
	
	/**
	 * The listener interface for receiving nextBtn events.
	 * The class that is interested in processing a nextBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addnextBtnListener<code> method. When
	 * the nextBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see nextBtnEvent
	 */
	class nextBtnListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(pos<view.getImagesArr().length-1)
			{
				pos++;
				view.setImage(pos);
			}
		}
		
	}
	
}
