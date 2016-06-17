package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.Constants;
import Views.DropboxAuthentication;
import Views.HelpFrame;
import Views.IntroFrame;

/**
 * The Class IntroFrameController.
 * This class controls the {@link IntroFrame} events.
 */
public class IntroFrameController {
	
	/** The intro frame. */
	private IntroFrame introFrame;
	
	/**
	 * Instantiates a new intro frame controller.
	 *
	 * @param introFrame the intro frame
	 */
	public IntroFrameController(IntroFrame introFrame)
	{
		this.introFrame = introFrame;
		this.introFrame.addLoginBtnListener(new loginBtnListener());
		this.introFrame.addHelpBtnListener(new helpBtnListener());
	}
	
	/**
	 * The listener interface for receiving loginBtn events.
	 * The class that is interested in processing a loginBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addloginBtnListener<code> method. When
	 * the loginBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see loginBtnEvent
	 */
	class loginBtnListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			new DropboxAuthentication();
			introFrame.dispose();
		}
		
	}
	
	/**
	 * The listener interface for receiving helpBtn events.
	 * The class that is interested in processing a helpBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addhelpBtnListener<code> method. When
	 * the helpBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see helpBtnEvent
	 */
	class helpBtnListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			new helpFrameController(new HelpFrame(Constants.HELP_INTRO,Constants.HELP_INTRO_AMOUNT));
		}
		
	}
}
