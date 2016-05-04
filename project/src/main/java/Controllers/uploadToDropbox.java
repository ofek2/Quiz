package Controllers;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import Entities.Constants;

public class uploadToDropbox extends SwingWorker<Void, Void>{
	private String source;
	public uploadToDropbox(String source) {
		// TODO Auto-generated constructor stub
		super();
		this.source=source;
	}
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		DropBoxSimple.uploadFolder(new File((new File(".")).getCanonicalPath()+"/OnlineQuizChecker.zip"), "/");
		return null;
	}
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
