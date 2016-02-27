package Views;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controllers.MainFrameController;

public class Main{
	static transient MainFrameView view;
	static transient MainFrameController controller;
	public static String[] mainArgs;
	public static void main(String [] args)
	{
		mainArgs = args;
		view = new MainFrameView();
		
		controller = new MainFrameController(view);
		
	}
}
