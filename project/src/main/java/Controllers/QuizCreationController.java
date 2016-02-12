package Controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Entities.QuizEntity;
import Views.MainFrameView;
import Views.QuizCreationView;
import Views.qPanel;
import project.HtmlBuilder;

public class QuizCreationController {
	private QuizCreationView view;
	private QuizEntity entity;
	private qPanelController qPanelController;
	protected static ArrayList<qPanelController> qPanels;
	private HtmlBuilder htmlBuilder;
	public QuizCreationController(QuizCreationView view,QuizEntity entity) {
		ActionListener[] fileMenuListeners = {new saveMenuListener(),new exitMenuListener()};
		this.view = view;
		this.entity = entity;
		this.view.addBtnAddListener(new addBtnListener());
		this.view.addFileMenuListeners(fileMenuListeners);
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
				if(tempQController.getaImgFile()!=null)
					questionImagePath= tempQController.getaImgFile().getPath();
				System.out.println("1");
				htmlBuilder.addQuestionData(i+1, tempQpanel.getTextAreaQ().getText(), questionImagePath);
				System.out.println("2");
				if(answerType.equals("Multiple Choice"))
				{
					choices = new ArrayList<String>();
					for (int j=0;j<tempQpanel.getMultipleChoicePanelController().cBfControllers.size();i++)
						choices.add(tempQpanel.getMultipleChoicePanelController().cBfControllers.get(j).view.getTextField().getText());
					htmlBuilder.addAnswersData(i+1, choices);
				}
				else
				{
					htmlBuilder.addAnswersData(i+1, answerType);
				}
				System.out.println("3");
				//------- add the Lecturer correct answer here---------
				
				//--------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!---------
			}
			
			try {
				htmlBuilder.writeHtml(entity.getQuizFolder().getCanonicalPath()+"/"+entity.getName());
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	class exitMenuListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
