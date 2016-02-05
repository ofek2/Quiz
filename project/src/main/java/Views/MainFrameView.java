package Views;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrameView extends JFrame {
	
//	private static Container contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrameView() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800,600);
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
