package Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Controllers.MainFrameController;
import Entities.StudentEntity;
import netscape.javascript.JSObject;
import project.HtmlParser;
import project.ObjectFileManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Font;

/**
 * The Class GradingOperation.
 * This class controls the grading operation of a specific 
 */
public class GradingOperation extends ViewPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The engine. */
	private WebEngine engine;

	/** The fx panel. */
	private JFXPanel fxPanel;
	
	/** The student grading panel. */
	private StudentGradingPanel studentGradingPanel;
	
	/** The student quiz path. */
	private String studentQuizPath;
	
	/** The grading menu. */
	private JMenu gradingMenu;
	
	/** The previous view. */
	private Container previousView;
	
	/** The in. */
	private InputStream in;
	
	/** The student quiz. */
	private HtmlParser studentQuiz;
	
	/** The student quiz file. */
	private File studentQuizFile;
	/**
	 * Instantiates a new grading operation.
	 *
	 * @param studentGradingPanel the student grading panel
	 * @param studentQuizPath the student quiz path
	 * @param previousView the previous view
	 */
	public GradingOperation(StudentGradingPanel studentGradingPanel, String studentQuizPath, Container previousView) {
		studentQuizFile = new File(studentQuizPath);
		try {
			in = new FileInputStream(studentQuizFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		studentQuiz = new HtmlParser(in);
		
		setBackground(Color.white);
		this.studentGradingPanel = studentGradingPanel;
		this.studentQuizPath = studentQuizPath;
		this.previousView = previousView;
		createScene();
		setLayout(null);
		
		fxPanel = new JFXPanel();
		fxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanel panel = new JPanel();
		panel.setBounds(250, 30,
			500, 600);
		panel.setBackground(Color.white);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
		JLabel studentIdLbl = new JLabel("Currently grading student ID: "+this.studentGradingPanel.getLblStudentid().getText());
		studentIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		
		panel.add(verticalStrut);
		
		panel.add(studentIdLbl);
		
		panel.add(verticalStrut);
		
		panel.add(fxPanel);
		add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(java.awt.Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);

		gradingMenu = new JMenu("File");
		menuBar.add(gradingMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		gradingMenu.add(mntmExit);
		mntmExit.addActionListener(new ExitListener());

	}
	
	/**
	 * The listener interface for receiving exit events.
	 * The class that is interested in processing a exit
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When
	 * the exit event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitEvent
	 */
	class ExitListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (studentGradingPanel.getGradeBtn().getText().equals("Grade"))
				studentGradingPanel.getGradeBtn().setText("Edit");
	
			MainFrameController.view.changeContentPane((ViewPanel) previousView);

		}

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

				

				engine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
					if (newValue == Worker.State.SUCCEEDED) {

						JSObject jsobj = (JSObject) engine.executeScript("window");
						jsobj.setMember("Desktop", new Desktop());
					}
				});

				JSObject jsobj = (JSObject) engine.executeScript("window");
				jsobj.setMember("Desktop", new Desktop());
				fxPanel.setScene(new Scene(view));
			
				engine.load("file:///" + studentQuizPath);
				
			}
		});
	}
    


	/**
	 * The Class Desktop.
	 */
	public class Desktop {
		
	

		/**
		 * Receive input.
		 *
		 * @param score the score
		 * @param questionNumber the question number
		 */
		public void receiveInput(String score, String questionNumber) {
			
			insertScoreToHTML(studentQuizPath, score, questionNumber);
		
			studentGradingPanel.getLblGrade().setText(score);
		}
		
		/**
		 * Insert score to html.
		 *
		 * @param studentQuizPath the student quiz path
		 * @param score the score
		 * @param questionNumber the question number
		 */
		private void insertScoreToHTML(String studentQuizPath, String score, String questionNumber) {

			try {
				
				if (!questionNumber.equals("0")) {
					NodeList questionElement = studentQuiz.document.getElementsByTagName("Q" + questionNumber);
					Element question = (Element) questionElement.item(0);
					NodeList inputs = question.getElementsByTagName("input");
					for (int i = 0; i < inputs.getLength(); i++) {
						Element input = (Element) inputs.item(i);
						if (input.getAttribute("id").equals("score")) {
							input.setAttribute("value", score);
						}
					}
				} else {
					Element finalScore = (Element) studentQuiz.document.getElementsByTagName("u").item(0);
					finalScore.removeChild(finalScore.getFirstChild());
					finalScore.appendChild(studentQuiz.document.createTextNode(score));
					try {
						String quizName;
						String studentId;
						String studentFilePath;
						studentId = (String) studentQuizFile.getName().subSequence(0,
								studentQuizFile.getName().length() - 5);
						studentFilePath = studentQuizFile.getParentFile().getParentFile().getParentFile()
								.getParentFile().getParentFile().getCanonicalPath() + "/Students/" + studentId + ".ser";
						quizName = studentQuizFile.getParentFile().getParentFile().getParentFile().getName();
						StudentEntity result = (StudentEntity) ObjectFileManager.loadObject(studentFilePath);

						result.setQuizScore(quizName, score);
						FileOutputStream fos = new FileOutputStream(studentFilePath);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(result);
						oos.close();
						studentGradingPanel.getLblGrade().setText(score);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				studentQuiz.writeHtml(studentQuizPath);
			} catch (TransformerException e) {
				e.printStackTrace();
			}

		}

	}

}