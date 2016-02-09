package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Views.InitialWindowView;
import Views.MainFrameView;
import Views.QuizCreationView;
import Controllers.QuizCreationController;

public class MainFrameController {
	public static MainFrameView view;
	private File appFolder;
	private InitialWindowController initialWindowController;
	
	///////////////////////////
	File temp1;
	File temp2;
	File temp3;
	///////////////////////////
	public MainFrameController(MainFrameView view) {
		this.view=view;
		
		//load the courses folders into the array list
		//////////////////////////////
		initialWindowController.coursesFiles=new ArrayList<File>();
		try {
			initialWindowController.coursesFiles.add(temp1=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"1"));
			initialWindowController.coursesFiles.add(temp2=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"2"));
			initialWindowController.coursesFiles.add(temp3=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"3"));
			temp1.mkdir();
			temp2.mkdir();
			temp3.mkdir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//////////////////////////////////
		InitialWindowView initialWindowView = new InitialWindowView();
		initialWindowController = new InitialWindowController(initialWindowView);
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
