package Controllers;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import Views.InitialWindowView;
import Views.MainFrameView;
import Views.QuizCreationView;
import Controllers.QuizCreationController;

public class MainFrameController {
	public static MainFrameView view;
	private File appFolder;
	public MainFrameController(MainFrameView view) {
		this.view=view;
		InitialWindowView initialWindowView = new InitialWindowView();
		InitialWindowController initialWindowController = new InitialWindowController(initialWindowView);
		this.view.changeContentPane(initialWindowView);
		try {
			appFolder= new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker");
			if(!appFolder.exists())
				appFolder.mkdir();
			else
				JOptionPane.showMessageDialog(null,"The application folder already exists, all of the existing data will be lost","Alert",JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
