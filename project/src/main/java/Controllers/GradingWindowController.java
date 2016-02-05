package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Views.GradingWindowView;
import Views.ViewPanel;

public class GradingWindowController {
	private GradingWindowView view;
	private Container previousView;
	public GradingWindowController(GradingWindowView view) {
		this.view = view;
		addListeners();
	}
	public Container getPreviousView() {
		return previousView;
	}
	public void setPreviousView(Container previousView) {
		this.previousView = previousView;
	}
	private void addListeners()
	{
		ActionListener[] fileListeners = {new SendListener(),new SaveListener(),new ExitListener()};
		view.addFileListeners(fileListeners);
		
	}
	
	class SendListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		}
		
	}
	class SaveListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class ExitListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel)previousView);
		}
		
	}
}
