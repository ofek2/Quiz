package Controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.google.common.io.Files;

import Entities.QuizEntity;
import Views.MainFrameView;
import Views.QuizCreationView;
import Views.qPanel;
import javafx.event.Event;
import javafx.fxml.Initializable;
import project.HtmlBuilder;

public class qPanelController{
	private qPanel view;
	private QuizEntity quizEntity;
	private QuizCreationView parentView;
	private JFileChooser qFileChooser;
	private JFileChooser aFileChooser;
	private String quizPath;
	private File qImgFile;
	private File aImgFile;
	private String fileExtension;
	public qPanelController(qPanel view,QuizCreationView parentView,QuizEntity quizEntity) {
		this.view = view;
		this.parentView=parentView;
		this.quizEntity=quizEntity;
		this.view.removeBtnAddListener(new removeBtnListener());
		this.view.listenChkBoxAddListner(new listenChkBoxListener());
		this.view.qBrowseBtnAddListener(new qBrowseBtnListener());
		this.view.ansBrowseBtnAddListener(new ansBrowseBtnListener());
		this.view.aTypeCBaddItemListener(new aTypeItemListener());
		qFileChooser=view.getQuestionFileChooser();
		aFileChooser=view.getAnswerFileChooser();
		try {
			quizPath = quizEntity.getQuizFolder().getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qImgFile=null;
		aImgFile=null;
	}
	
	
	
	class removeBtnListener implements ActionListener
	{
		private BufferedImage bufferedImage;
		public void actionPerformed(ActionEvent e) {
			System.out.println("click");
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			if(qImgFile!=null)
				qImgFile.delete();
			if(aImgFile!=null)
				aImgFile.delete();
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);		
			for (int i = view.getQuestionNumber()-1; i < QuizCreationController.qPanels.size(); i++) {
				QuizCreationController.qPanels.get(i).view.setQuestionNumber(i+1);
				QuizCreationController.qPanels.get(i).view.getQuestionLbl().setText("Question"+(i+1));

				
				try {
					if(QuizCreationController.qPanels.get(i).qImgFile!=null){
					File tempFile = new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
							"Question"+(i+1)+".PNG");
					bufferedImage = ImageIO.read(QuizCreationController.qPanels.get(i).qImgFile);
					ImageIO.write(bufferedImage,QuizCreationController.qPanels.get(i).fileExtension , tempFile);
					QuizCreationController.qPanels.get(i).qImgFile.delete();
					QuizCreationController.qPanels.get(i).qImgFile=tempFile;
					}
					if(QuizCreationController.qPanels.get(i).aImgFile!=null){
					File tempFile = new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
							"Answer"+(i+1)+".PNG");
					bufferedImage = ImageIO.read(QuizCreationController.qPanels.get(i).aImgFile);
					ImageIO.write(bufferedImage,QuizCreationController.qPanels.get(i).fileExtension , tempFile);
					QuizCreationController.qPanels.get(i).aImgFile.delete();
					QuizCreationController.qPanels.get(i).aImgFile=tempFile;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
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
	class aTypeItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getAnswerPanel().getLayout());
			    cl.show(view.getAnswerPanel(), (String)e.getItem());
			
		}
		
	}
	class listenChkBoxListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			if(view.getListenChkBox().isSelected())
			{
				view.getChckbxHideQuestion().setVisible(true);
			}
			else
				view.getChckbxHideQuestion().setVisible(false);
			
		}
		
	}
	
	class qBrowseBtnListener implements ActionListener
	{
		private int returnVal;
		private File fileSave;
		private BufferedImage image;
		private String questionLbl;
		private String questionImgPath;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=qFileChooser.showSaveDialog(view.getQuestionDataPanel());
			if(returnVal==qFileChooser.APPROVE_OPTION)
			{
				qImgFile = qFileChooser.getSelectedFile();				
				try {
					questionLbl = view.getQuestionLbl().getText()+".PNG";
					questionImgPath = quizPath +"/" + questionLbl;
					fileExtension = Files.getFileExtension(qImgFile.getCanonicalPath());
					image = ImageIO.read(qImgFile); 	
					fileSave = new File(questionImgPath);
					ImageIO.write(image,fileExtension , fileSave);
					qImgFile = fileSave;
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		
	}
	class ansBrowseBtnListener implements ActionListener
	{
		private int returnVal;
		private File fileSave;
		private BufferedImage image;
		private String answerLbl;
		private String answerImgPath;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=aFileChooser.showSaveDialog(view.getAnswerPanel());
			if(returnVal==aFileChooser.APPROVE_OPTION)
			{
				aImgFile = aFileChooser.getSelectedFile();				
				try {
					answerLbl = "Answer"+view.getQuestionNumber()+".PNG";
					answerImgPath = quizPath +"/" + answerLbl;
					fileExtension = Files.getFileExtension(aImgFile.getCanonicalPath());
					image = ImageIO.read(aImgFile); 	
					fileSave = new File(answerImgPath);
					ImageIO.write(image,fileExtension , fileSave);
					aImgFile = fileSave;
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		
	}
	public qPanel getQuestionPanel() {
		return view;
	}
	
	}
	

