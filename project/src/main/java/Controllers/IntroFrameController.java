package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.DropboxAuthentication;
import Views.IntroFrame;

public class IntroFrameController {
	
	private IntroFrame introFrame;
	
	public IntroFrameController(IntroFrame introFrame)
	{
		this.introFrame = introFrame;
		this.introFrame.addLoginBtnListener(new loginBtnListener());
	}
	class loginBtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			new DropboxAuthentication();
			introFrame.dispose();
		}
		
	}
}
