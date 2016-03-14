package Controllers;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
import project.progListener;

// TODO: Auto-generated Javadoc
/**
 * The Class DropBoxSimple.
 */
public class DropBoxSimple {
	
	/** The app key. */
	final String APP_KEY = "6uzu0uyprajxb0p";
	
	/** The app secret. */
	final String APP_SECRET = "e7iwzdqp4rwtu88";
	
	/** The root path. */
	public static String rootPath;
	
	/** The api. */
	public static DropboxAPI<WebAuthSession> api;
	// public static DbxClientV1 client;
	/** The app keys. */
	// private DbxRequestConfig config ;
	private AppKeyPair appKeys;
	
	/** The session. */
	private WebAuthSession session;
	
	/** The pair. */
	private RequestTokenPair pair;
	
	/** The authenticated. */
	private boolean authenticated;

	/** The download progress label. */
	public static JLabel downloadProgressLabel;
	
	/** The download progress d. */
	public static JDialog downloadProgressD;
	
	/** The total dropbox size. */
	private static double totalDropboxSize;

	/**
	 * Instantiates a new drop box simple.
	 */
	public DropBoxSimple() {
		try {
			rootPath = new File(".").getCanonicalPath() + "\\OnlineQuizChecker";

			downloadProgressLabel = new JLabel("");
			DropBoxSimple.downloadProgressLabel.setLocation(100, 0);
			downloadProgressD = new JDialog();
			downloadProgressD.setLayout(new BorderLayout());
			downloadProgressD.setSize(400, 200);
			downloadProgressD.setLocationRelativeTo(null);
			downloadProgressD.add(DropBoxSimple.downloadProgressLabel, BorderLayout.CENTER);
			// downloadProgressD = new JDialog();
			// downloadProgressD.setContentPane(downloadProgressOP);
			// downloadProgressD.setSize(400,200);
			// downloadProgressD.setLocationRelativeTo(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		session = new WebAuthSession(appKeys, Session.AccessType.APP_FOLDER);
		authenticated = false;

	}

	/**
	 * Gets the authorization url.
	 *
	 * @return the authorization url
	 */
	public String getAuthorizationUrl() {
		WebAuthInfo authInfo;
		try {

			authInfo = session.getAuthInfo();
			pair = authInfo.requestTokenPair;
			String url = authInfo.url;
			return url;
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Start session.
	 *
	 * @return true, if successful
	 */
	// This function authenticates new user
	public boolean startSession() {

		if (!authenticated) {
			try {
				session.retrieveWebAccessToken(pair);
				authenticated = true;
			} catch (Exception e) {

			}

			if (authenticated) {
				AccessTokenPair tokens = session.getAccessTokenPair();

				api = new DropboxAPI<WebAuthSession>(session);
				try {
					System.out.println(api.accountInfo().displayName);
				} catch (DropboxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Upload folder.
	 *
	 * @param file the file
	 * @param path the path
	 * @param progressListener the progress listener
	 */
	public static void uploadFolder(File file, String path, progListener progressListener) {

		if (!file.exists())
			return;
		if (file.isDirectory()) {
			if (file.listFiles().length == 0)
				try {
					if (!file.getCanonicalPath().equals(rootPath))
						api.createFolder(path + file.getName());
				} catch (DropboxException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else {
				try {

					if (!file.getCanonicalPath().equals(rootPath))
						path = path + file.getName() + "/";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (File f : file.listFiles()) {
					uploadFolder(f, path, progressListener);
				}
			}
		} else {
			try {
				FileInputStream in = new FileInputStream(file);
				// downloadProgressD.setVisible(true);
				api.putFileOverwrite(path + file.getName(), in, file.length(), progressListener);
			} catch (FileNotFoundException | DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Download folder.
	 *
	 * @param path the path
	 * @param dropPath the drop path
	 * @param progressListener the progress listener
	 */
	public static void downloadFolder(String path, String dropPath, progListener progressListener) {
		FileOutputStream outputStream = null;
		try {
			File file = new File(path);

			Entry existingFile = api.metadata(dropPath, 0, null, true, null);
			if (existingFile.isDir) {
				// if(existingFile.contents.isEmpty())

				file.mkdir();
				for (Entry entry : existingFile.contents) {
					downloadFolder(path + "/" + entry.fileName(), dropPath + "/" + entry.fileName(), progressListener);
				}
			} else {
				outputStream = new FileOutputStream(file);

				// downloadProgressD.setVisible(true);

				DropboxFileInfo info = api.getFile(dropPath, null, outputStream, progressListener);
				// downloadProgressD.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recursive delete dropbox folder.
	 *
	 * @param path the path
	 */
	public static void recursiveDeleteDropboxFolder(String path) {
		try {
			Entry existingFile = api.metadata(path, 0, null, true, null);

			if (existingFile.isDir) {
				for (int i = 0; i < existingFile.contents.size(); i++) {
					recursiveDeleteDropboxFolder(path + "/" + existingFile.contents.get(i).fileName());
				}
				if (!path.equals("/"))
					api.delete(path);
			} else
				api.delete(path);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the dropbox total size.
	 *
	 * @param path the path
	 * @param sum the sum
	 * @return the dropbox total size
	 */
	public static long getDropboxTotalSize(String path, long sum) {
		try {
			Entry existingFile = api.metadata(path, 0, null, true, null);

			if (existingFile.isDir) {
				for (int i = 0; i < existingFile.contents.size(); i++) {
					sum += getDropboxTotalSize(path + "/" + existingFile.contents.get(i).fileName(), 0);
				}
				return sum;
			} else
				return existingFile.bytes;
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Gets the total dropbox size.
	 *
	 * @return the total dropbox size
	 */
	public double getTotalDropboxSize() {
		return totalDropboxSize;
	}

	/**
	 * Sets the total dropbox size.
	 *
	 * @param size the new total dropbox size
	 */
	public static void setTotalDropboxSize(double size) {
		totalDropboxSize = size;
	}

}
