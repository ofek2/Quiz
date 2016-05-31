package Controllers;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Entities.Constants;

/**
 * The Class uploadToDropbox.
 * This class is responsible for uploading new files to the user's
 * Dropbox account.
 */
public class uploadToDropbox extends SwingWorker<Void, Void>{
	
	/** The source. */
	private String source;
	
	/**
	 * Instantiates a new upload to dropbox.
	 *
	 * @param source the source
	 */
	public uploadToDropbox(String source) {
		// TODO Auto-generated constructor stub
		super();
		this.source=source;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		DropBoxSimple.uploadFolder(new File((new File(".")).getCanonicalPath()+"/OnlineQuizChecker.zip"), "/");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		DropBoxSimple.progressListener.dialog.setVisible(false);
		JOptionPane.showMessageDialog(null, "Files Uploaded Successfully To Your Dropbox Account");
		if(source.equals(Constants.SAVE_AND_EXIT))
		System.exit(0);
	}

}
