package Views;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;
import Controllers.DropBoxSimple;
import Controllers.IntroFrameController;
import Controllers.MainFrameController;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Class DropboxAuthentication.
 * This class is used for the Dropbox login sequence.
 */
public class DropboxAuthentication extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dbx. */
	public static DropBoxSimple dbx;
	
	/** The jfx panel. */
	private final JFXPanel jfxPanel = new JFXPanel();
	
	/** The engine. */
	private WebEngine engine;
	
	/** The authorized. */
	private boolean authorized = false;
	
	/** The panel. */
	private final JPanel panel = new JPanel(new BorderLayout());

	/**
	 * Instantiates a new dropbox authentication.
	 */
	public DropboxAuthentication() {
		super();
		setTitle("Online Quiz Checker");
		initComponents();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dbx = new DropBoxSimple();
				String url = dbx.getAuthorizationUrl();
				loadURL(url);
			}
		});
	
	}

	/**
	 * Inits the components.
	 */
	private void initComponents() {
		createScene();
		panel.add(jfxPanel, BorderLayout.CENTER);
		getContentPane().add(panel);

		setPreferredSize(new Dimension(600, 600));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Creates the scene.
	 */
	private void createScene() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				WebView view = new WebView();
				engine = view.getEngine();

				engine.titleProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							final String newValue) {
						if (newValue != null && newValue.equals("API Request Authorized - Dropbox")) {
							if (authorized == false && dbx.startSession()) {
								engine.getLoadWorker().stateProperty().addListener((obs, oldVal, newVal) -> {
									if (newVal == Worker.State.SUCCEEDED) {
										authorized = true;
										new MainFrameController(new MainFrameView());
										setVisible(false);
									}
								});

							}
						} else if (newValue != null && newValue.equals("Home - Dropbox")) {
							new IntroFrameController(new IntroFrame());
							setVisible(false);
						}
					}
				});
				jfxPanel.setScene(new Scene(view));
			}
		});
	}

	/**
	 * Load url.
	 *
	 * @param url the url
	 */
	public void loadURL(final String url) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String tmp = toURL(url);

				if (tmp == null) {
					tmp = toURL("http://" + url);
				}

				engine.load(tmp);
			}
		});
	}

	/**
	 * To url.
	 *
	 * @param str the str
	 * @return the string
	 */
	private static String toURL(String str) {
		try {
			return new URL(str).toExternalForm();
		} catch (MalformedURLException exception) {
			return null;
		}
	}

	

}