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

public class GmailAuthFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;
	private JPanel cardPanel;
	private CardLayout layout;
	private JPanel sendMailPanel;
	private JButton sendBtn;
	private String authorizationCode;
	public GmailAuthFrame()
	{
		initComponents();

	}
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
											// TODO Auto-generated catch block
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
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public void sendBtnAddListener(ActionListener listener)
	{
		sendBtn.addActionListener(listener);
	}
}
