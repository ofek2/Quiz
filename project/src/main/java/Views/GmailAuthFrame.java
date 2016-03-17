package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GmailAuthFrame extends JFrame{
	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;
	private JPanel cardPanel;
	private CardLayout layout;
	private JPanel sendMailPanel;
	private JButton sendBtn;
	public GmailAuthFrame()
	{
		initComponents();

	}
	private void initComponents() {
		createScene();
		JPanel gmailPanel = new JPanel(new BorderLayout());
		gmailPanel.add(jfxPanel, BorderLayout.CENTER);
		getContentPane().add(gmailPanel);
		pack();
		layout = new CardLayout(0,0);
		cardPanel = new JPanel(layout);
		
		sendMailPanel = new JPanel(new BorderLayout());
		sendBtn = new JButton("Send Grades");
		sendBtn.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label = new JLabel("Send The Graded Quizzes To Your Students\nBy Clicking The Button Bellow:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		sendMailPanel.add(label,BorderLayout.NORTH);
		sendMailPanel.add(sendBtn,BorderLayout.CENTER);
		cardPanel.add(gmailPanel,"BeforeAuth");
		cardPanel.add(sendMailPanel,"AfterAuth");
		layout.show(gmailPanel, "BeforeAuth");
		
		setPreferredSize(new Dimension(600, 600));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
	}
	private void createScene() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				WebView view = new WebView();
				engine = view.getEngine();

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
	public WebEngine getEngine() {
		return engine;
	}
	public void setEngine(WebEngine engine) {
		this.engine = engine;
	}
	public JPanel getCardPanel() {
		return cardPanel;
	}
	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	public CardLayout getLayout() {
		return layout;
	}
	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}
	public JPanel getSendMailPanel() {
		return sendMailPanel;
	}
	public void setSendMailPanel(JPanel sendMailPanel) {
		this.sendMailPanel = sendMailPanel;
	}
	public JButton getSendBtn() {
		return sendBtn;
	}
	public void setSendBtn(JButton sendBtn) {
		this.sendBtn = sendBtn;
	}
	public JFXPanel getJfxPanel() {
		return jfxPanel;
	}
	
}
