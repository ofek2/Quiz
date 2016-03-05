package Views;


import Controllers.MainFrameController;

public class Main{
//	static transient MainFrameView view;
//	static transient MainFrameController controller;
	private static DropBoxAuthenticationView view;
	//public static String[] mainArgs;
	public static void main(String [] args)
	{
		//mainArgs = args;
		/*view = new MainFrameView();
		
		controller = new MainFrameController(view);
		*/
		view = new DropBoxAuthenticationView();
		
	}
}
