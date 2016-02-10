package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.MainFrameController;
import Controllers.MultipleChoicePanelController;

import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Insets;
import java.awt.Point;

public class qPanel extends ViewPanel {
	private JLabel questionLbl;
	private JComboBox<String> answerTypeCb;
	private JButton btnRemove;
	private JTextField scoreField;
	private JLabel lblScore;
	private JPanel qDataPanel;
	private JButton browseBtn;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter extensionFilter;
	private JTextArea textArea;
	private int questionNumber;
	private JCheckBox listenChkBox;
	private JCheckBox chckbxHideQuestion;
	private JPanel answerPanel;
	private MultipleChoicePanelController multipleChoicePanelController;
	private JLabel choicesLbl;
	private JLabel theAnswerLbl;
	private JButton ansBrowseBtn;
	private final static int width=MainFrameController.view.getWidth()-20;
	private final static int height=(int) ((int)MainFrameController.view.getHeight()/2.5f);
	/**
	 * Create the panel.
	 */
	public qPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width,height));
		setMinimumSize(new Dimension(width, height));
		setLayout(null);
		setAlignmentY(0.0f);
		
		btnRemove = new JButton("X");
		btnRemove.setMargin(new Insets(0, 0, 0, 0));
		btnRemove.setFont(new Font("Arial", Font.BOLD, 15));
		btnRemove.setBounds(width-40, 10, 30, 27);
		add(btnRemove);
		
		questionLbl = new JLabel("Question "+questionNumber);
		questionLbl.setBounds(8, 8, 126, 27);
		questionLbl.setFont(new Font("Arial", Font.BOLD, 20));
		add(questionLbl);
		
		lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Arial", Font.PLAIN, 11));
		lblScore.setBounds(getRightPos(questionLbl)+10, 11, 52, 14);
		add(lblScore);
		
		scoreField = new JTextField();
		scoreField.setBounds(getRightPos(lblScore)+2, 8, 52, 20);
		add(scoreField);
		scoreField.setColumns(10);
		
		
		
		//Question side
		
		JLabel theQuestionLbl = new JLabel("The Question");
		theQuestionLbl.setSize(141, 18);
		theQuestionLbl.setLocation(setCorrectPosX(theQuestionLbl, width/4), height/8);
		theQuestionLbl.setFont(new Font("Arial", Font.BOLD, 18));
		add(theQuestionLbl);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(width/2, theQuestionLbl.getHeight(), 2, height-8);
		add(separator);
		
		qDataPanel = new JPanel();
		qDataPanel.setSize(width/2-20, height*7/16);
		qDataPanel.setLocation(8, setCorrectPosY(qDataPanel, height*5/8));
		add(qDataPanel);
		qDataPanel.setLayout(null);
		qDataPanel.setOpaque(false);
		
		JLabel qLabel = new JLabel("Enter question:");
		qLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		qLabel.setBounds(5, 7, 117, 14);
		qDataPanel.add(qLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(qDataPanel.getWidth()*5/8,qDataPanel.getHeight()*3/4);
		scrollPane.setLocation(getRightPos(qLabel)+2, qLabel.getY()); 
		qDataPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		listenChkBox = new JCheckBox("Enable listening");
		listenChkBox.setFont(new Font("Tahoma", Font.PLAIN, 9));
		listenChkBox.setSize(97, 23);
		listenChkBox.setLocation(getRightPos(scrollPane)+2,scrollPane.getY());
		listenChkBox.setOpaque(false);
		qDataPanel.add(listenChkBox);
		
		chckbxHideQuestion = new JCheckBox("Hide question");
		chckbxHideQuestion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxHideQuestion.setOpaque(false);
		chckbxHideQuestion.setVisible(false);
		chckbxHideQuestion.setSize(97, 23);
		chckbxHideQuestion.setLocation(listenChkBox.getX(),listenChkBox.getY()+30);
		qDataPanel.add(chckbxHideQuestion);
		
		JLabel browseLabel = new JLabel("Browse an image:");
		browseLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		browseLabel.setBounds(qLabel.getX(), scrollPane.getY()+scrollPane.getHeight()+20, 104, 19);
		qDataPanel.add(browseLabel);
		
		fileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		fileChooser.setFileFilter(extensionFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		browseBtn = new JButton("Browse..");
		browseBtn.setMargin(new Insets(0, 0, 0, 0));
		browseBtn.setSize(89, 23);
		browseBtn.setLocation(getRightPos(browseLabel)+2, browseLabel.getY());
		qDataPanel.add(browseBtn);
		
	
		
		//Answer side
		theAnswerLbl = new JLabel("The Answer");
		theAnswerLbl.setFont(new Font("Arial", Font.BOLD, 18));
		theAnswerLbl.setBounds(width*3/4, height/8, 141, 18);
		add(theAnswerLbl);
		
		JLabel answerTypeLbl = new JLabel("Answer type:");
		answerTypeLbl.setSize(95, 18);
		answerTypeLbl.setLocation(setCorrectPosX(answerTypeLbl,width*10/16), setCorrectPosY(answerTypeLbl, height*5/16));
		answerTypeLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		add(answerTypeLbl);
		
		answerTypeCb = new JComboBox<String>();
		answerTypeCb.setSize(160, 20);
		answerTypeCb.setLocation(getRightPos(answerTypeLbl)+2, answerTypeLbl.getY());
		answerTypeCb.addItem("Multiple Choice");
		answerTypeCb.addItem("Free Text");
		answerTypeCb.addItem("Free Drawing");
		answerTypeCb.setSelectedIndex(0);
		answerTypeCb.setMaximumSize(answerTypeCb.getPreferredSize());
		add(answerTypeCb);
		
		answerPanel = new JPanel();
		answerPanel.setBounds(setCorrectPosX(answerPanel, width*10/16), setCorrectPosY(answerPanel, height*5/16), 317, 162);
		add(answerPanel);
		answerPanel.setLayout(new CardLayout(0, 0));
		
		MultipleChoicePanel multipleChoicePanel = new MultipleChoicePanel();
		multipleChoicePanelController = new MultipleChoicePanelController(multipleChoicePanel);
		answerPanel.add(multipleChoicePanel, "Multiple Choice");
		
		JPanel freeTextPanel = new JPanel();
		answerPanel.add(freeTextPanel, "Free Text");
		freeTextPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(96, 11, 211, 140);
		freeTextPanel.add(scrollPane_1);
		
		JTextArea answerTextA = new JTextArea();
		scrollPane_1.setViewportView(answerTextA);
		answerTextA.setWrapStyleWord(true);
		answerTextA.setLineWrap(true);
		answerTextA.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JLabel lblEnterAnswer = new JLabel("Enter answer:");
		lblEnterAnswer.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEnterAnswer.setBounds(10, 14, 90, 14);
		freeTextPanel.add(lblEnterAnswer);
		
		JPanel freeDrawPanel = new JPanel();
		answerPanel.add(freeDrawPanel, "Free Drawing");
		freeDrawPanel.setLayout(null);
		
		ansBrowseBtn = new JButton("Browse..");
		ansBrowseBtn.setBounds(124, 10, 89, 23);
		ansBrowseBtn.setMargin(new Insets(0, 0, 0, 0));
		freeDrawPanel.add(ansBrowseBtn);
		
		JLabel label = new JLabel("Browse an image:");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setBounds(10, 11, 104, 19);
		freeDrawPanel.add(label);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, theAnswerLbl.getY()+theAnswerLbl.getHeight(), width-2, 2);
		add(separator_1);
	
	
		
	}
	
	public JPanel getAnswerPanel() {
		return answerPanel;
	}
	public void setAnswerPanel(JPanel answerPanel) {
		this.answerPanel = answerPanel;
	}
	public JCheckBox getChckbxHideQuestion() {
		return chckbxHideQuestion;
	}
	public void setChckbxHideQuestion(JCheckBox chckbxHideQuestion) {
		this.chckbxHideQuestion = chckbxHideQuestion;
	}
	public void browseBtnAddListener(ActionListener listener)
	{
		browseBtn.addActionListener(listener);
	}
	
	public void removeBtnAddListener(ActionListener listener)
	{
		btnRemove.addActionListener(listener);
	}
	public void aTypeCBaddItemListener(ItemListener listener)
	{
		answerTypeCb.addItemListener(listener);
	}
	public void listenChkBoxAddListner(ActionListener listener)
	{
		listenChkBox.addActionListener(listener);
	}
	public void ansBrowseBtnAddListener(ActionListener listener)
	{
		ansBrowseBtn.addActionListener(listener);
	}
	public JCheckBox getListenChkBox() {
		return listenChkBox;
	}
	public void setListenChkBox(JCheckBox listenChkBox) {
		this.listenChkBox = listenChkBox;
	}
	
	public JLabel getQuestionLbl() {
		return questionLbl;
	}
	public void setQuestionLbl(JLabel questionLbl) {
		this.questionLbl = questionLbl;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser= fileChooser;
	}
	public JPanel getQuestionDataPanel() {
		return qDataPanel;
	}
	public void setQuestionDataPanel(String s) {
		textArea.setText(s);;
	}
	private int getRightPos(JComponent comp)
	{
		return comp.getX()+comp.getWidth();
	}
	private int setCorrectPosX(JComponent comp,int tempW) {
		// TODO Auto-generated method stub
		return tempW-comp.getWidth();
	}
	private int setCorrectPosY(JComponent comp,int tempH) {
		// TODO Auto-generated method stub
		return tempH-comp.getHeight();
	}
}
