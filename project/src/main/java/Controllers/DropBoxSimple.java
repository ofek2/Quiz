package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.io.comparator.PathFileComparator;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DeltaEntry;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;

import Entities.Constants;
import Utilities.progListener;

/**
 * The Class DropBoxSimple.
 * This class simplifies Dropbox actions.
 */
public class DropBoxSimple {
	
	/** The app key. */
	final String APP_KEY = "6uzu0uyprajxb0p";
	
	/** The app secret. */
	final String APP_SECRET = "e7iwzdqp4rwtu88";
	
	/** The root path. */
	public static String rootPath;
	
	static {
		try {
			rootPath = new File(".").getCanonicalPath() + "\\"+Constants.APP_NAME;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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

	/** The progress listener. */
	public static progListener progressListener;
	
	/** The local paths. */
	private static ArrayList<String> localPaths;
	/** The download progress label. */
//	public static JLabel downloadProgressLabel;
	
	/** The download progress d. */
//	public static JDialog downloadProgressD;
	
	

	/**
	 * Instantiates a new drop box simple.
	 */
	public DropBoxSimple() {
	
		appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		session = new WebAuthSession(appKeys, Session.AccessType.APP_FOLDER);
		authenticated = false;
		progressListener = new progListener(0, "");
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
	 * Start authentication session.
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
				api = new DropboxAPI<WebAuthSession>(session);
				try {
					System.out.println(api.accountInfo().displayName +" is logged in");
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
	 * Upload folder to Dropbox account.
	 *
	 * @param file the file/folder
	 * @param path the path to upload a file/folder to
	 */
	public static void uploadFolder(File file, String path) {

		if (!file.exists())
			return;
		if (file.isDirectory()) {
			if (file.listFiles().length == 0)
				try {
					if (!file.getCanonicalPath().equals(rootPath))
					{
							//If this path doesn't exists a new folder will be created in dropbox
							try {
								api.createFolder(path + file.getName());
							} catch (DropboxException e1) {
								// do nothing if folder already exists in dropbox
							}
					}
				} catch (IOException e) {
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
					uploadFolder(f, path);
				}
			}
		} else {
			try {
				FileInputStream in = new FileInputStream(file);
				
				api.putFileOverwrite(path + file.getName(), in, file.length(), progressListener);
			} catch (FileNotFoundException | DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Download folder from Dropbox account.
	 *
	 * @param path the path to get the file/folder from
	 * @param dropPath the path in Dropbox
	 */
	public static void downloadFolder(String path, String dropPath) {
		FileOutputStream outputStream = null;
		try {
			File file = new File(path);

			Entry existingFile = api.metadata(dropPath, 0, null, true, null);
			if (existingFile.isDir) {
				file.mkdir();
				for (Entry entry : existingFile.contents) {
					downloadFolder(path + "/" + entry.fileName(), dropPath + "/" + entry.fileName());
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
	 * Delete removed files from Dropbox.
	 */
	public static void deleteRemovedFilesFromDropbox()
	{
		List<DeltaEntry<Entry>> entries;
		localPaths = new ArrayList<String>();
		try {
				entries = api.delta(null).entries;
				String [] pathsSorted = sortDBPaths(entries);
				File appFolder = new File(rootPath);
				listLocalPaths(appFolder);
				boolean delete = true;
				for(int i=0;i<pathsSorted.length;i++)
				{
					delete = true;
					for(int j=0;j<localPaths.size();j++)
						if(pathsSorted[i].equals(localPaths.get(j)))
								delete=false;
					if(delete)
						api.delete(pathsSorted[i].replaceAll("\\\\", Matcher.quoteReplacement("/")));
				}
			} catch (DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Sort Dropbox paths.
	 *
	 * @param entries the entries
	 * @return the Dropbox paths sorted in String[]
	 */
	private static String [] sortDBPaths(List<DeltaEntry<Entry>> entries) {
		String [] entriesArr = new String[entries.size()]; 
		for(int i=0;i<entries.size();i++)
			entriesArr[i]=entries.get(i).metadata.path;
	
		File [] entriesAsFiles = new File[entriesArr.length];
		for(int i=0;i<entriesAsFiles.length;i++)
		{
			entriesAsFiles[i] = new File(entriesArr[i]);
		}
		Arrays.sort(entriesAsFiles,PathFileComparator.PATH_REVERSE);
		String [] strPaths = new String[entriesAsFiles.length];
		for(int i=0;i<strPaths.length;i++)
		{
			try {
				strPaths[i]= entriesAsFiles[i].getCanonicalPath().substring(2); //cuts the path beginning
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return strPaths;
	}

	/**
	 * List local paths.
	 *
	 * @param file the initial folder
	 */
	private static void listLocalPaths(File file) 
	{
		if(!file.exists())
		return;
		if(file.isDirectory())
		{
			try {
				String path = changeToDBPathFormat(file.getCanonicalPath());
				if (!path.isEmpty())
				localPaths.add(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0;i<file.listFiles().length;i++)
				{
					listLocalPaths(file.listFiles()[i]);
				}
		
		}
		else
			try {
				localPaths.add(changeToDBPathFormat(file.getCanonicalPath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Change to Dropbox path format.
	 *
	 * @param localPath the local path
	 * @return converted path as a String
	 */
	private static String changeToDBPathFormat(String localPath)
	{
		String trimed="";
		trimed	= localPath.replace(rootPath,Matcher.quoteReplacement(""));
		return trimed;
		
	}

	/**
	 * Gets the Dropbox total size.
	 *
	 * @return the Dropbox total size
	 */
	public static long getDropboxTotalSize() {
		List<DeltaEntry<Entry>> entries;
		long sum = 0;
		try {
			entries = api.delta(null).entries;
			
			for(int i = 0 ;i<entries.size();i++)
			{
				sum += entries.get(i).metadata.bytes;
			
			}
			
			return sum;
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	

}
