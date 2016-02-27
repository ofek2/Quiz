package Controllers;

import java.awt.Color;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import Entities.QuizEntity;
import Entities.QuizObjectEntity;
import Views.InitialWindowView;
import Views.QuizCreationView;
import Views.qPanel;
import project.HtmlBuilder;
import project.ObjectFileManager;

public class QuizCreationController implements Serializable {
	private QuizCreationView view;
	private QuizEntity entity;
	private qPanelController qPanelController;
	protected static ArrayList<qPanelController> qPanels;
	private HtmlBuilder htmlBuilder;
	public static int saveFlag=1;
	private transient InitialWindowView initialWindowView;
	private windowListener windowListener;
	
	public QuizCreationController(QuizCreationView view,QuizEntity entity, InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = {new saveMenuListener(),new exitMenuListener()};
		this.view = view;
		this.entity = entity;
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		this.view.addSpinnerChangeListener(new addSpinnerChangeListener());
		windowListener = new windowListener();
		MainFrameController.view.removeWindowListener(MainFrameController.view.windowListener);
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
	public QuizCreationController(QuizCreationView view,QuizObjectEntity objectEntity, InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = {new saveMenuListener(),new exitMenuListener()};
		this.view = view;
		this.entity = objectEntity.getQuizEntity();
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addSpinnerChangeListener(new addSpinnerChangeListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		windowListener = new windowListener();
		MainFrameController.view.removeWindowListener(MainFrameController.view.windowListener);
		MainFrameController.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MainFrameController.view.addWindowListener(windowListener);
		qPanels = new ArrayList<qPanelController>();
		qPanels = objectEntity.getqPanels();
		for(int i =0;i<qPanels.size();i++)
		{
			qPanels.get(i).intializefilechoosers();
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

	public void addExitingQpanel(qPanel panel)
	{
		view.panel.add(panel);
		view.panel.remove(view.addBtn);
		view.panel.add(view.addBtn);
		view.panel.revalidate();
	}
	public void addNewQpanel()
	{
		qPanel qPview = new qPanel();
		qPanelController = new qPanelController(qPview,view,entity);
		view.panel.add(qPview);
		view.panel.revalidate();
		
		QuizCreationController.saveFlag=0;
		view.panel.remove(view.addBtn);
		view.panel.add(view.addBtn);
		
		qPview.setQuestionNumber(qPanels.size()+1);
		qPanels.add(qPanelController);
		QuizCreationController.qPanels.get(qPanels.size()-1).getQuestionPanel().getQuestionLbl().setText("Question"+(qPanels.size()));
		if((qPview.getQuestionNumber()-1)%2 == 0)
			qPview.setBackground(Color.getHSBColor(0.55f, 0.69f, 1));
		else
			qPview.setBackground(Color.getHSBColor(0.0711f, 0.9916f, 1));
		view.panel.revalidate();
	}
	class addBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			addNewQpanel();
		}
		
	}
	class addSpinnerChangeListener implements ChangeListener
	{

		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag=0;
		}

		
	}
	class saveMenuListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saveFlag=1;
			saveQuiz();
		
		
	}
	public void saveQuiz()
	{
		if(!entity.getQuizFolder().exists())
		{
			entity.getQuizFolder().mkdir();
			entity.getQuizFormFolder().mkdir();
			entity.getStudentsAnswersFolder().mkdir();
		}
		for(int i=0;i<qPanels.size();i++)
			qPanels.get(i).saveImages();
		recursiveDelete(entity.getQuizFormFolder());

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
		// -------add the title information to the html file here---------///
		
		//--------------------------------------------------------------------
		
		for (int i=0;i<qPanels.size();i++)
		{
			qPanel tempQpanel= qPanels.get(i).view;
			qPanelController tempQController = qPanels.get(i);
			int index =tempQpanel.getAnswerTypeCb().getSelectedIndex();
			String answerType = tempQpanel.getAnswerTypeCb().getItemAt(index); 
			ArrayList<String> choices;
			String score;
			
			if(tempQpanel.getScoreTextField().getText().isEmpty())
			{
				score = "0";
				tempQpanel.getScoreTextField().setText(score);
			}
			else 
				score = tempQpanel.getScoreTextField().getText();
			String questionImageName = "";
			htmlBuilder.addQuestion(i+1,answerType ,score);
			
			if(tempQController.getqImgFile()!=null)
			{
				questionImageName= tempQController.getqImgFile().getName();

			}
			htmlBuilder.addQuestionData(i+1, tempQpanel.getTextAreaQ().getText(), questionImageName);
			String answer="";
			if(answerType.equals("Multiple Choice"))
			{

				
				choices = new ArrayList<String>();
				
				for (int j=0;j<tempQpanel.getMultipleChoicePanelController().cBfControllers.size();j++)
				{
					if(tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view.getAnswerCheckBox().isSelected())
						answer+=(j+1)+" ";
					choices.add(tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view.getAnswerTextOption().getText());
				}
				String type;
				String[] splited = answer.split("\\s");
				if(splited.length>1)type="Multiple Choice";
				else type="Singel Choice";
				htmlBuilder.addAnswersData(i+1,type,choices);
				htmlBuilder.addLecturerAnswers(i+1,type, choices, answer);
			}
			else
			{
				if(answerType.equals("Free Text"))
					answer = tempQController.view.getTextAreaA().getText();
				else
				{
					if(tempQController.getaImgFile()!=null)
					answer = tempQController.getaImgFile().getPath();
				}
				htmlBuilder.addAnswersData(i+1, answerType);
				htmlBuilder.addLecturerAnswers(i+1,answerType,answer);
			}
		
		}
		
		try {
			htmlBuilder.writeHtml(entity.getQuizFormFolder().getCanonicalPath()+"/"+entity.getName());
			initialWindowView.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
			QuizObjectEntity quizObjectEntity = new QuizObjectEntity(entity, qPanels);
			String path = entity.getQuizFormFolder().getCanonicalPath()+"/"+entity.getName()+".ser";
			ObjectFileManager fileManager = new ObjectFileManager();
			fileManager.saveObject(quizObjectEntity, path);
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
	private void recursiveDelete(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        if((file.getName().equals("Question1D.PNG")||file.getName().equals("Answer1D.PNG"))
        		&&!file.isDirectory())
        file.delete();
    }
	

	class exitMenuListener implements ActionListener
	{
		private int exitFlag;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(saveFlag==0)
			{
			exitFlag=JOptionPane.showConfirmDialog(null,"You made an unsaved changes, all of this changes will be lost,\n do you want to keep the application progress?","Alert",JOptionPane.YES_NO_OPTION);
			if(exitFlag==JOptionPane.YES_OPTION)
				{
//			
				for(File file: entity.getQuizFormFolder().listFiles())
				{
					if(file.getName().endsWith(".PNG"))
						qPanelController.renameQandAImagesToOrigin(file);
				}
				MainFrameController.view.changeContentPane(initialWindowView);
				MainFrameController.view.removeWindowListener(windowListener);
				MainFrameController.view.addWindowListener(MainFrameController.view.windowListener);
				}
			}
			else
			{	

					MainFrameController.view.changeContentPane(initialWindowView);
					MainFrameController.view.removeWindowListener(windowListener);
					MainFrameController.view.addWindowListener(MainFrameController.view.windowListener);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

			}
		}
	}
	class windowListener extends WindowAdapter implements Serializable
	{
		private int exitFlag;
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			if(saveFlag==0)
			{
			exitFlag=JOptionPane.showConfirmDialog(null,"You made an unsaved changes, all of this changes will be lost,\n do you want to keep the application progress?","Alert",JOptionPane.YES_NO_OPTION);
			if(exitFlag==JOptionPane.YES_OPTION)
				{
//				if(saveComplete==0)
//				{
//					entity.getQuizFormFolder().delete();
//					try {
//						initialWindowView.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
				/////delete the d from the image name
//				for(int i=0;i<qPanels.size();i++)/////////////////////////////
				for(File file: entity.getQuizFormFolder().listFiles())
				{
					if(file.getName().endsWith(".PNG"))
						qPanelController.renameQandAImagesToOrigin(file);
				}
				MainFrameController.view.changeContentPane(initialWindowView);	
				MainFrameController.view.removeWindowListener(this);
				MainFrameController.view.addWindowListener(MainFrameController.view.windowListener);
				}
			}
			else
			{	
//				try {
//					if(saveComplete==0)
//					{
//						entity.getQuizFormFolder().delete();
//						initialWindowView.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
//					}
					MainFrameController.view.changeContentPane(initialWindowView);
					MainFrameController.view.removeWindowListener(this);
					MainFrameController.view.addWindowListener(MainFrameController.view.windowListener);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

			}
		}
	}

}
