package project;

import java.io.File;
import java.io.IOException;

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

import javax.swing.event.ChangeListener;

import Views.Main;
import Views.StudentGradingPanel;
import Views.ViewPanel;
import netscape.javascript.JSObject;


public class GradingOperation extends ViewPanel{
	//private final Scene scene;
	private final JFXPanel fxPanel;
	private StudentGradingPanel studentGradingPanel;
	private String studentQuizPath;
	public GradingOperation(StudentGradingPanel studentGradingPanel, String studentQuizPath) {
		this.studentGradingPanel = studentGradingPanel;
		this.studentQuizPath = studentQuizPath;
	    fxPanel = new JFXPanel();
		
		add(fxPanel);
		  Platform.runLater(new Runnable() {
		      @Override
		      public void run() {
		        initFX(fxPanel);
		      }
		    });
	}

	


	protected void initFX(JFXPanel fxPanel) {
		// TODO Auto-generated method stub
		final Scene scene = new Scene(new Browser(), 1250, 800, Color.web("#666970"));
		fxPanel.setScene(scene);
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