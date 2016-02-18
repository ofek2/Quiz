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
	
//	private static Container contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrameView() {
		super();
/////////////////////////////////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/////////////////////////////////		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setBounds(Constants.realtiveFrameXPos,Constants.realtiveFrameYPos,Toolkit.getDefaultToolkit().getScreenSize().width/2,(int) ((Toolkit.getDefaultToolkit().getScreenSize().width/2)/Constants.ratio));
		
//		setBounds(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//		setExtendedState(JFrame.MAXIMIZED_BOTH);

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		setBounds(new Rectangle(0, 0, bounds.width, bounds.height));
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
//		setExtendedState(JFrame.NORMAL);
		setResizable(false);
//		pack();
//		contentPane = new Container();
//		add(contentPane);
//		setContentPane(contentPane);
		setVisible(true);
	//	pack();
	}
	public void changeContentPane(ViewPanel view)
	{
		setContentPane(view);
//		contentPane=view;
//		setContentPane(contentPane);
	}
	public void changeContentPane(ViewScrollablePanel view)
	{
//		contentPane=view;
		setContentPane(view);
	}
}
