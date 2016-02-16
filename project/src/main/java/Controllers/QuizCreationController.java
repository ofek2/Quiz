package Controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Entities.QuizEntity;
import Entities.QuizObjectEntity;
import Views.InitialWindowView;
import Views.MainFrameView;
import Views.QuizCreationView;
import Views.qPanel;
import project.HtmlBuilder;

public class QuizCreationController implements Serializable {
	private transient QuizCreationView view;
	private QuizEntity entity;
	private qPanelController qPanelController;
	protected static ArrayList<qPanelController> qPanels;
	private HtmlBuilder htmlBuilder;
	protected static int saveFlag=1;
	private transient InitialWindowView initialWindowView;
	public QuizCreationController(QuizCreationView view,QuizEntity entity, InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = {new saveMenuListener(),new exitMenuListener()};
		this.view = view;
		this.entity = entity;
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		MainFrameController.view.addWindowListener(new windowListener());
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
		
		addQpanel();
	}
	public QuizCreationController(QuizCreationView view,QuizObjectEntity objectEntity, InitialWindowView initialWindowView) {
		ActionListener[] fileMenuListeners = {new saveMenuListener(),new exitMenuListener()};
		this.view = view;
		this.entity = objectEntity.getQuizEntity();
		this.initialWindowView = initialWindowView;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
		MainFrameController.view.addWindowListener(new windowListener());
		qPanels = objectEntity.getqPanels();
		
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
	public void addQpanel()
	{
		qPanel qPview = new qPanel();
		qPanelController = new qPanelController(qPview,view,entity);
		view.panel.add(qPview);
		view.panel.revalidate();
		
		
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
			addQpanel();
		}
		
	}
	class saveMenuListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saveFlag=1;
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
				htmlBuilder.addQuestion(i+1,answerType , Integer.parseInt(tempQpanel.getScoreTextField().getText()));
				String questionImagePath = "";
				if(tempQController.getqImgFile()!=null)
					questionImagePath= tempQController.getqImgFile().getPath();
				System.out.println("1");
				htmlBuilder.addQuestionData(i+1, tempQpanel.getTextAreaQ().getText(), questionImagePath);
				System.out.println("2");
				if(answerType.equals("Multiple Choice"))
				{

					System.out.println("3");
					choices = new ArrayList<String>();
					System.out.println(tempQpanel.getMultipleChoicePanelController().cBfControllers.size());
					for (int j=0;j<tempQpanel.getMultipleChoicePanelController().cBfControllers.size();j++)
					{
						
						choices.add(tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view.getTextField().getText());
					}
					htmlBuilder.addAnswersData(i+1, choices);
				}
				else
				{
					htmlBuilder.addAnswersData(i+1, answerType);
				}
				//------- add the Lecturer correct answer here---------
				
				//--------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!---------
			}
			
			try {
				htmlBuilder.writeHtml(entity.getQuizFolder().getCanonicalPath()+"/"+entity.getName());
				initialWindowView.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
			QuizObjectEntity quizObjectEntity = new QuizObjectEntity(entity, qPanels);
			FileOutputStream fos = new FileOutputStream(entity.getQuizFolder().getCanonicalPath()+"/"+entity.getName()+".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(quizObjectEntity);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		
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
				MainFrameController.view.changeContentPane(initialWindowView);	
			}
			else
			{	
				try {
					initialWindowView.setTree(new JTree(InitialWindowView.filesTree(new File(new File(".").getCanonicalPath()+"/OnlineQuizChecker"))));
					MainFrameController.view.changeContentPane(initialWindowView);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
	class windowListener implements WindowListener
	{

	
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}

}
