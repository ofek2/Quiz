package Views;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controllers.MainFrameController;
import Entities.Constants;


public class MainFrameView extends JFrame {
	
	/**
	 * Create the frame.
	 */
	public MainFrameView() {
		super();
/////////////////////////////////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/////////////////////////////////		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

}
