package Views;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.Constants;


public class MainFrameView extends JFrame {
	
//	private static Container contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrameView() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(Constants.realtiveFrameXPos,Constants.realtiveFrameYPos,Toolkit.getDefaultToolkit().getScreenSize().width/2,(int) ((Toolkit.getDefaultToolkit().getScreenSize().width/2)/Constants.ratio));
		System.out.println(Constants.realtiveFrameInitWidth+" "+Constants.realtiveFrameInitHeight+" "+((Toolkit.getDefaultToolkit().getScreenSize().width/2)/Constants.ratio)+" "+Constants.ratio);
		setResizable(false);
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
