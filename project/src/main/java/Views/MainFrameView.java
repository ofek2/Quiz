package Views;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import javax.swing.JFrame;
import Controllers.MainFrameController;


public class MainFrameView extends JFrame {
	
	/**
	 * Create the frame.
	 */
	public static windowListener windowListener;
	public MainFrameView() {
		super();
/////////////////////////////////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/////////////////////////////////		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		windowListener = new windowListener();
		addWindowListener(windowListener);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		setBounds(new Rectangle(0, 0, bounds.width, bounds.height));
		setResizable(false);

		setVisible(true);

	}
	public void changeContentPane(ViewPanel view)
	{
		setContentPane(view);
	}
	
	class windowListener implements WindowListener,Serializable
	{
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
				MainFrameController.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}

		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}

}
