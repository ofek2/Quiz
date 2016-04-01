package Views;

import java.io.Serializable;

import javax.swing.JPanel;
import Controllers.MainFrameController;

public class ViewPanel extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewPanel() {
		super();
		
		setSize(MainFrameController.view.getContentPane().getWidth(),MainFrameController.view.getContentPane().getHeight());
	}
	
}
