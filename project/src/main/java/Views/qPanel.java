package Views;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.MainFrameController;
import Controllers.MultipleChoicePanelController;
import Entities.Constants;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;

public class qPanel extends JPanel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//title
	private int questionNumber;
	private JLabel questionLbl;
	private JLabel lblScore;
	private JButton btnRemove;
	private JTextField scoreTextField;
	private JButton qbrowseBtn;
	private transient JFileChooser qFileChooser;
	private JTextArea textAreaA;
	private JCheckBox listenChkBox;
	private JCheckBox chckbxHideQuestion;
	//answer side
	private JComboBox<String> answerTypeCb;
	private transient JFileChooser aFileChooser;
	private JPanel answerPanel;
	private JLabel choicesLbl;
	private JButton ansBrowseBtn;
	
	private transient FileNameExtensionFilter extensionFilter;
	private MultipleChoicePanelController multipleChoicePanelController;
	
	private final static int width=Constants.FRAME_WIDTH-80;
//	private final static int height=(int) (MainFrameController.view.getContentPane().getHeight()/3);
//	private final static int width=1900;
	private final static int height=600;
	private static final int qPanelsGap = 10;
	private JTextArea textAreaQ;
	private JButton qImage;
	private JButton btnRemoveQuestionImage;
	private JButton btnRemoveAnswerImage;
	private JButton btnViewAnswerImage;
	private JPanel headPanel;
	private JPanel questionPartPanel;
	private JPanel textPanel;
	private JPanel listeningPanel;
	private JPanel answerPartPanel;
	private JPanel answerTitlePanel;
	private JPanel panel_3;
	private JLabel label;
	private JPanel freeDrawPanel;
	private JPanel imageButtonsPanel;
	private JPanel answerImageButtonsPanel;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel;
	public JPanel mainPanel;
	private JPanel panel_1;
	private JPanel panel_5;
	/**
	 * Create the panel.
	 */
	public qPanel() {
		setOpaque(false);
		setBackground(SystemColor.textHighlight);
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width,height));
		setMinimumSize(new Dimension(width, height));
		
		
		setAlignmentY(0.0f);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(javax.swing.Box.createVerticalStrut(qPanelsGap));
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.setBackground(SystemColor.textHighlight);
		add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		headPanel = new JPanel();
		mainPanel.add(headPanel);
		headPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		headPanel.setMaximumSize(new Dimension(32767, 60));
		headPanel.setOpaque(false);
		headPanel.setPreferredSize(new Dimension(800, 40));
		headPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setVgap(2);
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.setOpaque(false);
		headPanel.add(panel_4);
		//Question title objects
		questionLbl = new JLabel("Question 1.");
		panel_4.add(questionLbl);
		questionLbl.setVerticalAlignment(SwingConstants.TOP);
		questionLbl.setForeground(Color.WHITE);
		questionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		questionLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel_4.add(panel);
		
		lblScore = new JLabel("Score:");
		panel.add(lblScore);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		
		scoreTextField = new JTextField();
		panel.add(scoreTextField);
		scoreTextField.setHorizontalAlignment(SwingConstants.LEFT);
		scoreTextField.setColumns(10);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(2);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.setOpaque(false);
		headPanel.add(panel_2);
		
		btnRemove = new JButton("X");
		btnRemove.setBackground(new Color(255, 255, 255));
		panel_2.add(btnRemove);
		btnRemove.setPreferredSize(new Dimension(30, 30));
		btnRemove.setSize(new Dimension(25, 40));
		btnRemove.setMaximumSize(new Dimension(60, 60));
		btnRemove.setMinimumSize(new Dimension(0, 0));
		btnRemove.setMargin(new Insets(0, 0, 0, 0));
		
		panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 7));
		panel_5.setMaximumSize(new Dimension(32767, 7));
		panel_5.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		mainPanel.add(panel_5);
		
		questionPartPanel = new JPanel();
		questionPartPanel.setOpaque(false);
		mainPanel.add(questionPartPanel);
		questionPartPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "The Question", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), new Color(255, 255, 255)));
		questionPartPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel questionTextPanel = new JPanel();
		questionTextPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Question Text", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		questionTextPanel.setOpaque(false);
		questionPartPanel.add(questionTextPanel);
		questionTextPanel.setLayout(new BoxLayout(questionTextPanel, BoxLayout.Y_AXIS));
		
		textPanel = new JPanel();
		textPanel.setOpaque(false);
		textPanel.setPreferredSize(new Dimension(10, 100));
		textPanel.setMinimumSize(new Dimension(10, 100));
		questionTextPanel.add(textPanel);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		textPanel.add(scrollPane);
		
		textAreaQ = new JTextArea();
		textAreaQ.setFont(new Font("Courier New", Font.PLAIN, 12));
		scrollPane.setViewportView(textAreaQ);
		
		listeningPanel = new JPanel();
		listeningPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) listeningPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		questionTextPanel.add(listeningPanel);
		
		listenChkBox = new JCheckBox("Enable listening");
		listenChkBox.setBackground(Color.WHITE);
		listeningPanel.add(listenChkBox);
		listenChkBox.setOpaque(false);
		
		chckbxHideQuestion = new JCheckBox("Hide question");
		chckbxHideQuestion.setBackground(Color.WHITE);
		listeningPanel.add(chckbxHideQuestion);
		chckbxHideQuestion.setVisible(false);
		chckbxHideQuestion.setOpaque(false);
		
		JPanel questionImagePanel = new JPanel();
		questionImagePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Question Image", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		questionImagePanel.setOpaque(false);
		questionPartPanel.add(questionImagePanel);
		questionImagePanel.setLayout(new BoxLayout(questionImagePanel, BoxLayout.Y_AXIS));
		
		JPanel qImageTitleLbl = new JPanel();
		qImageTitleLbl.setOpaque(false);
		questionImagePanel.add(qImageTitleLbl);
		
		JLabel questionImageLbl = new JLabel("Add an image to the question");
		questionImageLbl.setForeground(Color.WHITE);
		qImageTitleLbl.add(questionImageLbl);
		questionImageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		questionImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		questionImageLbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		imageButtonsPanel = new JPanel();
		imageButtonsPanel.setOpaque(false);
		questionImagePanel.add(imageButtonsPanel);
		
		qbrowseBtn = new JButton("Browse..");
		qbrowseBtn.setBorder(UIManager.getBorder("Button.border"));
		qbrowseBtn.setBackground(Color.WHITE);
		imageButtonsPanel.add(qbrowseBtn);
		
		qImage = new JButton();
		qImage.setBorder(UIManager.getBorder("Button.border"));
		qImage.setBackground(Color.WHITE);
		imageButtonsPanel.add(qImage);
		qImage.setText("View Image");
		qImage.setVisible(false);
		
		btnRemoveQuestionImage = new JButton("Remove Image");
		btnRemoveQuestionImage.setBorder(UIManager.getBorder("Button.border"));
		btnRemoveQuestionImage.setBackground(Color.WHITE);
		imageButtonsPanel.add(btnRemoveQuestionImage);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 7));
		panel_1.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setMaximumSize(new Dimension(32767, 7));
		mainPanel.add(panel_1);
		
		answerPartPanel = new JPanel();
		answerPartPanel.setOpaque(false);
		mainPanel.add(answerPartPanel);
		answerPartPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "The Answer", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.WHITE));
		answerPartPanel.setLayout(new BoxLayout(answerPartPanel, BoxLayout.Y_AXIS));
		
		answerTitlePanel = new JPanel();
		answerTitlePanel.setOpaque(false);
		answerPartPanel.add(answerTitlePanel);
		answerTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setFont(new Font("Arial", Font.PLAIN, 11));
		answerTitlePanel.add(panel_3);
		
		JLabel lblAnswerType = new JLabel("Choose answer type:");
		lblAnswerType.setForeground(Color.WHITE);
		lblAnswerType.setFont(new Font("Arial", Font.BOLD, 14));
		panel_3.add(lblAnswerType);
		
		answerTypeCb = new JComboBox<String>();
		answerTypeCb.setBackground(Color.WHITE);
		panel_3.add(answerTypeCb);
		answerTypeCb.setModel(new DefaultComboBoxModel<String>(new String[] {"Multiple Choice", "Free Text", "Free Draw"}));
		answerTypeCb.setSelectedIndex(0);
		
		answerPanel = new JPanel();
		answerPanel.setMaximumSize(new Dimension(800, 32767));
		answerPartPanel.add(answerPanel);
		answerPanel.setOpaque(false);
		answerPanel.setLayout(new CardLayout(0, 0));
		
		MultipleChoicePanel multipleChoicePanel = new MultipleChoicePanel();
		multipleChoicePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choices", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		multipleChoicePanel.jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		multipleChoicePanel.setPreferredSize(new Dimension(444, 100));
		multipleChoicePanel.setMinimumSize(new Dimension(444, 100));
		GridBagLayout gridBagLayout_1 = (GridBagLayout) multipleChoicePanel.getLayout();
		gridBagLayout_1.columnWeights = new double[]{1.0};
		answerPanel.add(multipleChoicePanel, "Multiple Choice");
		multipleChoicePanelController = new MultipleChoicePanelController(multipleChoicePanel);
		multipleChoicePanel.jsp.setPreferredSize(new Dimension(answerPanel.getWidth(),answerPanel.getHeight()));
		
		JPanel freeTextPanel = new JPanel();
		freeTextPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Answer Text", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		answerPanel.add(freeTextPanel, "Free Text");
		GridBagLayout gbl_freeTextPanel = new GridBagLayout();
		gbl_freeTextPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_freeTextPanel.rowHeights = new int[]{93, 0};
		gbl_freeTextPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_freeTextPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		freeTextPanel.setLayout(gbl_freeTextPanel);
		
		JLabel lblEnterText = new JLabel("Enter text:");
		GridBagConstraints gbc_lblEnterText = new GridBagConstraints();
		gbc_lblEnterText.anchor = GridBagConstraints.NORTH;
		gbc_lblEnterText.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnterText.gridx = 0;
		gbc_lblEnterText.gridy = 0;
		freeTextPanel.add(lblEnterText, gbc_lblEnterText);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		freeTextPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaA = new JTextArea();
		scrollPane_1.setViewportView(textAreaA);
		
		freeDrawPanel = new JPanel();
		freeDrawPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Answer Image", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		freeDrawPanel.setOpaque(false);
		answerPanel.add(freeDrawPanel, "Free Draw");
		freeDrawPanel.setLayout(new BoxLayout(freeDrawPanel, BoxLayout.Y_AXIS));
		
		JPanel answerImageTitlePanel = new JPanel();
		answerImageTitlePanel.setOpaque(false);
		freeDrawPanel.add(answerImageTitlePanel);
		
		label = new JLabel("Add an image to the answer");
		label.setForeground(Color.WHITE);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		answerImageTitlePanel.add(label);
		
		answerImageButtonsPanel = new JPanel();
		answerImageButtonsPanel.setOpaque(false);
		freeDrawPanel.add(answerImageButtonsPanel);
		
		ansBrowseBtn = new JButton("Browse..");
		ansBrowseBtn.setBackground(Color.WHITE);
		answerImageButtonsPanel.add(ansBrowseBtn);
		
	
		
		btnViewAnswerImage = new JButton("View Image");
		btnViewAnswerImage.setBackground(Color.WHITE);
		answerImageButtonsPanel.add(btnViewAnswerImage);
		
		btnRemoveAnswerImage = new JButton("Remove Image");
		btnRemoveAnswerImage.setBackground(Color.WHITE);
		answerImageButtonsPanel.add(btnRemoveAnswerImage);
		
		
		btnViewAnswerImage.setVisible(false);
		btnRemoveAnswerImage.setVisible(false);
		freeTextPanel.setOpaque(false);
		
		Component verticalStrut = Box.createVerticalStrut(qPanelsGap);
		add(verticalStrut);
		btnRemoveQuestionImage.setVisible(false);
		 initializeJFileChoosers();
		
		
	}

	
	public JPanel getAnswerImageButtonsPanel() {
		return answerImageButtonsPanel;
	}


	public void setAnswerImageButtonsPanel(JPanel answerImageButtonsPanel) {
		this.answerImageButtonsPanel = answerImageButtonsPanel;
	}


	public JPanel getImageButtonsPanel() {
		return imageButtonsPanel;
	}


	public void setImageButtonsPanel(JPanel imageButtonsPanel) {
		this.imageButtonsPanel = imageButtonsPanel;
	}


	public JLabel getLblScore() {
		return lblScore;
	}
	public void setLblScore(JLabel lblScore) {
		this.lblScore = lblScore;
	}
	public JButton getBtnRemove() {
		return btnRemove;
	}
	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}
	public JButton getQbrowseBtn() {
		return qbrowseBtn;
	}
	public void setQbrowseBtn(JButton qbrowseBtn) {
		this.qbrowseBtn = qbrowseBtn;
	}
	public JButton getRemoveQuestionImageBtn() {
		return btnRemoveQuestionImage;
	}
	public JButton getRemoveAnswerImageBtn() {
		return btnRemoveAnswerImage;
	}
	public JFileChooser getqFileChooser() {
		return qFileChooser;
	}
	public void setqFileChooser(JFileChooser qFileChooser) {
		this.qFileChooser = qFileChooser;
	}
	public JTextArea getTextAreaQ() {
		return textAreaQ;
	}
	public void setTextAreaQ(JTextArea textAreaQ) {
		this.textAreaQ = textAreaQ;
	}
	public JTextArea getTextAreaA() {
		return textAreaA;
	}
	public void setTextAreaA(JTextArea textAreaA) {
		this.textAreaA = textAreaA;
	}
	public JComboBox<String> getAnswerTypeCb() {
		return answerTypeCb;
	}
	public void setAnswerTypeCb(JComboBox<String> answerTypeCb) {
		this.answerTypeCb = answerTypeCb;
	}
	public JFileChooser getaFileChooser() {
		return aFileChooser;
	}
	public void setaFileChooser(JFileChooser aFileChooser) {
		this.aFileChooser = aFileChooser;
	}
	public JLabel getChoicesLbl() {
		return choicesLbl;
	}
	public void setChoicesLbl(JLabel choicesLbl) {
		this.choicesLbl = choicesLbl;
	}
	public JButton getAnsBrowseBtn() {
		return ansBrowseBtn;
	}
	public void setAnsBrowseBtn(JButton ansBrowseBtn) {
		this.ansBrowseBtn = ansBrowseBtn;
	}
	public FileNameExtensionFilter getExtensionFilter() {
		return extensionFilter;
	}
	public void setExtensionFilter(FileNameExtensionFilter extensionFilter) {
		this.extensionFilter = extensionFilter;
	}
	public MultipleChoicePanelController getMultipleChoicePanelController() {
		return multipleChoicePanelController;
	}
	public void setMultipleChoicePanelController(MultipleChoicePanelController multipleChoicePanelController) {
		this.multipleChoicePanelController = multipleChoicePanelController;
	}
	public JTextField getScoreTextField() {
		return scoreTextField;
	}
	public void setScoreTextField(JTextField textField) {
		this.scoreTextField = textField;
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
	public void qBrowseBtnAddListener(ActionListener listener)
	{
		qbrowseBtn.addActionListener(listener);
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
	public void qImageBtnAddListener(ActionListener listener)
	{
		qImage.addActionListener(listener);
	}
	public void viewAnswerImageBtnAddListener(ActionListener listener)
	{
		btnViewAnswerImage.addActionListener(listener);
	}
	public void removeQuestionImageBtnAddListener(ActionListener listener)
	{
		btnRemoveQuestionImage.addActionListener(listener);
	}
	public void removeAnswerImageBtnAddListener(ActionListener listener)
	{
		btnRemoveAnswerImage.addActionListener(listener);
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
	public JButton getqImage() {
		return qImage;
	}
	public JButton getbtnViewAnswerImage() {
		return btnViewAnswerImage;
	}
	public void setqImage(JButton qImage) {
		this.qImage = qImage;
	}

	public JButton getQuestionImageBrosweButton() {
		return qbrowseBtn;
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
	
	public JFileChooser getQuestionFileChooser() {
		return qFileChooser;
	}
	public void setQuestionFileChooser(JFileChooser fileChooser) {
		this.qFileChooser= fileChooser;
	}
	public JFileChooser getAnswerFileChooser() {
		return aFileChooser;
	}
	public void setAnswerFileChooser(JFileChooser fileChooser) {
		this.aFileChooser= fileChooser;
	}
	
	public void addTextAreaQKeyListener(KeyListener listener){
		textAreaQ.addKeyListener(listener);
	}
	public void addTextAreaAKeyListener(KeyListener listener){
		textAreaA.addKeyListener(listener);
	}
	public void addScoreTextFieldKeyListener(KeyListener listener){
		scoreTextField.addKeyListener(listener);
	}
	public void addChckbxHideQuestionListener(ActionListener listener){
		chckbxHideQuestion.addActionListener(listener);
	}
	public void initializeJFileChoosers()
	{
		qFileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		qFileChooser.setFileFilter(extensionFilter);
		qFileChooser.setAcceptAllFileFilterUsed(false);
		aFileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		aFileChooser.setFileFilter(extensionFilter);
		aFileChooser.setAcceptAllFileFilterUsed(false);
	}
	
}
