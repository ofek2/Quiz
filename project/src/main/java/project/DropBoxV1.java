package project;

import java.util.Locale;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.v1.DbxClientV1;

public class DropBoxV1 {
	final String APP_KEY = "6uzu0uyprajxb0p";
	final String APP_SECRET = "e7iwzdqp4rwtu88";

	public static DbxClientV1 client;
	private DbxWebAuthNoRedirect webAuth;
	private DbxRequestConfig config;
	public DropBoxV1() {
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		
	}
	public String getAuthorizeationUrl()
	{
		
		return webAuth.start();
	}
	public String createTokenFromCode(String code)
	{
		DbxAuthFinish authFinish;
		try {
			authFinish = webAuth.finish(code);
			return authFinish.accessToken;
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void createClient(String accessToken)
	{
		client = new DbxClientV1(config, accessToken);
		try {
			System.out.println(client.getAccountInfo().email);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
