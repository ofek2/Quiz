package Controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Views.MainFrameView;
import Views.QuizCreationView;
import Views.qPanel;
import javafx.event.Event;
import javafx.fxml.Initializable;
import project.HtmlBuilder;

public class qPanelController{
	private qPanel view;
	private QuizCreationView parentView;
	public qPanelController(qPanel view,QuizCreationView parentView) {
		this.view = view;
		this.parentView=parentView;
		this.view.removeBtnAddListener(new removeBtnListener());
		this.view.qTypeCBaddItemListener(new qTypeItemListener());
	}
	
	class removeBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			System.out.println("click");
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);
			for (int i = view.getQuestionNumber()-1; i < QuizCreationController.qPanels.size(); i++) {
				QuizCreationController.qPanels.get(i).setQuestionNumber(i+1);
				QuizCreationController.qPanels.get(i).getQuestionLbl().setText("Question "+(i+1));
			}
			/*
			try {
				HtmlBuilder hb= new HtmlBuilder();
				hb.initiateHtml();
				hb.writeHtml("C:\\Users\\Ofek\\ProjectWorkspace\\project\\testFile.html");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		}
		
	}
	class qTypeItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getQuestionPanel().getLayout());
			    cl.show(view.getQuestionPanel(), (String)e.getItem());
			
		}
		
	}
}
