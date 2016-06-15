package Views;

import java.io.Serializable;

import javax.swing.JPanel;
import Controllers.MainFrameController;

/**
 * The Class ViewPanel.
 * This class is a general panel in the application.
 */
public class ViewPanel extends JPanel implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new view panel.
	 */
	public ViewPanel() {
		super();
		
		setSize(MainFrameController.view.getContentPane().getWidth(),MainFrameController.view.getContentPane().getHeight());
	}
	
}
