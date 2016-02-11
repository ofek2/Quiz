package Views;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.UIManager;

import Controllers.MainFrameController;
import Entities.Constants;

public class ViewPanel extends JPanel{
	public ViewPanel() {
		super();
		
		setSize(MainFrameController.view.getWidth(),MainFrameController.view.getHeight());
	}
	
}
