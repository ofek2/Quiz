package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import project.GoogleMail;

/**
 * The Class GmailAuthFrame.
 * This class presents the Gmail authorization screen.
 */
public class GmailAuthFrame extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The jfx panel. */
	private final JFXPanel jfxPanel = new JFXPanel();
	
	/** The engine. */
	private WebEngine engine;
	
	/** The card panel. */
	private JPanel cardPanel;
	
	/** The layout. */
	private CardLayout layout;
	
	/** The send mail panel. */
	private JPanel sendMailPanel;
	
	/** The send button. */
	private JButton sendBtn;
	
	/** The authorization code. */
	private String authorizationCode;
	
	/**
	 * Instantiates a new gmail auth frame.
	 */
	public GmailAuthFrame()
	{
		initComponents();

	}
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		createScene();
		JPanel gmailPanel = new JPanel(new BorderLayout());
		gmailPanel.add(jfxPanel, BorderLayout.CENTER);

		layout = new CardLayout(0,0);
		cardPanel = new JPanel(layout);
		
		sendMailPanel = new JPanel();
		sendMailPanel.setLayout(new BoxLayout(sendMailPanel,BoxLayout.Y_AXIS));
		sendMailPanel.setSize(600,220);
		sendBtn = new JButton("Send Grades");
		
	
		sendBtn.setFont(new Font("Ariel", Font.BOLD, 16));
		JLabel label1 = new JLabel("Click the button bellow if you want");
		JLabel label2 = new JLabel("to send the graded quizzes to your students");
		label1.setSize(label1.getPreferredSize());
		label1.setFont(new Font("Ariel", Font.PLAIN, 18));
		label2.setSize(label1.getPreferredSize());
		label2.setFont(new Font("Ariel", Font.PLAIN, 18));
	
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		sendBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sendMailPanel.add(Box.createRigidArea(new Dimension(0,20)));
		sendMailPanel.add(label1);
		sendMailPanel.add(Box.createRigidArea(new Dimension(0,5)));
		sendMailPanel.add(label2);
		sendMailPanel.add(Box.createRigidArea(new Dimension(0,30)));
		sendMailPanel.add(sendBtn);
		
		cardPanel.add(gmailPanel,"BeforeAuth");
		cardPanel.add(sendMailPanel,"AfterAuth");
		layout.show(cardPanel, "BeforeAuth");
		getContentPane().add(cardPanel);
	
		setPreferredSize(new Dimension(600, 600));
		
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
	                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
	                        SwingUtilities.invokeLater(new Runnable() {
	                            @Override 
	                            public void run() {
	                            	
	                               if(newValue!= null && newValue.contains("Success code"))
	                               {
	                            	  
	                            	   authorizationCode = newValue.substring(newValue.indexOf('=')+1);
	                            		
	                        			try {
											GoogleMail.finishAuth(authorizationCode);
											layout.next(cardPanel);
											setPreferredSize(new Dimension(600, 220));										
											pack();						
											setLocationRelativeTo(null);
										} catch (IOException e) {
											e.printStackTrace();
										}
	                               }
	                            }
	                        });
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
	
	/**
	 * Gets the engine.
	 *
	 * @return the engine
	 */
	public WebEngine getEngine() {
		return engine;
	}
	
	/**
	 * Sets the engine.
	 *
	 * @param engine the new engine
	 */
	public void setEngine(WebEngine engine) {
		this.engine = engine;
	}
	
	/**
	 * Gets the card panel.
	 *
	 * @return the card panel
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	/**
	 * Sets the card panel.
	 *
	 * @param cardPanel the new card panel
	 */
	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#getLayout()
	 */
	public CardLayout getLayout() {
		return layout;
	}
	
	/**
	 * Sets the layout.
	 *
	 * @param layout the new layout
	 */
	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}
	
	/**
	 * Gets the send mail panel.
	 *
	 * @return the send mail panel
	 */
	public JPanel getSendMailPanel() {
		return sendMailPanel;
	}
	
	/**
	 * Sets the send mail panel.
	 *
	 * @param sendMailPanel the new send mail panel
	 */
	public void setSendMailPanel(JPanel sendMailPanel) {
		this.sendMailPanel = sendMailPanel;
	}
	
	/**
	 * Gets the send button.
	 *
	 * @return the send button
	 */
	public JButton getSendBtn() {
		return sendBtn;
	}
	
	/**
	 * Sets the send button.
	 *
	 * @param sendBtn the new send button
	 */
	public void setSendBtn(JButton sendBtn) {
		this.sendBtn = sendBtn;
	}
	
	/**
	 * Gets the jfx panel.
	 *
	 * @return the jfx panel
	 */
	public JFXPanel getJfxPanel() {
		return jfxPanel;
	}
	
	/**
	 * Gets the authorization code.
	 *
	 * @return the authorization code
	 */
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	
	/**
	 * Sets the authorization code.
	 *
	 * @param authorizationCode the new authorization code
	 */
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	/**
	 * Send button add listener.
	 *
	 * @param listener the listener
	 */
	public void sendBtnAddListener(ActionListener listener)
	{
		sendBtn.addActionListener(listener);
	}
}
