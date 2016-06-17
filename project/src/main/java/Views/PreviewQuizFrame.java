package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Controllers.QuizCreationController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * The Class PreviewQuizFrame.
 * This class is used for previewing a quiz in the creation process.
 */
public class PreviewQuizFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The jfx panel. */
	private  JFXPanel jfxPanel;
	
	/** The engine. */
	private WebEngine engine;
	
	/** The panel. */
	private final JPanel panel = new JPanel(new BorderLayout());
	
	/** The window listener. */
	private windowListener windowListener;
	
	/** The temp folder to delete. */
	private String tempFolderToDelete;
	
	/**
	 * Instantiates a new preview quiz frame.
	 *
	 * @param tempFolderToDelete the temp folder to delete
	 * @param controller the controller
	 */
	public PreviewQuizFrame(String tempFolderToDelete,QuizCreationController controller){
		super();
		jfxPanel = new JFXPanel();
		this.tempFolderToDelete = tempFolderToDelete;
		windowListener = new windowListener();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(windowListener);
		initComponents();
	}
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		createScene();
		panel.add(jfxPanel, BorderLayout.CENTER);
		getContentPane().add(panel);

		setPreferredSize(new Dimension(800, 800));

		
		pack();
		setLocationRelativeTo(null);
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
	 * The listener interface for receiving window events.
	 * The class that is interested in processing a window
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addwindowListener<code> method. When
	 * the window event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see windowEvent
	 */
	class windowListener extends WindowAdapter implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			File tmp = new File(tempFolderToDelete);
			for(int i = 0;i<QuizCreationController.qPanels.size();i++)
			{
				QuizCreationController.qPanels.get(i).settempaImgFile(QuizCreationController.qPanels.get(i).getaImgFile());
				QuizCreationController.qPanels.get(i).settempqImgFile(QuizCreationController.qPanels.get(i).getqImgFile());
			}
			recursiveDelete(tmp);
			dispose();
		}
	}
	
	/**
	 * Recursive delete.
	 *
	 * @param file the file
	 */
	private void recursiveDelete(File file) {
		if (!file.exists())
			return;
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				recursiveDelete(f);
			}
		}
		file.delete();
	}
}
