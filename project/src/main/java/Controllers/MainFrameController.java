package Controllers;

import Views.InitialWindowView;
import Views.MainFrameView;
import Views.QuizCreationView;
import Controllers.QuizCreationController;

public class MainFrameController {
	public static MainFrameView view;
	public MainFrameController(MainFrameView view) {
		this.view=view;
		InitialWindowView initialWindowView = new InitialWindowView();
		InitialWindowController initialWindowController = new InitialWindowController(initialWindowView);
		this.view.changeContentPane(initialWindowView);
	}
}
