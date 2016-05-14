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
import javax.swing.SwingWorker;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import Controllers.MainFrameController;
import Controllers.QuizCreationController;
import Controllers.removeFromDropbox;
import Entities.Constants;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PreviewQuizFrame extends JFrame{
	private final JFXPanel jfxPanel = new JFXPanel();
	private WebEngine engine;
	private final JPanel panel = new JPanel(new BorderLayout());
	private windowListener windowListener;
	private String tempFolderToDelete;
	private QuizCreationController controller;
	public PreviewQuizFrame(String tempFolderToDelete,QuizCreationController controller){
		super();
		this.controller = controller;
		this.tempFolderToDelete = tempFolderToDelete;
		windowListener = new windowListener();
		removeWindowListener(MainFrameController.view.windowListener);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(windowListener);
		initComponents();
	}
	private void initComponents() {
		createScene();
		panel.add(jfxPanel, BorderLayout.CENTER);
		getContentPane().add(panel);

		setPreferredSize(new Dimension(800, 800));

		
		pack();
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
	class windowListener extends WindowAdapter implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			File tmp = new File(tempFolderToDelete);
			for(int i = 0;i<controller.qPanels.size();i++)
			{
				controller.qPanels.get(i).settempaImgFile(null);
				controller.qPanels.get(i).settempqImgFile(null);
			}
			recursiveDelete(tmp);
			dispose();
		}
	}
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
