package Views;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Controllers.DropBoxSimple;
import Controllers.MainFrameController;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DropBoxAuthenticationView extends Application{
	public static DropBoxSimple dbx;
	public static String userEmail;
	private Stage stage;
	//public static DropBoxV1 dbx;
	@Override
    public void start(Stage stage) {
		this.stage=stage;
        stage.setTitle("OnlineQuizChecker");
        stage.setWidth(600);
        stage.setHeight(600);
        Scene scene = new Scene(new Browser(), 600,600, Color.web("#666970"));
       // VBox root = new VBox();    
      
       
        //root.getChildren().addAll(browser);
      //  scene.setRoot(root);
      
        stage.setScene(scene);
        stage.show();
       ////////////////////////////////////This is for later use://// new MainFrameController(new MainFrameView());
    
    }
	class Browser extends Region {

		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		public Browser() {
			// apply the styles
		//	getStyleClass().add("browser");
			// load the web page
//			try {
			
			
			dbx = new DropBoxSimple();
			String url = dbx.getAuthorizationUrl();
		
			webEngine.load(url);
			
			webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
				
				@Override
				public void handle(WebEvent<String> event) {
					// TODO Auto-generated method stub
					if(dbx.startSession())
					{
						webEngine.getLoadWorker().stateProperty()
					    .addListener((obs, oldValue, newValue) -> {
					      if (newValue == Worker.State.SUCCEEDED) {
					    	  Document doc = webEngine.getDocument();

							  NodeList divs = doc.getElementsByTagName("div");
					
								for (int i =0; i<divs.getLength();i++)
									if(((Element)divs.item(i)).getAttribute("class")!=null)
										if(((Element)divs.item(i)).getAttribute("class").equals("email force-no-break"))
											userEmail=((Element)divs.item(i)).getTextContent();
								
								new MainFrameController(new MainFrameView());
								stage.hide();
								webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
									
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
			//System.out.println(url);
		/*	dbx = new DropBoxV1();
			String url = dbx.getAuthorizeationUrl();
		
				webEngine.load(url);
			webEngine.setOnVisibilityChanged(new EventHandler<WebEvent<Boolean>>() {
				
				@Override
				public void handle(WebEvent<Boolean> event) {
					// TODO Auto-generated method stub
					  try {
						  System.out.println("someshit");
							String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
							String accessToken=	dbx.createTokenFromCode(code);
							
							dbx.createClient(accessToken);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});*/
//				new File(".").getCanonicalPath()
//				+"/OnlineQuizChecker/1,sss/Quizzes/shit/StudentsAnswers/shit.html");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			// add the web view to the scene
			getChildren().add(browser);
		
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
    public static void main(String[] args) {
        launch(args);
    	
    }
    
	/*
	private JFXPanel fxPanel;
	private Thread thread;
	public static DropBoxSimple dbx;
	@SuppressWarnings("deprecation")
	public DropBoxAuthenticationView()
	{
		System.setProperty("java.net.useSystemProxies", "true");
		setLayout(null);
		setSize(600,600);
		setLocationRelativeTo(null);
		fxPanel = new JFXPanel();
		Platform.setImplicitExit(false);
		fxPanel.setBounds(0,0,600,600);
		fxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		add(fxPanel);
		
		thread = new Thread(this);

		
		Platform.runLater(thread);
	}
	@Override
	public void run() {
		initFX(fxPanel);
	}
	

	protected void initFX(JFXPanel fxPanel) {
		// TODO Auto-generated method stub
		final Scene scene = new Scene(new Browser(), 600,600, Color.web("#666970"));
		fxPanel.setScene(scene);
		
	}
	class Browser extends Region {

		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		public Browser() {
			// apply the styles
		//	getStyleClass().add("browser");
			// load the web page
//			try {
			dbx = new DropBoxSimple();
			String url = dbx.getAuthorizationUrl();
	
			System.out.println(url);
				webEngine.load(url);
//				new File(".").getCanonicalPath()
//				+"/OnlineQuizChecker/1,sss/Quizzes/shit/StudentsAnswers/shit.html");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			// add the web view to the scene
			getChildren().add(browser);
			dbx.startSession();
		
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
*/
}
