package project;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GoogleMail {
	public static Gmail service;
	/** Application name. */
	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(".", ".credentials/GoogleMail.json");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/gmail-java-quickstart.json
	 */
	private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_SEND);

	private static final String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
	
	private static GoogleAuthorizationCodeFlow flow;
	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	
	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static String startAuthorize() throws IOException {
		// Load client secrets.
		InputStream in = GoogleMail.class.getResourceAsStream("/client_secrets.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		String authorizeUrl = flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URL).build();
	
		return authorizeUrl;
	}
	public static void finishAuth(String authorizationCode) throws IOException
	{
		
		// Load client secrets.
		InputStream in = GoogleMail.class.getResourceAsStream("/client_secrets.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		// Authorize the OAuth2 token.
		GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(authorizationCode);
		tokenRequest.setRedirectUri(CALLBACK_URL);
		GoogleTokenResponse tokenResponse = tokenRequest.execute();
		System.out.println(tokenRequest.toString());
		System.out.println(tokenResponse.getAccessToken());
		// Credential credential = null;
		// Create the OAuth2 credential.
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(new NetHttpTransport())
				.setJsonFactory(new JacksonFactory()).setClientSecrets(clientSecrets).build();

		// Set authorized credentials.
		credential.setFromTokenResponse(tokenResponse);
	
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		
		service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

	}
//	/**
//	 * Build and return an authorized Gmail client service.
//	 * 
//	 * @return an authorized Gmail client service
//	 * @throws IOException
//	 */
//	public static Gmail getGmailService() throws IOException {
//		Credential credential = startAuthorize();
//		return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
//	}

	public static void SendMail(String studentsMail, String subject, String quizPath,
			String fileName) {
		// Build a new authorized API client service.

		try {
			MimeMessage email;
			email = createEmailWithAttachment(studentsMail, "me", subject, "",quizPath,fileName);
			Message message = createMessageWithEmail(email);
			message = service.users().messages().send("me", message).execute();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);
		InternetAddress tAddress = new InternetAddress(to);
		InternetAddress fAddress = new InternetAddress(from);

		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	public static Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		email.writeTo(bytes);
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	public static MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText,
			String filePath, String filename) throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);
		InternetAddress tAddress = new InternetAddress(to);
		InternetAddress fAddress = new InternetAddress(from);

		email.setFrom(fAddress);
		email.addRecipient(javax.mail.Message.RecipientType.TO, tAddress);
		email.setSubject(subject);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(bodyText, "text/plain");
		mimeBodyPart.setHeader("Content-Type", "text/plain; charset=\"UTF-8\"");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		mimeBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(filePath);

		mimeBodyPart.setDataHandler(new DataHandler(source));
		mimeBodyPart.setFileName(filename);
		String contentType = Files.probeContentType(FileSystems.getDefault().getPath(filePath));
		mimeBodyPart.setHeader("Content-Type", contentType + "; name=\"" + filename + "\"");
		mimeBodyPart.setHeader("Content-Transfer-Encoding", "base64");

		multipart.addBodyPart(mimeBodyPart);

		email.setContent(multipart);

		return email;
	}

}