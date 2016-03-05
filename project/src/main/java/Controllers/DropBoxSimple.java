package Controllers;

import java.awt.Desktop;
import java.io.Console;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

import com.dropbox.client2.DropboxAPI;
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

	public DropboxAPI<WebAuthSession> api;

	private AppKeyPair appKeys;
	private WebAuthSession session;
	private RequestTokenPair pair;

	public DropBoxSimple() {

		appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		session = new WebAuthSession(appKeys, Session.AccessType.DROPBOX);

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
	public void startSession() {
		boolean authenticated = false;

		while (!authenticated) {
			try {
				session.retrieveWebAccessToken(pair);

				authenticated = true;
			} catch (Exception e) {

			}
		}

		AccessTokenPair tokens = session.getAccessTokenPair();
	
		api = new DropboxAPI<WebAuthSession>(session);
	}

	// This function authenticates an authorized user
	public void startSession(String key, String secret) {
		AccessTokenPair tokens = new AccessTokenPair(key, secret);
		session.setAccessTokenPair(tokens);
		api = new DropboxAPI<WebAuthSession>(session);
	}
}
