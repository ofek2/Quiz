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
	private JFileChooser fileChooser;
	private String quizPath;
	private File imgFile;
	private String fileExtension;
	
	public qPanelController(qPanel view,QuizCreationView parentView,QuizEntity quizEntity) {
		this.view = view;
		this.parentView=parentView;
		this.quizEntity=quizEntity;
		this.view.removeBtnAddListener(new removeBtnListener());
		this.view.listenChkBoxAddListner(new listenChkBoxListener());
		this.view.browseBtnAddListener(new browseBtnListener());
		fileChooser=view.getFileChooser();
		try {
			quizPath = quizEntity.getQuizFile().getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgFile=null;
	}
	
	
	
	class removeBtnListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			System.out.println("click");
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			if(imgFile!=null)
				imgFile.delete();
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);		
			for (int i = view.getQuestionNumber()-1; i < QuizCreationController.qPanels.size(); i++) {
				QuizCreationController.qPanels.get(i).view.setQuestionNumber(i+1);
				QuizCreationController.qPanels.get(i).view.getQuestionLbl().setText("Question"+(i+1));
//				if(QuizCreationController.qPanels.get(i).imgFile!=null)
//					try {
//						if(QuizCreationController.qPanels.get(i).imgFile!=null)
//						Files.move(imgFile, new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
//									"Question"+(i+1)+".PNG"));
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				if(QuizCreationController.qPanels.get(i).imgFile!=null){
				try {
					File tempFile = new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
							"Question"+(i+1)+".PNG");
					final BufferedImage bufferedImage = ImageIO.read(QuizCreationController.qPanels.get(i).imgFile);
					ImageIO.write(bufferedImage,fileExtension , tempFile);
					QuizCreationController.qPanels.get(i).imgFile.delete();
					QuizCreationController.qPanels.get(i).imgFile=tempFile;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				}
//				if(QuizCreationController.qPanels.get(i).imgFile!=null)
//						QuizCreationController.qPanels.get(i).imgFile.renameTo(
//						new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
//								"Question"+(i+1)+".PNG"));
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
	/*class aTypeItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getQuestionPanel().getLayout());
			    cl.show(view.getQuestionPanel(), (String)e.getItem());
			
		}
		
	}*/
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
	
	class browseBtnListener implements ActionListener
	{
		private int returnVal;
		private File fileSave;
		private BufferedImage bufferedImage;
		private String questionLbl;
		private String questionImgPath;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=fileChooser.showSaveDialog(view.getQuestionDataPanel());
			if(returnVal==fileChooser.APPROVE_OPTION)
			{
				imgFile = fileChooser.getSelectedFile();				
				try {
					questionLbl = view.getQuestionLbl().getText()+".PNG";
					questionImgPath = quizPath +"/" + questionLbl;
					fileExtension = Files.getFileExtension(imgFile.getCanonicalPath());
					bufferedImage = ImageIO.read(imgFile); 	
					fileSave = new File(questionImgPath);
					ImageIO.write(bufferedImage,fileExtension , fileSave);
					imgFile = fileSave;
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
	

