package Controllers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.google.common.io.Files;
import Entities.QuizEntity;
import Views.QuizCreationView;
import Views.qPanel;

/**
 * The Class qPanelController.
 * This class controls a qPanel events.
 */
public class qPanelController implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The view. */
	public qPanel view;

	/** The quiz entity. */
	private QuizEntity quizEntity;
	
	/** The parent view. */
	private QuizCreationView parentView;
	
	/** The question file chooser. */
	private transient JFileChooser qFileChooser;
	
	/** The answer file chooser. */
	private transient JFileChooser aFileChooser;
	
	/** The quiz path. */
	private static String quizPath;
	
	/** The question img file. */
	private File qImgFile;
	
	/** The answer img file. */
	private File aImgFile;
	
	/** The file extension. */
	private static String fileExtension;
	
	/** The question image icon. */
	private JLabel qImageIcon=null;
	
	/** The question img path. */
	private String questionImgPath;
	
	/** The answer img path. */
	private String answerImgPath;
	
	/** The text item listener. */
	public static textItemListener textItemListener;
	
	/**
	 * Instantiates a new q panel controller.
	 *
	 * @param view the view
	 * @param parentView the parent view
	 * @param quizEntity the quiz entity
	 */
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
		textItemListener = new textItemListener();
		setqPanelListeners();
		qFileChooser=this.view.getQuestionFileChooser();
		aFileChooser=this.view.getAnswerFileChooser();
		try {
			quizPath = quizEntity.getQuizFormFolder().getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qImgFile=null;
		aImgFile=null;
		
	}
	
	/**
	 * Initialize the file choosers.
	 */
	public void initializeFileChoosers()
	{
		this.view.initializeJFileChoosers();
		qFileChooser=this.view.getQuestionFileChooser();
		aFileChooser=this.view.getAnswerFileChooser();
	}
	
	/**
	 * Set the question panel listeners.
	 */
	private void setqPanelListeners()
	{
		view.addTextAreaQKeyListener(qPanelController.textItemListener);
		view.addTextAreaAKeyListener(qPanelController.textItemListener);
		view.addScoreTextFieldKeyListener(qPanelController.textItemListener);
		view.addChckbxHideQuestionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuizCreationController.saveFlag=0;
			}
		});	
	}
	
/**
 *
 * @see textItemEvent
 */
