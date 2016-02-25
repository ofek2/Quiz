package Views;

import Controllers.MainFrameController;

public class Main{
	static MainFrameView view;
	static MainFrameController controller;
	public static void main(String [] args)
	{
	/*	try {
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
		view = new MainFrameView();
		
		controller = new MainFrameController(view);
		
	}
}
