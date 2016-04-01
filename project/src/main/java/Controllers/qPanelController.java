package Controllers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class qPanelController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public qPanel view;

	private QuizEntity quizEntity;
	private QuizCreationView parentView;
	private transient JFileChooser qFileChooser;
	private transient JFileChooser aFileChooser;
	private static String quizPath;
	private File qImgFile;
	private File aImgFile;
	private static String fileExtension;
	private JLabel qImageIcon=null;
	private String questionImgPath;
	private String answerImgPath;
	public static textItemListener textItemListener;
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
	public void intializefilechoosers()
	{
		this.view.initializeJFileChoosers();
		qFileChooser=this.view.getQuestionFileChooser();
		aFileChooser=this.view.getAnswerFileChooser();
	}
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
	
//	public static void setcheckboxFieldActionListeners(Component comp)
//	{
//		for (Component item : ((Container) comp).getComponents()){
//			
//            if ((item.getClass() == JTextField.class)){
//            	JTextField textFiled = (JTextField) item;
//            	textFiled.addKeyListener(textItemListener);
//            }
//            if ((item.getClass() == JCheckBox.class)){
//            	JCheckBox checkBox = (JCheckBox) item;
//            	checkBox.addActionListener(new ActionListener() {
//					
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						QuizCreationController.saveFlag=0;
//					}
//				});
//            }
//            
//            if( item instanceof Container ) {
//                Component[] comps = ( (Container) item ).getComponents();
//                for( Component c : comps ) {
//                	setcheckboxFieldActionListeners(c);
//                }
//            }
//        }
//	}
	public class textItemListener implements KeyListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			QuizCreationController.saveFlag=0;
		}
	}
	
	class removeBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private transient BufferedImage bufferedImage;
		public void actionPerformed(ActionEvent e) {
			QuizCreationController.saveFlag=0;
			parentView.panel.remove(view);
			parentView.panel.revalidate();
			MainFrameController.view.repaint();
			if(qImgFile!=null && qImgFile.exists())
			{
//				qImgFile=null;
//				questionImgPath="";
//				qImgFile.delete();
				renameQuestionImage();				
			}
			if(aImgFile!=null && aImgFile.exists())
			{
//				aImgFile.delete();
//				aImgFile=null;	
//				answerImgPath="";
				renameAnswerImage();
			}
			QuizCreationController.qPanels.remove(view.getQuestionNumber()-1);		
			fixColors();
			for (int i = view.getQuestionNumber()-1; i < QuizCreationController.qPanels.size(); i++) {
				QuizCreationController.qPanels.get(i).view.setQuestionNumber(i+1);
				QuizCreationController.qPanels.get(i).view.getQuestionLbl().setText("Question"+(i+1));

				
//				try {
//					if(QuizCreationController.qPanels.get(i).qImgFile!=null){
//					File tempFile = new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
//							"Question"+(i+1)+".PNG");
//					bufferedImage = ImageIO.read(QuizCreationController.qPanels.get(i).qImgFile);
//					ImageIO.write(bufferedImage,QuizCreationController.qPanels.get(i).fileExtension , tempFile);
//					QuizCreationController.qPanels.get(i).qImgFile.delete();
//					QuizCreationController.qPanels.get(i).qImgFile=tempFile;
//					}
//					if(QuizCreationController.qPanels.get(i).aImgFile!=null){
//					File tempFile = new File(QuizCreationController.qPanels.get(i).quizPath+"/"+
//							"Answer"+(i+1)+".PNG");
//					bufferedImage = ImageIO.read(QuizCreationController.qPanels.get(i).aImgFile);
//					ImageIO.write(bufferedImage,QuizCreationController.qPanels.get(i).fileExtension , tempFile);
//					QuizCreationController.qPanels.get(i).aImgFile.delete();
//					QuizCreationController.qPanels.get(i).aImgFile=tempFile;
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}	
				
			}
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
	class aTypeItemListener implements ItemListener,Serializable
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void itemStateChanged(ItemEvent e) {
			 CardLayout cl = (CardLayout)(view.getAnswerPanel().getLayout());
			    cl.show(view.getAnswerPanel(), (String)e.getItem());
			
			    if((!((String)e.getItem()).equals("Free Draw"))&&aImgFile!=null)
			    	removeAnswerImage();
			    QuizCreationController.saveFlag=0;
		}
		
	}
	class listenChkBoxListener implements ActionListener,Serializable
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
	
	class qBrowseBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int returnVal;
		private transient File fileSave;
		private transient BufferedImage image;
		private String questionLbl;
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			returnVal=qFileChooser.showSaveDialog(view.getQuestionDataPanel());
			if(returnVal==qFileChooser.APPROVE_OPTION)
			{
				qImgFile = qFileChooser.getSelectedFile();				
				questionLbl = view.getQuestionLbl().getText()+".PNG";
				questionImgPath = quizPath +"/" + questionLbl;
				view.getqImage().setVisible(true);
				view.getRemoveQuestionImageBtn().setVisible(true);
				view.getQuestionDataPanel().revalidate();
				QuizCreationController.saveFlag=0;
			}
		}
		
	}
	class ansBrowseBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int returnVal;
		private transient File fileSave;
		private transient BufferedImage image;
		private String answerLbl;
		
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
				view.getQuestionDataPanel().revalidate();
				QuizCreationController.saveFlag=0;
			}
		}
		
	}
	class qImageBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private transient BufferedImage image;
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
	
	class viewAnswerImageBtnAddListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private transient BufferedImage image;
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
	
	
	class removeQuestionImageBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			renameQuestionImage();
			view.getRemoveQuestionImageBtn().setVisible(false);
			view.getqImage().setVisible(false);
			view.getQuestionDataPanel().revalidate();
			QuizCreationController.saveFlag=0;
		}
	}
	
	class removeAnswerImageBtnListener implements ActionListener,Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			removeAnswerImage();
			QuizCreationController.saveFlag=0;
		}
	}
	
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
	
	public void removeAnswerImage()
	{
		renameAnswerImage();
		view.getRemoveAnswerImageBtn().setVisible(false);
		view.getbtnViewAnswerImage().setVisible(false);
		view.getQuestionDataPanel().revalidate();
	}
	
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
	}
	

