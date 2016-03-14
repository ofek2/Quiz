import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Controllers.DropBoxSimple;
import Controllers.MainFrameController;
import Views.MainFrameView;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import static javafx.concurrent.Worker.State.FAILED;

public class Main extends JFrame {

	public static DropBoxSimple dbx;
	public static String userEmail;
	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;

	private final JPanel panel = new JPanel(new BorderLayout());

	public Main() {
		super();
		initComponents();

	}

	private void initComponents() {
		createScene();

		panel.add(jfxPanel, BorderLayout.CENTER);
		getContentPane().add(panel);

		setPreferredSize(new Dimension(600, 600));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	private void createScene() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				WebView view = new WebView();
				engine = view.getEngine();

				engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
					@Override
					public void handle(final WebEvent<String> event) {

						if (dbx.startSession()) {
							engine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
								if (newValue == Worker.State.SUCCEEDED) {
									Document doc = engine.getDocument();

									NodeList divs = doc.getElementsByTagName("div");

									for (int i = 0; i < divs.getLength(); i++)
										if (((Element) divs.item(i)).getAttribute("class") != null)
											if (((Element) divs.item(i)).getAttribute("class")
													.equals("email force-no-break"))
												userEmail = ((Element) divs.item(i)).getTextContent();
									// stage.close();

									// Platform.exit();

									new MainFrameController(new MainFrameView());
									setVisible(false);

									engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {

										@Override
										public void handle(WebEvent<String> event) {
											// TODO Auto-generated method stub

										}
									});

								}
							});

						}

					}
				});

				jfxPanel.setScene(new Scene(view));
			}
		});
	}

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

	private static String toURL(String str) {
		try {
			return new URL(str).toExternalForm();
		} catch (MalformedURLException exception) {
			return null;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Main browser = new Main();
				browser.setVisible(true);
				dbx = new DropBoxSimple();
				String url = dbx.getAuthorizationUrl();
				browser.loadURL(url);

			}
		});
	}
}