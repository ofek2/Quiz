package Views;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controllers.MainFrameController;


public class MainFrameView extends JFrame implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public static windowListener windowListener;
	public MainFrameView() {
		super();
		setTitle("Online Quiz Checker");
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
/////////////////////////////////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/////////////////////////////////		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		windowListener = new windowListener();
		addWindowListener(windowListener);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
//		setBounds(new Rectangle(0, 0, bounds.width, bounds.height));
		setSize(1000,700);
		setLocationRelativeTo(null);
		setResizable(false);

		setVisible(true);

	}
	public void changeContentPane(ViewPanel view)
	{
		setContentPane(view);
	}
	
	class windowListener extends WindowAdapter implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
				MainFrameController.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}

		
		
	}

}
