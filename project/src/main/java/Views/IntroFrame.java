package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controllers.IntroFrameController;
import Entities.Constants;
import javafx.scene.image.Image;

import java.awt.FlowLayout;
import java.io.InputStream;
import java.net.URL;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * The Class IntroFrame.
 * This class is a boundary controlled by {@link IntroFrameController}.
 */
public class IntroFrame extends JFrame{
	
	/** The login button. */
	private JButton loginBtn;
	
	/** The help button. */
	private JButton helpBtn;
	
	/**
	 * Instantiates a new intro frame.
	 */
	public IntroFrame()
	{
		setTitle("Online Quiz Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setSize(350,300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel helpBtnPanel = new JPanel();
		helpBtnPanel.setMaximumSize(new Dimension(32767, 100));
		helpBtnPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) helpBtnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(helpBtnPanel);
		
		helpBtn = new JButton("Help");
		helpBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		helpBtnPanel.add(helpBtn);
		JPanel logoPanel = new JPanel();
		logoPanel.setMaximumSize(new Dimension(32767, 100));
		logoPanel.setOpaque(false);
		logoPanel.setBackground(Color.WHITE);
		getContentPane().add(logoPanel);
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel braudeLbl = new JLabel("");
		URL url = getClass().getResource("/braudelogo.jpg");
		braudeLbl.setIcon(new ImageIcon(url));
		logoPanel.add(braudeLbl);
		
		JPanel welcomeLbl = new JPanel();
		welcomeLbl.setMinimumSize(new Dimension(10, 100));
		
		welcomeLbl.setMaximumSize(new Dimension(32767, 100));
		welcomeLbl.setOpaque(false);
		getContentPane().add(welcomeLbl);
		FlowLayout fl_welcomeLbl = new FlowLayout(FlowLayout.CENTER, 5, 5);
		welcomeLbl.setLayout(fl_welcomeLbl);
		
		JLabel lblWelcomeToOnlinequizchecker = new JLabel("Welcome to OnlineQuizChecker");
		lblWelcomeToOnlinequizchecker.setFont(new Font("Tahoma", Font.BOLD, 18));
		welcomeLbl.add(lblWelcomeToOnlinequizchecker);
		
		JPanel loginBtnPanel = new JPanel();
		loginBtnPanel.setMaximumSize(new Dimension(32767, 300));
		loginBtnPanel.setOpaque(false);
		getContentPane().add(loginBtnPanel);
		loginBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtnPanel.add(loginBtn);
		setVisible(true);
	}
	
	/**
	 * Adds the login btn listener.
	 *
	 * @param actionListener the action listener
	 */
	public void addLoginBtnListener(ActionListener actionListener)
	{
		loginBtn.addActionListener(actionListener);
	}
	
	/**
	 * Adds the help btn listener.
	 *
	 * @param actionListener the action listener
	 */
	public void addHelpBtnListener(ActionListener actionListener)
	{
		helpBtn.addActionListener(actionListener);
	}
}
