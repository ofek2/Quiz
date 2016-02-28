package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;

import Controllers.MainFrameController;
import Views.Main;
import Views.StudentGradingPanel;
import Views.ViewPanel;
import netscape.javascript.JSObject;


public class GradingOperation extends ViewPanel implements Runnable{
	//private final Scene scene;
	private final JFXPanel fxPanel;
	private StudentGradingPanel studentGradingPanel;
	private String studentQuizPath;
	private JMenu gradingMenu;
	private Container previousView;
	private Runnable run;
	private Thread thread;
	@SuppressWarnings("deprecation")
	public GradingOperation(StudentGradingPanel studentGradingPanel,
			String studentQuizPath, Container previousView) {
		this.studentGradingPanel = studentGradingPanel;
		this.studentQuizPath = studentQuizPath;
		this.previousView = previousView;
		setLayout(null);
		fxPanel = new JFXPanel();
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(java.awt.Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);
		
		gradingMenu = new JMenu("File");
		menuBar.add(gradingMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		gradingMenu.add(mntmSave);
		mntmSave.addActionListener(new SaveListener());
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		gradingMenu.add(mntmExit);
		mntmExit.addActionListener(new ExitListener());
//		ActionListener[] fileListeners = {new SaveListener(),new ExitListener()};
		fxPanel.setSize(1250, 800);
		add(fxPanel);
//		run = new Runnable() {///////////////////
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				initFX(fxPanel);
//			}
//		};
//		if(thread.isAlive())
//			thread.destroy();
		thread = new Thread(this);/////////////
//		thread.start();
		
		Platform.runLater(thread);//////////////////
//		Platform.runLater(this);
//		  Platform.runLater(new Runnable() {
//		      @Override
//		      public void run() {
//		    	  System.out.println("1");
//		        initFX(fxPanel);
//		      }
//		    });
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("1");
		initFX(fxPanel);
	}
	

	protected void initFX(JFXPanel fxPanel) {
		// TODO Auto-generated method stub
		final Scene scene = new Scene(new Browser(), 1250, 800, Color.web("#666970"));
		fxPanel.setScene(scene);
		
	}

	
//	public void addFileListeners(ActionListener[] listener)
//	{
//		for (int i = 0; i < listener.length; i++) {
//			gradingMenu.getItem(i).addActionListener(listener[i]);
//		}
//	}

	
	class SaveListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}
		
	}
	
	class ExitListener implements ActionListener
	{

//		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			Platform.runLater(new Thread(run));
//			if(thread.isAlive())
//				thread.destroy();
			MainFrameController.view.changeContentPane((ViewPanel)previousView);
//			thread.interrupt();

//			thread.stop();
		}
		
	}


	class Browser extends Region {

		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		public Browser() {
			// apply the styles
			getStyleClass().add("browser");
			// load the web page
//			try {
				webEngine
						.load("file:///"+studentQuizPath);
//				new File(".").getCanonicalPath()
//				+"/OnlineQuizChecker/1,sss/Quizzes/shit/StudentsAnswers/shit.html");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			// add the web view to the scene
			getChildren().add(browser);
			webEngine
					.getLoadWorker()
					.stateProperty()
					.addListener(
							(obs, oldValue, newValue) -> {
								if (newValue == Worker.State.SUCCEEDED) {

									JSObject jsobj = (JSObject) webEngine
											.executeScript("window");
									jsobj.setMember("Desktop", new Desktop());
								}
							});

			JSObject jsobj = (JSObject) webEngine.executeScript("window");
			jsobj.setMember("Desktop", new Desktop());
		}

		private Node createSpacer() {
			Region spacer = new Region();
			HBox.setHgrow(spacer, Priority.ALWAYS);
			return spacer;
		}

		@Override
		protected void layoutChildren() {
			double w = getWidth();
			double h = getHeight();
			layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
		}

		@Override
		protected double computePrefWidth(double height) {
			return 750;
		}

		@Override
		protected double computePrefHeight(double width) {
			return 500;
		}
	}

	public class Desktop {
		public void receiveInput(String score,String questionNumber) {
			// Platform.exit();
			System.out.print(score+","+questionNumber);
		}
	}


}