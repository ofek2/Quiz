package Controllers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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

public class qPanelController implements Serializable{
	public qPanel view;

	private QuizEntity quizEntity;
	private transient QuizCreationView parentView;
	private transient JFileChooser qFileChooser;
	private transient JFileChooser aFileChooser;
	private transient String quizPath;
	private transient File qImgFile;
	private transient File aImgFile;
	private transient String fileExtension;
	private transient JLabel qImageIcon=null;
	private transient Graphics graphics;
	public qPanelController(qPanel view,QuizCreationView parentView,QuizEntity quizEntity) {
		this.view = view;
		this.parentView=parentView;
		this.quizEntity=quizEntity;
		this.view.removeBtnAddListener(new removeBtnListener());
		this.view.listenChkBoxAddListner(new listenChkBoxListener());
		this.view.qBrowseBtnAddListener(new qBrowseBtnListener());
		this.view.ansBrowseBtnAddListener(new ansBrowseBtnListener());
		this.view.aTypeCBaddItemListener(new aTypeItemListener());
		this.view.qImageBtnAddListener(new qImageBtnListener());
		this.view.viewAnswerImageBtnAddListener(new viewAnswerImageBtnAddListener());
		this.view.removeQuestionImageBtnAddListener(new removeQuestionImageBtnListener());
		this.view.removeAnswerImageBtnAddListener(new removeAnswerImageBtnListener());
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
			QuizCreationController.saveFlag=0;
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			if(qImgFile!=null)
				qImgFile.delete();
			if(aImgFile!=null)
				aImgFile.delete();
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);		
			fixColors();
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
			private void fixColors() {
				for(int i=0;i<QuizCreationController.qPanels.size();i++)
				{
					if(i%2 == 0)
						QuizCreationController.qPanels.get(i).view.setBackground(Color.getHSBColor(0.55f, 0.69f, 1));
					else
						QuizCreationController.qPanels.get(i).view.setBackground(Color.getHSBColor(0.0711f, 0.9916f, 1));
				}
				
			}
			
		
	}
	class aTypeItemListener implements ItemListener
	{

		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getAnswerPanel().getLayout());
			    cl.show(view.getAnswerPanel(), (String)e.getItem());
			
			    if((!((String)e.getItem()).equals("Free Draw"))&&aImgFile!=null)
			    	removeAnswerImage();
			    QuizCreationController.saveFlag=0;
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
			
			QuizCreationController.saveFlag=0;
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
					view.getqImage().setVisible(true);
					view.getRemoveQuestionImageBtn().setVisible(true);
					view.getQuestionDataPanel().revalidate();
					QuizCreationController.saveFlag=0;
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
					view.getRemoveAnswerImageBtn().setVisible(true);
					view.getbtnViewAnswerImage().setVisible(true);
					view.getQuestionDataPanel().revalidate();
					QuizCreationController.saveFlag=0;
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		
	}
	class qImageBtnListener implements ActionListener
	{
		private BufferedImage image;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				try {
					image = ImageIO.read(qImgFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JFrame pictureViewFrame = new JFrame();
				JScrollPane jsp=new JScrollPane(new JLabel(new ImageIcon(image)),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pictureViewFrame.getContentPane().setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
				pictureViewFrame.setLayout(new BorderLayout());
				pictureViewFrame.pack();
				pictureViewFrame.setLocationRelativeTo(null);
				pictureViewFrame.setVisible(true);
				pictureViewFrame.getContentPane().add(BorderLayout.CENTER,jsp);
		}
		
	}
	
	class viewAnswerImageBtnAddListener implements ActionListener
	{
		private BufferedImage image;
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				try {
					image = ImageIO.read(aImgFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JFrame pictureViewFrame = new JFrame();
				JScrollPane jsp=new JScrollPane(new JLabel(new ImageIcon(image)),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pictureViewFrame.getContentPane().setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
				pictureViewFrame.setLayout(new BorderLayout());
				pictureViewFrame.pack();
				pictureViewFrame.setLocationRelativeTo(null);
				pictureViewFrame.setVisible(true);
				pictureViewFrame.getContentPane().add(BorderLayout.CENTER,jsp);
		}
		
	}
	
	
	class removeQuestionImageBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			qImgFile.delete();	
			view.getRemoveQuestionImageBtn().setVisible(false);
			view.getqImage().setVisible(false);
			view.getQuestionDataPanel().revalidate();
			QuizCreationController.saveFlag=0;
		}
	}
	
	class removeAnswerImageBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			removeAnswerImage();
			QuizCreationController.saveFlag=0;
		}
	}
	public void removeAnswerImage()
	{
		aImgFile.delete();	
		view.getRemoveAnswerImageBtn().setVisible(false);
		view.getbtnViewAnswerImage().setVisible(false);
		view.getQuestionDataPanel().revalidate();
	}
	
	public qPanel getQuestionPanel() {
		return view;
	}
	public QuizEntity getQuizEntity() {
		return quizEntity;
	}
	public void setQuizEntity(QuizEntity quizEntity) {
		this.quizEntity = quizEntity;
	}
	public QuizCreationView getParentView() {
		return parentView;
	}
	public void setParentView(QuizCreationView parentView) {
		this.parentView = parentView;
	}
	public JFileChooser getqFileChooser() {
		return qFileChooser;
	}
	public void setqFileChooser(JFileChooser qFileChooser) {
		this.qFileChooser = qFileChooser;
	}
	public JFileChooser getaFileChooser() {
		return aFileChooser;
	}
	public void setaFileChooser(JFileChooser aFileChooser) {
		this.aFileChooser = aFileChooser;
	}
	public String getQuizPath() {
		return quizPath;
	}
	public void setQuizPath(String quizPath) {
		this.quizPath = quizPath;
	}
	public File getqImgFile() {
		return qImgFile;
	}
	public void setqImgFile(File qImgFile) {
		this.qImgFile = qImgFile;
	}
	public File getaImgFile() {
		return aImgFile;
	}
	public void setaImgFile(File aImgFile) {
		this.aImgFile = aImgFile;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public JLabel getqImageIcon() {
		return qImageIcon;
	}
	public void setqImageIcon(JLabel qImageIcon) {
		this.qImageIcon = qImageIcon;
	}
	public Graphics getGraphics() {
		return graphics;
	}
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}
	}
	

