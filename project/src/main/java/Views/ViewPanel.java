package Views;

import java.io.Serializable;

import javax.swing.JPanel;
import Controllers.MainFrameController;

public class ViewPanel extends JPanel implements Serializable{
	public ViewPanel() {
		super();
		
		setSize(MainFrameController.view.getContentPane().getWidth(),MainFrameController.view.getContentPane().getHeight());
	}
	
}
