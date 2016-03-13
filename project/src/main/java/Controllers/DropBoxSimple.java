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

public class DropBoxSimple {
	final String APP_KEY = "6uzu0uyprajxb0p";
	final String APP_SECRET = "e7iwzdqp4rwtu88";
	public static String rootPath;
	public static DropboxAPI<WebAuthSession> api;
//	public static DbxClientV1 client;
//	private  DbxRequestConfig config ;
	private AppKeyPair appKeys;
	private WebAuthSession session;
	private RequestTokenPair pair;
	private boolean authenticated;

	public static JLabel downloadProgressLabel;
	public static JDialog downloadProgressD;
	private static double totalDropboxSize;
	public DropBoxSimple() {
		try {
			rootPath= new File(".").getCanonicalPath()+"\\OnlineQuizChecker";
			
			downloadProgressLabel = new JLabel("");
			DropBoxSimple.downloadProgressLabel.setLocation(100,0);
			downloadProgressD = new JDialog();
			downloadProgressD.setLayout(new BorderLayout());
			downloadProgressD.setSize(400,200);
			downloadProgressD.setLocationRelativeTo(null);
			downloadProgressD.add(DropBoxSimple.downloadProgressLabel,BorderLayout.CENTER);
//	    	downloadProgressD = new JDialog();
//	    	downloadProgressD.setContentPane(downloadProgressOP);
//			downloadProgressD.setSize(400,200);
//			downloadProgressD.setLocationRelativeTo(null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		session = new WebAuthSession(appKeys, Session.AccessType.APP_FOLDER);
		authenticated = false;
		
	}

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

	// This function authenticates new user
	public boolean startSession() {
		

		if(!authenticated)
		{
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

	/*class UploadFolder extends Thread
	{
		private File file;
		private String path;
		public UploadFolder(File file,String path)
		{
			this.file=file;
			this.path=path;
		}
		public void run()
		{
			uploadFolder(file, path);
		}
	}
	class DownloadFolder extends Thread
	{
		private String path;
		private String dropPath;
		public DownloadFolder(String path,String dropPath)
		{
			this.path=path;
			this.dropPath = dropPath;
		}
		public void run()
		{
			downloadFolder(path, dropPath);
		}
	}
	class DeleteFolder extends Thread
	{
		private String path;
		public DeleteFolder(String path)
		{
			this.path = path;
		}
		public void run()
		{
			recursiveDeleteDropboxFolder(path);
		}
	}*/
	public static void uploadFolder(File file,String path, progListener progressListener)
	{
		
		if (!file.exists())
            return;
        if (file.isDirectory()) {
        	if(file.listFiles().length == 0)
				try {
					if(!file.getCanonicalPath().equals(rootPath))
					api.createFolder(path+file.getName());
				} catch (DropboxException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	else{
        		try {
        		
					if(!file.getCanonicalPath().equals(rootPath))
					path=path+file.getName()+"/";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	 for (File f : file.listFiles()) {
        		 uploadFolder(f,path,progressListener);
        	 }
        	}
        }
        else{
		try {
			FileInputStream in = new FileInputStream(file);
//			downloadProgressD.setVisible(true);	 
			api.putFileOverwrite(path+file.getName(), in, file.length(), progressListener);
		} catch (FileNotFoundException | DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
			
		
	}
	public static void downloadFolder(String path,String dropPath,progListener progressListener)
	{
		FileOutputStream outputStream = null;
		try {
		    File file = new File(path);
		   
		    Entry existingFile =api.metadata(dropPath, 0, null, true, null);
		    if(existingFile.isDir)
		    {
		    	//if(existingFile.contents.isEmpty())
		
		    	file.mkdir();
		    	for(Entry entry:existingFile.contents)
		    	{
		    		downloadFolder(path+"/"+entry.fileName(),dropPath+"/"+entry.fileName(),progressListener);
		    	}
		    }
		    else
		    {
		    	outputStream = new FileOutputStream(file);
		
//		    	downloadProgressD.setVisible(true);	    	 
		    	
		    	DropboxFileInfo info = api.getFile(dropPath, null, outputStream,progressListener);
		    	//downloadProgressD.setVisible(false);
		    }
		} catch (Exception e) {
		   e.printStackTrace();
		} 
	}
	public static void recursiveDeleteDropboxFolder(String path)
	{
		 try {
			Entry existingFile =api.metadata(path, 0, null, true, null);
			
			if(existingFile.isDir)
			{
				for(int i = 0;i<existingFile.contents.size();i++)
		    	{
		    		recursiveDeleteDropboxFolder(path+"/"+existingFile.contents.get(i).fileName());
		    	}
				if(!path.equals("/"))
				api.delete(path);
			}
			else
				api.delete(path);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static long getDropboxTotalSize(String path,long sum)
	{
		 try {
				Entry existingFile =api.metadata(path, 0, null, true, null);
				
				if(existingFile.isDir)
				{
					for(int i = 0;i<existingFile.contents.size();i++)
			    	{
						sum+=getDropboxTotalSize(path+"/"+existingFile.contents.get(i).fileName(),0);
			    	}
					return sum;
				}
				else
					return existingFile.bytes;
			} catch (DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return 0;
	}
	
	public double getTotalDropboxSize() {
		return totalDropboxSize;
	}

	public static void setTotalDropboxSize(double size) {
		totalDropboxSize = size;
	}
	
}
