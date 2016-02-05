// Include the Dropbox SDK.
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.core.*;
import com.dropbox.core.v2.*;
import java.io.*;
import java.net.URL;
import java.util.Locale;

public class DropboxTest {
    public static void main(String[] args) throws IOException, DbxException {
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "6uzu0uyprajxb0p";
        final String APP_SECRET = "e7iwzdqp4rwtu88";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
        
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
      
     // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
   /*     URL url = new URL(authorizeUrl);
        InputStream is = (InputStream) url.getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while((line = br.readLine()) != null){
          sb.append(line);
        }
        String htmlContent = sb.toString();*/
        
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
