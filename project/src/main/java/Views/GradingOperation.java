package Views;

import java.awt.BorderLayout;
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

public class GradingOperation extends ViewPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WebEngine engine;

	private JFXPanel fxPanel;
	private StudentGradingPanel studentGradingPanel;
	private String studentQuizPath;
	private JMenu gradingMenu;
	private Container previousView;

	@SuppressWarnings("deprecation")
	public GradingOperation(StudentGradingPanel studentGradingPanel, String studentQuizPath, Container previousView) {
		setBackground(Color.white);
		this.studentGradingPanel = studentGradingPanel;
		this.studentQuizPath = studentQuizPath;
		this.previousView = previousView;
		createScene();
		setLayout(null);
		
		fxPanel = new JFXPanel();
		fxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanel panel = new JPanel();
		panel.setBounds(40, 30,
			920, 550);
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
	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (studentGradingPanel.getGradeBtn().getText().equals("Grade"))
				studentGradingPanel.getGradeBtn().setText("Edit");
			MainFrameController.view.changeContentPane((ViewPanel) previousView);

		}

	}

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
    


	public class Desktop {
		InputStream in;

		public void receiveInput(String score, String questionNumber) {
			// Platform.exit();
			// System.out.print(score+","+questionNumber);
		
			insertScoreToHTML(studentQuizPath, score, questionNumber);
		}

		private void insertScoreToHTML(String studentQuizPath, String score, String questionNumber) {

			try {
				// System.out.println("-"+questionNumber+"-");
				File studentQuizFile = new File(studentQuizPath);
				in = new FileInputStream(studentQuizFile);
				HtmlParser studentQuiz = new HtmlParser(in);
				if (!questionNumber.equals("fscore")) {
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
					// finalScore.setPrefix(score);
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
//						if (studentGradingPanel.getGradeBtn().getText().equals("Grade"))
//							studentGradingPanel.getGradeBtn().setText("Edit");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			//	studentQuiz.document.removeChild(studentQuiz.document.getElementById("scoreScript"));
				studentQuiz.writeHtml(studentQuizPath);
				engine.load("file:///" + studentQuizPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}