//	}
	public class textItemListener implements KeyListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag=0;
		}
	}
	
	/**
	 * Listener for the remove image button 
	 * @see removeBtnEvent
	 */
	class removeBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The buffered image. */
		private transient BufferedImage bufferedImage;
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			QuizCreationController.saveFlag=0;
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			if(qImgFile!=null && qImgFile.exists())
			{
				renameQuestionImage();				
			}
			if(aImgFile!=null && aImgFile.exists())
			{
				renameAnswerImage();
			}
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);		
			fixColors();
			for (int i = view.getQuestionNumber()-1; i < QuizCreationController.qPanels.size(); i++) {
				QuizCreationController.qPanels.get(i).view.setQuestionNumber(i+1);
				QuizCreationController.qPanels.get(i).view.getQuestionLbl().setText("Question"+(i+1));

			}
		}
			
			/**
			 * Fix colors to match the sequence Blue - Orange - Blue - Orange...
			 */
			private void fixColors() {
				for(int i=0;i<QuizCreationController.qPanels.size();i++)
				{
					if(i%2 == 0)
						QuizCreationController.qPanels.get(i).view.mainPanel.setBackground(SystemColor.textHighlight);
					else
						QuizCreationController.qPanels.get(i).view.mainPanel.setBackground(new Color(204, 102, 51));
				}
				
			}
			
		
	}
	
	/**
	 * Item Listener for answer type JComboBox
	 * @see aTypeItemEvent
	 */
	class aTypeItemListener implements ItemListener,Serializable
	{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getAnswerPanel().getLayout());
			    cl.show(view.getAnswerPanel(), (String)e.getItem());
			
			    if((!((String)e.getItem()).equals("Free Draw"))&&aImgFile!=null)
			    	removeAnswerImage();
			    QuizCreationController.saveFlag=0;
		}
		
	}
	
	/**
	 * 
	 * @see listenChkBoxEvent
	 */
	class listenChkBoxListener implements ActionListener,Serializable
	{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * Listener for question as an image browse button
	 * @see qBrowseBtnEvent
	 */
	class qBrowseBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The return val. */
		private int returnVal;
		
		/** The file save. */
		private transient File fileSave;
		
		/** The image. */
		private transient BufferedImage image;
		
		/** The question lbl. */
		private String questionLbl;
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=qFileChooser.showSaveDialog(view.getImageButtonsPanel());
			if(returnVal==qFileChooser.APPROVE_OPTION)
			{
				qImgFile = qFileChooser.getSelectedFile();				
				questionLbl = view.getQuestionLbl().getText()+".PNG";
				questionImgPath = quizPath +"/" + questionLbl;
				view.getqImage().setVisible(true);
				view.getRemoveQuestionImageBtn().setVisible(true);
				view.getImageButtonsPanel().revalidate();
				QuizCreationController.saveFlag=0;
			}
		}
		
	}
	
	/**
	 * Listener for answer as an image browse button
	 * @see ansBrowseBtnEvent
	 */
	class ansBrowseBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The return val. */
		private int returnVal;
		
		/** The file save. */
		private transient File fileSave;
		
		/** The image. */
		private transient BufferedImage image;
		
		/** The answer lbl. */
		private String answerLbl;
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=aFileChooser.showSaveDialog(view.getAnswerPanel());
			if(returnVal==aFileChooser.APPROVE_OPTION)
			{
				aImgFile = aFileChooser.getSelectedFile();				
				answerLbl = "Answer"+view.getQuestionNumber()+".PNG";
				answerImgPath = quizPath +"/" + answerLbl;
				view.getRemoveAnswerImageBtn().setVisible(true);
				view.getbtnViewAnswerImage().setVisible(true);
				view.getAnswerImageButtonsPanel().revalidate();
				QuizCreationController.saveFlag=0;
			}
		}
		
	}
	
	/**
	 * Listener for viewing the selected question image
	 * @see qImageBtnEvent
	 */
	class qImageBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The image. */
		private transient BufferedImage image;
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * Listener for viewing the selected answer image
	 * @see viewAnswerImageBtnAddEvent
	 */
	class viewAnswerImageBtnAddListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The image. */
		private transient BufferedImage image;
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	
	/**
	 * Listener for remove question image button
	 * @see removeQuestionImageBtnEvent
	 */
	class removeQuestionImageBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			renameQuestionImage();
			view.getRemoveQuestionImageBtn().setVisible(false);
			view.getqImage().setVisible(false);
			view.getImageButtonsPanel().revalidate();
			QuizCreationController.saveFlag=0;
		}
	}
	
	/**
	 * Listener for remove answer image button
	 * @see removeAnswerImageBtnEvent
	 */
	class removeAnswerImageBtnListener implements ActionListener,Serializable
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			removeAnswerImage();
			QuizCreationController.saveFlag=0;
		}
	}
	
	/**
	 * Rename question image.
	 */
	public void renameQuestionImage()
	{
		try {
			if(qImgFile.getParent().equals(quizPath))
			{
				BufferedImage bufferedImage = null;
				File tempFile = new File(quizPath+"/"+
				"Question"+view.getQuestionNumber()+"D"+".PNG");
				bufferedImage = ImageIO.read(qImgFile);
				ImageIO.write(bufferedImage,fileExtension , tempFile);
				qImgFile.delete();
				qImgFile=tempFile;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Removes the answer image.
	 */
	public void removeAnswerImage()
	{
		renameAnswerImage();
		view.getRemoveAnswerImageBtn().setVisible(false);
		view.getbtnViewAnswerImage().setVisible(false);
		view.getAnswerImageButtonsPanel().revalidate();
	}
	
	/**
	 * Rename answer image.
	 */
	public void renameAnswerImage()
	{
		try {
			if(aImgFile.getParent().equals(quizPath))
			{
				BufferedImage bufferedImage = null;
				File tempFile = new File(quizPath+"/"+
				"Answer"+view.getQuestionNumber()+"D"+".PNG");
				bufferedImage = ImageIO.read(aImgFile);
				ImageIO.write(bufferedImage,fileExtension , tempFile);
				aImgFile.delete();
				aImgFile=tempFile;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	/**
	 * Rename question and answer images to origin.
	 *
	 * @param file the file
	 */
	public static void renameQandAImagesToOrigin(File file)
	{
		try {
			if(file.getName().contains("D"))
				{
					String str = file.getName().replace("D", "");
					BufferedImage bufferedImage = null;
					File tempFile = new File(quizPath+"/"+str);				
						bufferedImage = ImageIO.read(file);
					ImageIO.write(bufferedImage,fileExtension , tempFile);
					file.delete();
					file=tempFile;
				} 
			}	
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Save images.
	 */
	public void saveImages()
	{
		BufferedImage image;
		File fileSave;
		if(qImgFile!=null&&!qImgFile.getName().contains("D")){
			try {
				String questionLbl = view.getQuestionLbl().getText()+".PNG";
				questionImgPath = quizPath +"/" + questionLbl;
				fileExtension = Files.getFileExtension(qImgFile.getCanonicalPath());
				
				image = ImageIO.read(qImgFile); 
				fileSave = new File(questionImgPath);
				ImageIO.write(image,fileExtension , fileSave);		
				if(qImgFile.getParent().equals(quizPath))
				{
				if(!qImgFile.getName().equals(questionLbl))
				qImgFile.delete();
				}
				qImgFile = fileSave;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		}
		if(aImgFile!=null&&!aImgFile.getName().contains("D"))
		{
			try {
				String answerLbl = "Answer"+view.getQuestionNumber()+".PNG";
				answerImgPath = quizPath +"/" + answerLbl;
				fileExtension = Files.getFileExtension(aImgFile.getCanonicalPath());
				
				image = ImageIO.read(aImgFile); 	
				fileSave = new File(answerImgPath);
				ImageIO.write(image,fileExtension , fileSave);
				if(aImgFile.getParent().equals(quizPath))
				{
				if(!aImgFile.getName().equals(answerLbl))
				aImgFile.delete();
				}
				aImgFile = fileSave;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		}
	}
	
	/**
	 * Gets the question panel.
	 *
	 * @return the question panel
	 */
	public qPanel getQuestionPanel() {
		return view;
	}
	
	/**
	 * Gets the quiz entity.
	 *
	 * @return the quiz entity
	 */
	public QuizEntity getQuizEntity() {
		return quizEntity;
	}
	
	/**
	 * Sets the quiz entity.
	 *
	 * @param quizEntity the new quiz entity
	 */
	public void setQuizEntity(QuizEntity quizEntity) {
		this.quizEntity = quizEntity;
	}
	
	/**
	 * Gets the parent view.
	 *
	 * @return the parent view
	 */
	public QuizCreationView getParentView() {
		return parentView;
	}
	
	/**
	 * Sets the parent view.
	 *
	 * @param parentView the new parent view
	 */
	public void setParentView(QuizCreationView parentView) {
		this.parentView = parentView;
	}
	
	/**
	 * Gets the question file chooser.
	 *
	 * @return the q file chooser
	 */
	public JFileChooser getqFileChooser() {
		return qFileChooser;
	}
	
	/**
	 * Sets the question file chooser.
	 *
	 * @param qFileChooser the new q file chooser
	 */
	public void setqFileChooser(JFileChooser qFileChooser) {
		this.qFileChooser = qFileChooser;
	}
	
	/**
	 * Gets the answer file chooser.
	 *
	 * @return the answer file chooser
	 */
	public JFileChooser getaFileChooser() {
		return aFileChooser;
	}
	
	/**
	 * Sets the answer file chooser.
	 *
	 * @param aFileChooser the new a file chooser
	 */
	public void setaFileChooser(JFileChooser aFileChooser) {
		this.aFileChooser = aFileChooser;
	}
	
	/**
	 * Gets the quiz path.
	 *
	 * @return the quiz path
	 */
	public String getQuizPath() {
		return quizPath;
	}
	
	/**
	 * Sets the quiz path.
	 *
	 * @param quizPath the new quiz path
	 */
	public void setQuizPath(String quizPath) {
		this.quizPath = quizPath;
	}
	
	/**
	 * Gets the question image file.
	 *
	 * @return the question image file
	 */
	public File getqImgFile() {
		return qImgFile;
	}
	
	/**
	 * Sets the question image file.
	 *
	 * @param qImgFile the new question image file
	 */
	public void setqImgFile(File qImgFile) {
		this.qImgFile = qImgFile;
	}
	
	/**
	 * Gets the answer image file.
	 *
	 * @return the answer image file
	 */
	public File getaImgFile() {
		return aImgFile;
	}
	
	/**
	 * Sets the answer image file.
	 *
	 * @param aImgFile the new answer image file
	 */
	public void setaImgFile(File aImgFile) {
		this.aImgFile = aImgFile;
	}
	
	/**
	 * Gets the file extension.
	 *
	 * @return the file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	
	/**
	 * Sets the file extension.
	 *
	 * @param fileExtension the new file extension
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	/**
	 * Gets the question image icon.
	 *
	 * @return the question image icon
	 */
	public JLabel getqImageIcon() {
		return qImageIcon;
	}
	
	/**
	 * Sets the question image icon.
	 *
	 * @param qImageIcon the new question image icon
	 */
	public void setqImageIcon(JLabel qImageIcon) {
		this.qImageIcon = qImageIcon;
	}
	}
	

