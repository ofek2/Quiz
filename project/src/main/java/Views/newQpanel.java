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
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.awt.Component;

public class newQpanel extends JPanel implements Serializable{
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
	
//	private final static int width=MainFrameController.view.getContentPane().getWidth()-20;
//	private final static int height=(int) (MainFrameController.view.getContentPane().getHeight()/3);
	private final static int width=800;
	private final static int height=400;
	private JTextArea textAreaQ;
	private JButton qImage;
	private JButton btnRemoveQuestionImage;
	private JButton btnRemoveAnswerImage;
	private JButton btnViewAnswerImage;
	private JPanel headPanel;
	private JPanel removePanel;
	private JPanel questionPartPanel;
	private JPanel textPanel;
	private JPanel listeningPanel;
	private JPanel answerPartPanel;
	private JPanel answerTitlePanel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel label;
	/**
	 * Create the panel.
	 */
	public newQpanel() {
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width,height));
		setMinimumSize(new Dimension(width, height));
		
		
		setAlignmentY(0.0f);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		removePanel = new JPanel();
		removePanel.setPreferredSize(new Dimension(800, 10));
		add(removePanel);
		removePanel.setLayout(new BorderLayout(0, 0));
		
		btnRemove = new JButton("X");
		btnRemove.setPreferredSize(new Dimension(30, 30));
		btnRemove.setSize(new Dimension(25, 25));
		btnRemove.setMaximumSize(new Dimension(35, 35));
		btnRemove.setMinimumSize(new Dimension(30, 30));
		removePanel.add(btnRemove, BorderLayout.EAST);
		btnRemove.setMargin(new Insets(0, 0, 0, 0));
		
		headPanel = new JPanel();
		headPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(headPanel);
		headPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		headPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		//Question title objects
		questionLbl = new JLabel("Question 1.");
		questionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(questionLbl);
		questionLbl.setFont(new Font("Arial", Font.BOLD, 17));
		
		JPanel panel_1 = new JPanel();
		headPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		lblScore = new JLabel("Score:");
		panel_1.add(lblScore);
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		
		scoreTextField = new JTextField();
		panel_1.add(scoreTextField);
		scoreTextField.setHorizontalAlignment(SwingConstants.LEFT);
		scoreTextField.setColumns(10);
		
		questionPartPanel = new JPanel();
		questionPartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(questionPartPanel);
		questionPartPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel questionTextPanel = new JPanel();
		questionPartPanel.add(questionTextPanel);
		questionTextPanel.setLayout(new BoxLayout(questionTextPanel, BoxLayout.Y_AXIS));
		
		JPanel questionTitlePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) questionTitlePanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		questionTextPanel.add(questionTitlePanel);
		
		JLabel lblTheQuestion = new JLabel("Enter the question properties");
		questionTitlePanel.add(lblTheQuestion);
		lblTheQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheQuestion.setFont(new Font("Arial", Font.BOLD, 15));
		
		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(10, 100));
		textPanel.setMinimumSize(new Dimension(10, 100));
		questionTextPanel.add(textPanel);
		GridBagLayout gbl_textPanel = new GridBagLayout();
		gbl_textPanel.columnWidths = new int[]{95, 198, 0};
		gbl_textPanel.rowHeights = new int[]{100, 0};
		gbl_textPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_textPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		textPanel.setLayout(gbl_textPanel);
		
		JLabel lblEnterQuestion = new JLabel("Enter question:");
		lblEnterQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblEnterQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEnterQuestion = new GridBagConstraints();
		gbc_lblEnterQuestion.fill = GridBagConstraints.BOTH;
		gbc_lblEnterQuestion.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnterQuestion.gridx = 0;
		gbc_lblEnterQuestion.gridy = 0;
		textPanel.add(lblEnterQuestion, gbc_lblEnterQuestion);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		textPanel.add(scrollPane, gbc_scrollPane);
		
		textAreaQ = new JTextArea();
		textAreaQ.setFont(new Font("Courier New", Font.PLAIN, 12));
		scrollPane.setViewportView(textAreaQ);
		
		listeningPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) listeningPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		questionTextPanel.add(listeningPanel);
		
			
			listenChkBox = new JCheckBox("Enable listening");
			listeningPanel.add(listenChkBox);
			listenChkBox.setOpaque(false);
			
			chckbxHideQuestion = new JCheckBox("Hide question");
			listeningPanel.add(chckbxHideQuestion);
			chckbxHideQuestion.setVisible(false);
			chckbxHideQuestion.setOpaque(false);
		
		JPanel questionImagePanel = new JPanel();
		questionPartPanel.add(questionImagePanel);
		questionImagePanel.setLayout(new BoxLayout(questionImagePanel, BoxLayout.Y_AXIS));
		
		JPanel qImageTitleLbl = new JPanel();
		questionImagePanel.add(qImageTitleLbl);
		
		JLabel questionImageLbl = new JLabel("Add an image to the question");
		qImageTitleLbl.add(questionImageLbl);
		questionImageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		questionImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		questionImageLbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		JPanel imageButtonsPanel = new JPanel();
		questionImagePanel.add(imageButtonsPanel);
		
		qbrowseBtn = new JButton("Browse..");
		imageButtonsPanel.add(qbrowseBtn);
		
		qImage = new JButton();
		imageButtonsPanel.add(qImage);
		qImage.setText("View Image");
		qImage.setVisible(false);
		
		btnRemoveQuestionImage = new JButton("Remove Image");
		imageButtonsPanel.add(btnRemoveQuestionImage);
		btnRemoveQuestionImage.setVisible(false);
		
		answerPartPanel = new JPanel();
		add(answerPartPanel);
		answerPartPanel.setLayout(new BoxLayout(answerPartPanel, BoxLayout.Y_AXIS));
		
		answerTitlePanel = new JPanel();
		answerPartPanel.add(answerTitlePanel);
		answerTitlePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		answerTitlePanel.add(panel_2);
		
		JLabel lblTheAnswer = new JLabel("Enter the answer properties");
		panel_2.add(lblTheAnswer);
		lblTheAnswer.setFont(new Font("Arial", Font.BOLD, 15));
		
		panel_3 = new JPanel();
		panel_3.setFont(new Font("Arial", Font.PLAIN, 11));
		answerTitlePanel.add(panel_3);
		
		JLabel lblAnswerType = new JLabel("Answer type:");
		lblAnswerType.setFont(new Font("Arial", Font.BOLD, 14));
		panel_3.add(lblAnswerType);
		
		answerTypeCb = new JComboBox<String>();
		panel_3.add(answerTypeCb);
		answerTypeCb.setModel(new DefaultComboBoxModel<String>(new String[] {"Multiple Choice", "Free Text", "Free Draw"}));
		answerTypeCb.setSelectedIndex(0);
		
		answerPanel = new JPanel();
		answerPartPanel.add(answerPanel);
		answerPanel.setOpaque(false);
		answerPanel.setLayout(new CardLayout(0, 0));
		
		MultipleChoicePanel multipleChoicePanel = new MultipleChoicePanel();
		multipleChoicePanel.setPreferredSize(new Dimension(444, 100));
		multipleChoicePanel.setMinimumSize(new Dimension(444, 100));
		GridBagLayout gridBagLayout_1 = (GridBagLayout) multipleChoicePanel.getLayout();
		gridBagLayout_1.columnWeights = new double[]{1.0};
		answerPanel.add(multipleChoicePanel, "Multiple Choice");
		multipleChoicePanelController = new MultipleChoicePanelController(multipleChoicePanel);
		multipleChoicePanel.jsp.setPreferredSize(new Dimension(answerPanel.getWidth(),answerPanel.getHeight()));
		
		JPanel freeTextPanel = new JPanel();
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
		
		JPanel freeDrawPanel = new JPanel();
		answerPanel.add(freeDrawPanel, "Free Draw");
		freeDrawPanel.setLayout(new BoxLayout(freeDrawPanel, BoxLayout.Y_AXIS));
		
		JPanel answerImageTitlePanel = new JPanel();
		freeDrawPanel.add(answerImageTitlePanel);
		
		label = new JLabel("Add an image to the answer");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		answerImageTitlePanel.add(label);
		freeDrawPanel.setOpaque(false);
		
		JPanel answerImageButtonsPanel = new JPanel();
		freeDrawPanel.add(answerImageButtonsPanel);
		
		ansBrowseBtn = new JButton("Browse..");
		answerImageButtonsPanel.add(ansBrowseBtn);
		
		btnRemoveAnswerImage = new JButton("Remove Image");
		answerImageButtonsPanel.add(btnRemoveAnswerImage);
		
		btnViewAnswerImage = new JButton("View Image");
		answerImageButtonsPanel.add(btnViewAnswerImage);
		btnViewAnswerImage.setVisible(false);
		btnRemoveAnswerImage.setVisible(false);
		freeTextPanel.setOpaque(false);
		
		 initializeJFileChoosers();
		
		
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
