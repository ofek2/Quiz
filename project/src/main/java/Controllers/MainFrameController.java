package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Views.InitialWindowView;
import Views.MainFrameView;
import project.zipFileManager;
import Entities.Constants;
import Entities.CourseEntity;

/**
 * The Class MainFrameController.
 * This class controls the {@MainFrameView} events.
 */
public class MainFrameController {
	
	/** The view. */
	public static MainFrameView view;
	
	/** The app folder. */
	private File appFolder;
	
	/** The over write. */
	private int overWrite=JOptionPane.YES_OPTION;

	 
	/**
	 * Instantiates a new main frame controller.
	 *
	 * @param view the view
	 */
	public MainFrameController(MainFrameView view) {
		MainFrameController.view=view;
		
		loadApplicationFolder();

			InitialWindowController.coursesFiles=new ArrayList<CourseEntity>();
			for(File folder:appFolder.listFiles())
			{
				String[] splitedName = folder.getName().split(",");
				InitialWindowController.coursesFiles.add(new CourseEntity(folder, splitedName[0], splitedName[1]));
			}
		
			InitialWindowView initialWindowView = new InitialWindowView();
			new InitialWindowController(initialWindowView);
			MainFrameController.view.changeContentPane(initialWindowView);
			
		
	}
	
	/**
	 * Load application folder.
	 */
	public void loadApplicationFolder()
	{
		
		try {
			appFolder = new File(new File(".").getCanonicalPath()
					+ "/"+Constants.APP_NAME);
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
				
					DropBoxSimple.downloadFolder(new File(".").getCanonicalPath(),"/");
					
					DropBoxSimple.progressListener.dialog.setVisible(false);
					File zipFile = new File(new File(".").getCanonicalPath()+"/"+Constants.APP_NAME+".zip");
					if(zipFile.exists())
					{
						zipFileManager.unZipIt(zipFile.getCanonicalPath(), appFolder.getCanonicalPath());
						zipFile.delete();
					}
					else
						appFolder.mkdir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Recursive delete.
	 *
	 * @param file the file
	 */
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
