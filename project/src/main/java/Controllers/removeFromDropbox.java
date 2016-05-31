package Controllers;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingWorker;
import Views.CustomDialog;
import project.ObjectFileManager;
import project.zipFileManager;

/**
 * The Class removeFromDropbox. This class is responsible for removing files
 * from the user's Dropbox account before uploading new files.
 */
public class removeFromDropbox extends SwingWorker<Void, Void> {

	/** The dialog. */
	private CustomDialog dialog;

	/** The source. */
	private String source;

	/**
	 * Instantiates a new removes the from dropbox.
	 *
	 * @param dialog
	 *            the dialog
	 * @param source
	 *            the source
	 */
	public removeFromDropbox(CustomDialog dialog, String source) {
		super();
		this.dialog = dialog;
		this.source = source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		DropBoxSimple.deleteRemovedFilesFromDropbox();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		dialog.dispose();
		File appFolder;
		try {
			appFolder = new File(new File(".").getCanonicalPath() + "/OnlineQuizChecker");
			long folderSize = ObjectFileManager.folderSize(appFolder);
			DropBoxSimple.progressListener.init(folderSize, "uploaded");
			DropBoxSimple.progressListener.dialog.setVisible(true);
			zipFileManager.createZipFile(appFolder, (new File(".")).getCanonicalPath() + "/OnlineQuizChecker.zip");
			SwingWorker<Void, Void> uploadFolder = new uploadToDropbox(source);
			uploadFolder.execute();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
