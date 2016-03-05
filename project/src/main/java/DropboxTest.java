// Include the Dropbox SDK.
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
import com.dropbox.core.*;
import com.dropbox.core.v2.*;

import Controllers.DropBoxSimple;

import java.awt.Desktop;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

import javax.swing.JOptionPane;

public class DropboxTest {
    public static void main(String[] args) throws IOException, DbxException {
    	DropBoxSimple dbx = new DropBoxSimple();
    	try {
			Desktop.getDesktop().browse(new URL(dbx.getAuthorizationUrl()).toURI());
			dbx.startSession();
			System.out.println(dbx.api.accountInfo().displayName);
		} catch (URISyntaxException | DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "6uzu0uyprajxb0p";
        final String APP_SECRET = "e7iwzdqp4rwtu88";
     
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        
        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
 
        //DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        
        
     // Have the user sign in and authorize your app.
       // String authorizeUrl = webAuth.start();
   /*     URL url = new URL(authorizeUrl);
        InputStream is = (InputStream) url.getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while((line = br.readLine()) != null){
          sb.append(line);
        }
        String htmlContent = sb.toString();*/
/*
        WebAuthSession session = new WebAuthSession(appKeys, Session.AccessType.DROPBOX);
        WebAuthInfo authInfo;
		try {
			authInfo = session.getAuthInfo();
			  RequestTokenPair pair = authInfo.requestTokenPair;
		        String url = authInfo.url;
		        boolean b=false;
		        Desktop.getDesktop().browse(new URL(url).toURI());
		        //JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");
		        while (!b){
		        try {
		            session.retrieveWebAccessToken(pair);
		            b=true;
		        } catch (Exception e) {
		            System.out.println("authentication fail with exception:" + e);
		        	
		        }
		        }
		      
		        AccessTokenPair tokens = session.getAccessTokenPair();
		        System.out.println("Use this token pair in future so you don't have to re-authenticate each time:");
		        System.out.println("accessKey: " + tokens.key);
		        System.out.println("accessSecret: " + tokens.secret);
		        
		  
		} catch (DropboxException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DropboxAPI<WebAuthSession> api = new DropboxAPI<WebAuthSession>(session);
		
		try {
			System.out.println(api.accountInfo().displayName);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     /*   try {
			WebAuthInfo authInfo = session.getAuthInfo();
			String url = authInfo.url;
			while(!session.isLinked());
				System.out.println("Connected");
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        
        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.accessToken;
        
        DbxClient client = new DbxClient(config, accessToken);
        System.out.println("Linked account: " + client.getAccountInfo().displayName);
      /*  
        File inputFile = new File("c:\\H.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/test/H.txt",
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }*/
        
    }
}
