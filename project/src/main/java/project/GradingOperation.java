package project;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
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
import netscape.javascript.JSObject;

@SuppressWarnings("restriction")
public class GradingOperation extends Application {
	private Scene scene;
	private StudentGradingPanel studentGradingPanel;

	public GradingOperation(StudentGradingPanel studentGradingPanel) {
		this.studentGradingPanel = studentGradingPanel;
		launch(Main.mainArgs);
	}

	@Override
	public void start(Stage stage) {
		// create the scene
		stage.setTitle("Web View");
		scene = new Scene(new Browser(), 750, 500, Color.web("#666970"));
		stage.setScene(scene);
		// scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
		stage.show();
		
	}

//	 public static void main(String[] args){
//	 launch(args);
//	 }

	class Browser extends Region {

		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		public Browser() {
			// apply the styles
			getStyleClass().add("browser");
			// load the web page
			try {
				webEngine
						.load(new File(".").getCanonicalPath()
								+"/OnlineQuizChecker/2,b/Quizzes/1/StudentsAnswers/1.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
									jsobj.setMember("app", new Bridge());
								}
							});

			JSObject jsobj = (JSObject) webEngine.executeScript("window");
			jsobj.setMember("app", new Bridge());
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

	public class Bridge {
		public void exit(String str) {
			// Platform.exit();
			System.out.print(str);
		}
	}
}