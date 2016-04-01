package Controllers;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingWorker;
import Views.CustomDialog;
import project.ObjectFileManager;


public class removeFromDropbox extends SwingWorker<Void, Void>{
	
	private CustomDialog dialog;
	private String source;
	public removeFromDropbox(CustomDialog dialog,String source) {
		super();
		this.dialog = dialog;
		this.source = source;
	}
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		DropBoxSimple.deleteRemovedFilesFromDropbox();
		return null;
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		dialog.dispose();
		File appFolder;
		try {
			appFolder = new File(new File(".").getCanonicalPath() + "/OnlineQuizChecker");
			long folderSize = ObjectFileManager.folderSize(appFolder);
			DropBoxSimple.progressListener.init(folderSize,  "uploaded"); 
			DropBoxSimple.progressListener.dialog.setVisible(true);
			SwingWorker<Void, Void> uploadFolder = new uploadToDropbox(source);
			uploadFolder.execute();
//			DropBoxSimple.uploadFolder(new File(new File(".") + "/OnlineQuizChecker/"), "/");
//			DropBoxSimple.progressListener.dialog.setVisible(false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

