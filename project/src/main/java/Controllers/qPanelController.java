package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Views.QuizCreationView;
import Views.qPanel;
import javafx.event.Event;
import javafx.fxml.Initializable;
import project.HtmlBuilder;

public class qPanelController{
	private qPanel view;
	public qPanelController(qPanel view) {
		this.view = view;
		this.view.testBtnAddListener(new testBtnListener());
	}
	
	class testBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			System.out.println("click");
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
			}
		}
		
	}
	
}
