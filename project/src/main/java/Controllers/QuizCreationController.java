package Controllers;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Entities.Constants;
import Entities.QuizEntity;
import Entities.QuizObjectEntity;
import Views.HelpFrame;
import Views.InitialWindowView;
import Views.PreviewQuizFrame;
import Views.QuizCreationView;
import Views.qPanel;
import project.HtmlBuilder;
import project.ObjectFileManager;

/**
 * The Class QuizCreationController.
 * This class controls {@link QuizCreationView} events.
 */
public class QuizCreationController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The view. */
	private QuizCreationView view;

	/** The entity. */
	private QuizEntity entity;

	/** The q panel controller. */
	private qPanelController qPanelController;

	/** The q panels. */
	public static ArrayList<qPanelController> qPanels;

	/** The html builder. */
	private HtmlBuilder htmlBuilder;

	/** The save flag. */
	public static int saveFlag = 1;

	/** The initial window view. */
	private transient InitialWindowView initialWindowView;

	/** The window listener. */
	private windowListener windowListener;

	/** The questions to hide. */
	private ArrayList<Boolean> questionsToHide = new ArrayList<>();

	/** The Constant TOTAL_GRADE. */
	final static int TOTAL_GRADE = 100;

	/**
	 * Instantiates a new quiz creation controller.
	 *
	 * @param view
	 *            the view
	 * @param entity
	 *            the entity
	 * @param initialWindowView
	 *            the initial window view
	 */
	public QuizCreationController(QuizCreationView view, QuizEntity entity, InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = { new saveMenuListener(), new previewMenuListener(),
				new exitMenuListener() };
		this.view = view;
		this.entity = entity;
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		this.view.addHelpActionListener(new HelpListener());
		windowListener = new windowListener();
		MainFrameController.view.removeWindowListener(InitialWindowController.windowListener);
		MainFrameController.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MainFrameController.view.addWindowListener(windowListener);
		qPanels = new ArrayList<qPanelController>();
		try {
			htmlBuilder = new HtmlBuilder();
			htmlBuilder.initiateHtml();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addNewQpanel();
	}

	/**
	 * Instantiates a new quiz creation controller.
	 *
	 * @param view
	 *            the view
	 * @param objectEntity
	 *            the object entity
	 * @param initialWindowView
	 *            the initial window view
	 */
	public QuizCreationController(QuizCreationView view, QuizObjectEntity objectEntity,
			InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = { new saveMenuListener(), new previewMenuListener(),
				new exitMenuListener() };
		this.view = view;
		this.entity = objectEntity.getQuizEntity();
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		this.view.addHelpActionListener(new HelpListener());

		windowListener = new windowListener();
		MainFrameController.view.removeWindowListener(InitialWindowController.windowListener);
		MainFrameController.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MainFrameController.view.addWindowListener(windowListener);
		qPanels = new ArrayList<qPanelController>();
		qPanels = objectEntity.getqPanels();
		for (int i = 0; i < qPanels.size(); i++) {
			qPanels.get(i).initializeFileChoosers();
			try {
				qPanels.get(i).setQuizPath(objectEntity.getQuizEntity().getQuizFormFolder().getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			qPanels.get(i).settempaImgFile(qPanels.get(i).getaImgFile());
			qPanels.get(i).settempqImgFile(qPanels.get(i).getqImgFile());
			qPanels.get(i).setParentView(view);
			addExitingQpanel(qPanels.get(i).view);
		}
		try {
			htmlBuilder = new HtmlBuilder();
			htmlBuilder.initiateHtml();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds the exiting qpanel.
	 *
	 * @param panel
	 *            the panel
	 */
	public void addExitingQpanel(qPanel panel) {
		view.panel.add(panel);
		view.panel.remove(view.addBtn);

		view.panel.add(view.addBtn);
		view.panel.revalidate();
	}

	/**
	 * Adds the new qpanel.
	 */
	public void addNewQpanel() {
		qPanel qPview = new qPanel();
		qPanelController = new qPanelController(qPview, view, entity);
		view.panel.add(qPview);
		view.panel.revalidate();

		QuizCreationController.saveFlag = 0;
		view.panel.remove(view.addBtn);

		view.panel.add(view.addBtn);

		qPview.setQuestionNumber(qPanels.size() + 1);
		qPanels.add(qPanelController);
		QuizCreationController.qPanels.get(qPanels.size() - 1).getQuestionPanel().getQuestionLbl()
				.setText("Question" + (qPanels.size()));
		if ((qPview.getQuestionNumber() - 1) % 2 == 0)
			qPview.mainPanel.setBackground(SystemColor.textHighlight);
		else
			qPview.mainPanel.setBackground(new Color(204, 102, 51));
		;
		view.panel.revalidate();
	}

	/**
	 * The listener interface for receiving help events. The class that is
	 * interested in processing a help event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addHelpListener<code> method. When the help event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see HelpEvent
	 */
	class HelpListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new helpFrameController(new HelpFrame(Constants.HELP_NEWQUIZ,Constants.HELP_NEWQUIZ_AMOUNT));
		}

	}

	/**
	 * The listener interface for receiving addBtn events. The class that is
	 * interested in processing a addBtn event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addaddBtnListener<code> method. When the addBtn
	 * event occurs, that object's appropriate method is invoked.
	 *
	 * @see addBtnEvent
	 */
	class addBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			addNewQpanel();
		}

	}

	/**
	 * The listener interface for receiving addSpinnerChange events. The class
	 * that is interested in processing a addSpinnerChange event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addaddSpinnerChangeListener
	 * <code> method. When the addSpinnerChange event occurs, that object's
	 * appropriate method is invoked.
	 *
	 * @see addSpinnerChangeEvent
	 */
	class addSpinnerChangeListener implements ChangeListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
		 * ChangeEvent)
		 */
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag = 0;
		}

	}

	/**
	 * The listener interface for receiving saveMenu events. The class that is
	 * interested in processing a saveMenu event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addsaveMenuListener<code> method. When the saveMenu
	 * event occurs, that object's appropriate method is invoked.
	 *
	 * @see saveMenuEvent
	 */
	class saveMenuListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saveFlag = 1;
			int totalGrade = totalGrade();
			if (totalGrade == TOTAL_GRADE)
				saveQuiz();
			else
				JOptionPane.showMessageDialog(MainFrameController.view, "The total quiz score is " + totalGrade
						+ ", please adjust the questions scores to reach a score of " + TOTAL_GRADE);

		}

		/**
		 * Total grade.
		 *
		 * @return the int
		 */
		private int totalGrade() {
			int sum = 0;
			for (int i = 0; i < qPanels.size(); i++) {
				try {
					sum += Integer.valueOf(qPanels.get(i).view.getScoreTextField().getText());
				} catch (NumberFormatException e) {
					qPanels.get(i).view.getScoreTextField().setText("0");
				}
			}

			return sum;

		}

		/**
		 * Save quiz.
		 */
		public void saveQuiz() {
			if (!entity.getQuizFolder().exists()) {
				entity.getQuizFolder().mkdir();
				entity.getQuizFormFolder().mkdir();
				// entity.getStudentsAnswersFolder().mkdir();
			}
			for (int i = 0; i < qPanels.size(); i++)
				qPanels.get(i).saveImages();

			recursiveDelete(entity.getQuizFormFolder(),true);

			createHtmlFile("FINAL");

			try {
				htmlBuilder.writeHtml(
						entity.getQuizFormFolder().getCanonicalPath() + "/" + entity.getName() + "WithAnswers.html");
				htmlBuilder.removeLecturerAnswers(questionsToHide);
				htmlBuilder.writeHtml(entity.getQuizFormFolder().getCanonicalPath() + "/" + entity.getName() + ".html");
				initialWindowView.setTree(new JTree(InitialWindowView
						.filesTree(new File(new File(".").getCanonicalPath() + "/OnlineQuizChecker"))));
				QuizObjectEntity quizObjectEntity = new QuizObjectEntity(entity, qPanels);
				String path = entity.getQuizFormFolder().getCanonicalPath() + "/" + entity.getName() + ".ser";
				ObjectFileManager.saveObject(quizObjectEntity, path);
				JOptionPane.showMessageDialog(null, "All of the data saved successfully");

			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Creates the html file.
	 *
	 * @param state
	 *            the state
	 */
	private void createHtmlFile(String state) {

		try {
			htmlBuilder = new HtmlBuilder();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		htmlBuilder.initiateHtml();
		// -------add the title information to the html file
		// here---------///
		htmlBuilder.addTitleInfo(entity.getName());
		// --------------------------------------------------------------------

		for (int i = 0; i < qPanels.size(); i++) {
			qPanel tempQpanel = qPanels.get(i).view;
			qPanelController tempQController = qPanels.get(i);
			int index = tempQpanel.getAnswerTypeCb().getSelectedIndex();
			String answerType = tempQpanel.getAnswerTypeCb().getItemAt(index);
			ArrayList<String> choices;
			String score;
			// listening part
			boolean enableListening = tempQpanel.getListenChkBox().isSelected();
			boolean hideQuestion;
			if (enableListening)
				hideQuestion = tempQpanel.getChckbxHideQuestion().isSelected();
			else
				hideQuestion = false;
			questionsToHide.add(hideQuestion);
			if (tempQpanel.getScoreTextField().getText().isEmpty()) {
				score = "0";
				tempQpanel.getScoreTextField().setText(score);
			} else
				score = tempQpanel.getScoreTextField().getText();
			String questionImageName = "";
			htmlBuilder.addQuestion(i + 1, answerType, score);

			//
			if (state.equals("FINAL")) {
				if (tempQController.getqImgFile() != null) {
					if (!tempQController.getqImgFile().getName().endsWith("D.PNG"))
						questionImageName = tempQController.getqImgFile().getName();
				}
			} else {
				if (tempQController.gettempqImgFile() != null) {
					questionImageName = tempQController.gettempqImgFile().getName();
				}
			}
			htmlBuilder.addQuestionData(i + 1, tempQpanel.getTextAreaQ().getText(), questionImageName);
			String answer = "";
			if (answerType.equals("Multiple Choice")) {

				choices = new ArrayList<String>();

				for (int j = 0; j < tempQpanel.getMultipleChoicePanelController().cBfControllers.size(); j++) {
					if (tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view.getAnswerCheckBox()
							.isSelected())
						answer += (j + 1) + " ";
					choices.add(tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view
							.getAnswerTextOption().getText());
				}
				String type;
				answer = answer.trim();
				String[] splited = answer.split("\\s");
				if (splited.length > 1)
					type = "Multiple Choice";
				else
					type = "Single Choice";
				htmlBuilder.addAnswersData(i + 1, type, choices, enableListening);
				htmlBuilder.addLecturerAnswers(i + 1, type, choices, answer);
			} else {
				if (answerType.equals("Free Text"))
					answer = tempQController.view.getTextAreaA().getText();
				else {
					if (state.equals("FINAL")) {
						if (tempQController.getaImgFile() != null)
							if (!tempQController.getaImgFile().getName().endsWith("D.PNG"))
								answer = tempQController.getaImgFile().getName();
					} else {
						if (tempQController.gettempaImgFile() != null)
							answer = tempQController.gettempaImgFile().getName();
					}
				}
				htmlBuilder.addAnswersData(i + 1, answerType, enableListening);
				htmlBuilder.addLecturerAnswers(i + 1, answerType, answer);
			}

		}

	}

	/**
	 * The listener interface for receiving previewMenu events. The class that
	 * is interested in processing a previewMenu event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addpreviewMenuListener
	 * <code> method. When the previewMenu event occurs, that object's
	 * appropriate method is invoked.
	 *
	 * @see previewMenuEvent
	 */
	class previewMenuListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				File tempFolder = new File((new File(".")).getCanonicalPath() + "/Temp");
				tempFolder.mkdir();
				for (int i = 0; i < qPanels.size(); i++)
					qPanels.get(i).saveImagesForPreview(tempFolder.getCanonicalPath());

				createHtmlFile("TEMP");

				String path = tempFolder.getCanonicalPath() + "/Temp.html";
				htmlBuilder.writeHtml(path);

				PreviewQuizFrame frame = new PreviewQuizFrame(tempFolder.getCanonicalPath(),
						QuizCreationController.this);
				frame.setVisible(true);
				frame.loadURL("file:///" + path);

			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	/**
	 * Recursive delete.
	 *
	 * @param file
	 *            the file
	 */
	public void recursiveDelete(File file,boolean specific) {
		if (!file.exists())
			return;
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				recursiveDelete(f,specific);
			}
		}
		if(specific)
		{
			if (file.getName().contains("D.PNG") && !file.isDirectory())
				file.delete();
		}
		else
			file.delete();
	}

	/**
	 * The listener interface for receiving exitMenu events. The class that is
	 * interested in processing a exitMenu event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addexitMenuListener<code> method. When the exitMenu
	 * event occurs, that object's appropriate method is invoked.
	 *
	 * @see exitMenuEvent
	 */
	class exitMenuListener implements ActionListener {

		/** The exit flag. */
		private int exitFlag;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (saveFlag == 0) {
				exitFlag = JOptionPane.showConfirmDialog(null,
						"You made an unsaved changes, all of this changes will be lost,\n do you want to keep the application progress?",
						"Alert", JOptionPane.YES_NO_OPTION);
				if (exitFlag == JOptionPane.YES_OPTION) {
					if (entity.getQuizFormFolder().listFiles() != null)
						if (entity.getQuizFormFolder().listFiles().length > 0)
							for (File file : entity.getQuizFormFolder().listFiles()) {
								if (file.getName().endsWith(".PNG"))
									qPanelController.renameQandAImagesToOrigin(file);
							}
					MainFrameController.view.changeContentPane(initialWindowView);
					MainFrameController.view.removeWindowListener(windowListener);
					MainFrameController.view.addWindowListener(InitialWindowController.windowListener);
					
				}
			} else {

				MainFrameController.view.changeContentPane(initialWindowView);
				MainFrameController.view.removeWindowListener(windowListener);
				MainFrameController.view.addWindowListener(InitialWindowController.windowListener);
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
			
			}
			File tempPicturesFolder = new File(new File(".")+"/tempPicturesFolder");
			if(tempPicturesFolder.exists())
				recursiveDelete(tempPicturesFolder,false);
		}
	}

	/**
	 * The listener interface for receiving window events. The class that is
	 * interested in processing a window event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addwindowListener<code> method. When the window
	 * event occurs, that object's appropriate method is invoked.
	 *
	 * @see windowEvent
	 */
	class windowListener extends WindowAdapter implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The exit flag. */
		private int exitFlag;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.
		 * WindowEvent)
		 */
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			if (saveFlag == 0) {
				exitFlag = JOptionPane.showConfirmDialog(null,
						"You made an unsaved changes, all of this changes will be lost,\n do you want to keep the application progress?",
						"Alert", JOptionPane.YES_NO_OPTION);
				if (exitFlag == JOptionPane.YES_OPTION) {
					if (entity.getQuizFormFolder().listFiles() != null)
						if (entity.getQuizFormFolder().listFiles().length > 0)
							for (File file : entity.getQuizFormFolder().listFiles()) {
								if (file.getName().endsWith(".PNG"))
									qPanelController.renameQandAImagesToOrigin(file);
							}
					MainFrameController.view.changeContentPane(initialWindowView);
					MainFrameController.view.removeWindowListener(this);
					MainFrameController.view.addWindowListener(InitialWindowController.windowListener);
				}
			} else {

				MainFrameController.view.changeContentPane(initialWindowView);
				MainFrameController.view.removeWindowListener(this);
				MainFrameController.view.addWindowListener(InitialWindowController.windowListener);

			}
			File tempPicturesFolder = new File(new File(".")+"/tempPicturesFolder");
			if(tempPicturesFolder.exists())
				recursiveDelete(tempPicturesFolder,false);
		}
	}

}
