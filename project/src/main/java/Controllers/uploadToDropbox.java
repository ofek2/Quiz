package Controllers;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class uploadToDropbox extends SwingWorker<Void, Void>{

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		DropBoxSimple.uploadFolder(new File(new File(".") + "/OnlineQuizChecker/"), "/");
		return null;
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		DropBoxSimple.progressListener.dialog.setVisible(false);
		JOptionPane.showMessageDialog(null, "Files Uploaded Successfully To Your Dropbox Account");
		System.exit(0);
	}

}
