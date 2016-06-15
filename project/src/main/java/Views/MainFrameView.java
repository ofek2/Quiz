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


/**
 * The Class MainFrameView.
 * This class is the main frame of the application.
 */
public class MainFrameView extends JFrame implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public static windowListener windowListener;
	
	/**
	 * Instantiates a new main frame view.
	 */
	public MainFrameView() {
		super();
		setTitle("Online Quiz Checker");
		
		windowListener = new windowListener();
		addWindowListener(windowListener);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		setSize(1000,700);
		setLocationRelativeTo(null);
		setResizable(false);

		setVisible(true);

	}
	
	/**
	 * Change content pane.
	 *
	 * @param view the view
	 */
	public void changeContentPane(ViewPanel view)
	{
		setContentPane(view);
	}
	
	/**
	 * The listener interface for receiving window events.
	 * The class that is interested in processing a window
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addwindowListener<code> method. When
	 * the window event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see windowEvent
	 */
	class windowListener extends WindowAdapter implements Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
				MainFrameController.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}

		
		
	}

}
