package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.dropbox.client2.exception.DropboxException;

import Views.CustomDialog;
import Views.DropBoxAuthenticationView;
import Views.InitialWindowView;
import Views.MainFrameView;
import project.progListener;
import Entities.CourseEntity;

public class MainFrameController {
	public static MainFrameView view;
	private File appFolder;
	private InitialWindowController initialWindowController;
	private int overWrite=JOptionPane.YES_OPTION;

	 
	public MainFrameController(MainFrameView view) {
		this.view=view;
		
		loadApplicationFolder();
//		if(overWrite==JOptionPane.YES_OPTION)
//		{
		//load the courses folders into the array list
		///////////////////////////////////////////////////////////////////////////////////////////////
			initialWindowController.coursesFiles=new ArrayList<CourseEntity>();
			for(File folder:appFolder.listFiles())
			{
				String[] splitedName = folder.getName().split(",");
				initialWindowController.coursesFiles.add(new CourseEntity(folder, splitedName[0], splitedName[1]));
			}
		
			InitialWindowView initialWindowView = new InitialWindowView();
			initialWindowController = new InitialWindowController(initialWindowView);
			this.view.changeContentPane(initialWindowView);
//			}
//			else if(overWrite==JOptionPane.NO_OPTION||overWrite==JOptionPane.CLOSED_OPTION)
//				System.exit(1);
//			
		
		
	}
	public void loadApplicationFolder()
	{
		
		try {
			appFolder = new File(new File(".").getCanonicalPath()
					+ "/OnlineQuizChecker");
			if (!appFolder.exists())
				appFolder.mkdir();
			else
				overWrite = JOptionPane
						.showConfirmDialog(
								null,
								"The application folder already exists, all of the existing data will be lost,\n do you want to keep the application progress?",
								"Alert", JOptionPane.YES_NO_OPTION);

			if (overWrite == JOptionPane.YES_OPTION) {
				recursiveDelete(appFolder);
				try {
					long dropboxtotalsize = DropBoxSimple.getDropboxTotalSize();
					DropBoxSimple.progressListener.init(dropboxtotalsize,
							"downloaded");
					DropBoxSimple.progressListener.dialog.setVisible(true);
					DropBoxSimple.downloadFolder(appFolder.getCanonicalPath(),
							"/");
					DropBoxSimple.progressListener.dialog.setVisible(false);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// if(!appFolder.exists())
				// appFolder.mkdir();
				// else
				// overWrite=JOptionPane.showConfirmDialog(null,"The application folder already exists, all of the existing data will be lost,\n do you want to keep the application progress?","Alert",JOptionPane.YES_NO_OPTION);

			} else if (overWrite == JOptionPane.NO_OPTION
					|| overWrite == JOptionPane.CLOSED_OPTION)
				System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void recursiveDelete(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
    }
	
}
