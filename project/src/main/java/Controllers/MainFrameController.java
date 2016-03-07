package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.dropbox.client2.exception.DropboxException;

import Views.DropBoxAuthenticationView;
import Views.InitialWindowView;
import Views.MainFrameView;
import Entities.CourseEntity;

public class MainFrameController {
	public static MainFrameView view;
	private File appFolder;
	private InitialWindowController initialWindowController;
	private int overWrite=JOptionPane.YES_OPTION;
	/*
	///////////////////////////
	File temp1;
	File temp2;
	File temp3;
	///////////////////////////
	  */
	 
	public MainFrameController(MainFrameView view) {
		this.view=view;
		
		loadApplicationFolder();
		if(overWrite==JOptionPane.YES_OPTION)
		{
			System.out.println(DropBoxAuthenticationView.userEmail);
		//load the courses folders into the array list
		///////////////////////////////////////////////////////////////////////////////////////////////
			initialWindowController.coursesFiles=new ArrayList<CourseEntity>();
			for(File folder:appFolder.listFiles())
			{
				String[] splitedName = folder.getName().split(",");
				initialWindowController.coursesFiles.add(new CourseEntity(folder, splitedName[0], splitedName[1]));
			}
		
			/*try {
			temp1=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"1,a");
			temp2=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"2,b");
			temp3=new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"+"/"+"3,c");
			initialWindowController.coursesFiles.add(new CourseEntity(temp1, "1", "a"));
			initialWindowController.coursesFiles.add(new CourseEntity(temp2, "2", "b"));
			initialWindowController.coursesFiles.add(new CourseEntity(temp3, "3", "c"));
			if(!temp1.exists())
			temp1.mkdir();
			if(!temp2.exists())
			temp2.mkdir();
			if(!temp3.exists())
			temp3.mkdir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		////////////////////////////////////////////////////////////////////////////////////////////////
			InitialWindowView initialWindowView = new InitialWindowView();
			initialWindowController = new InitialWindowController(initialWindowView);
			this.view.changeContentPane(initialWindowView);
			}
			else if(overWrite==JOptionPane.NO_OPTION||overWrite==JOptionPane.CLOSED_OPTION)
				System.exit(1);
			
		
		
	}
	public void loadApplicationFolder()
	{
		
		try {
			appFolder= new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker");
			//DropBoxSimple.upload(appFolder, "/");
			
			if(!appFolder.exists())
				appFolder.mkdir();
			else
				overWrite=JOptionPane.showConfirmDialog(null,"The application folder already exists, all of the existing data will be lost,\n do you want to keep the application progress?","Alert",JOptionPane.YES_NO_OPTION);

			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
