package Controllers;

import java.awt.Desktop;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;

public class DropBoxSimple {
	final String APP_KEY = "6uzu0uyprajxb0p";
	final String APP_SECRET = "e7iwzdqp4rwtu88";

	public static DropboxAPI<WebAuthSession> api;

	private AppKeyPair appKeys;
	private WebAuthSession session;
	private RequestTokenPair pair;
	private boolean authenticated ;
	public DropBoxSimple() {

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

	// This function authenticates an authorized user
	public void startSession(String key, String secret) {
		AccessTokenPair tokens = new AccessTokenPair(key, secret);
		session.setAccessTokenPair(tokens);
		api = new DropboxAPI<WebAuthSession>(session);
	}
	public static void uploadFolder(File file,String path)
	{
		if (!file.exists())
            return;
        if (file.isDirectory()) {
        	if(file.listFiles().length == 0)
				try {
					api.createFolder(path+"/"+file.getName());
				} catch (DropboxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	else{
        	 for (File f : file.listFiles()) {
        		 uploadFolder(f,path+"/"+file.getName());
        	 }
        	}
        }
        else{
		try {
			FileInputStream in = new FileInputStream(file);
			api.putFileOverwrite(path+"/"+file.getName(), in, file.length(), null);
		} catch (FileNotFoundException | DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
			
		
	}
	public static void downloadFolder(String path,String dropPath)
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
		    		downloadFolder(path+"/"+entry.fileName(),dropPath+"/"+entry.fileName());
		    	}
		    }
		    else
		    {
		    	 outputStream = new FileOutputStream(file);
		    	DropboxFileInfo info = api.getFile(dropPath, null, outputStream, null);
		    	
		    }
		} catch (Exception e) {
		   e.printStackTrace();
		} 
	}
	class progListener extends ProgressListener
	{

		@Override
		public void onProgress(long arg0, long arg1) {
			// TODO Auto-generated method stub
		//	api.
		}
		
	}
}