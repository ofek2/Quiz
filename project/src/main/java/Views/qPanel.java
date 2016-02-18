package Views;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.MainFrameController;
import Controllers.MultipleChoicePanelController;
import Controllers.QuizCreationController;
import Controllers.qPanelController;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.CardLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class qPanel extends JPanel implements Serializable{
	//title
	private transient int questionNumber;
	private JLabel questionLbl;
	private JLabel lblScore;
	private JButton btnRemove;
	private JTextField scoreTextField;
	//question side
	private JPanel qDataPanel;
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
	
	private final static int width=MainFrameController.view.getContentPane().getWidth()-20;
	private final static int height=(int) (MainFrameController.view.getContentPane().getHeight()/3);
//	private final static int width=1900;
//	private final static int height=540;
	
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JTextArea textAreaQ;
	private JButton qImage;
	private JButton btnRemoveQuestionImage;
	private JButton btnRemoveAnswerImage;
	private JButton btnViewAnswerImage;
	/**
	 * Create the panel.
	 */
	public qPanel() {
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width,height));
		setMinimumSize(new Dimension(width, height));
		
		
		setAlignmentY(0.0f);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{119, 0, 54, 63, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0};
		gridBagLayout.rowHeights = new int[]{0, -3, 0, -2, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		//Question title objects
		questionLbl = new JLabel("Question 1.");
		questionLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_qLabel = new GridBagConstraints();
		gbc_qLabel.fill = GridBagConstraints.BOTH;
		gbc_qLabel.insets = new Insets(0, 5, 5, 5);
		gbc_qLabel.gridx = 0;
		gbc_qLabel.gridy = 0;
		add(questionLbl, gbc_qLabel);
		
		lblScore = new JLabel("Score:");
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.anchor = GridBagConstraints.WEST;
		gbc_lblScore.fill = GridBagConstraints.VERTICAL;
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 1;
		gbc_lblScore.gridy = 0;
		add(lblScore, gbc_lblScore);
		
		scoreTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.VERTICAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(scoreTextField, gbc_textField);
		scoreTextField.setColumns(10);
		
		btnRemove = new JButton("X");
		btnRemove.setMargin(new Insets(0, 0, 0, 0));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 24;
		gbc_btnNewButton.gridy = 0;
		add(btnRemove, gbc_btnNewButton);
		
		JLabel lblTheQuestion = new JLabel("The Question");
		lblTheQuestion.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblTheQuestion = new GridBagConstraints();
		gbc_lblTheQuestion.anchor = GridBagConstraints.WEST;
		gbc_lblTheQuestion.fill = GridBagConstraints.VERTICAL;
		gbc_lblTheQuestion.insets = new Insets(0, 5, 5, 5);
		gbc_lblTheQuestion.gridx = 0;
		gbc_lblTheQuestion.gridy = 2;
		add(lblTheQuestion, gbc_lblTheQuestion);
		
		separator_2 = new JSeparator();
		separator_2.setPreferredSize(new Dimension(2, 2));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.VERTICAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 12;
		gbc_separator_2.gridy = 2;
		add(separator_2, gbc_separator_2);
		
		JLabel lblTheAnswer = new JLabel("The Answer");
		lblTheAnswer.setFont(new Font("Arial", Font.BOLD, 15));
		GridBagConstraints gbc_lblTheAnswer = new GridBagConstraints();
		gbc_lblTheAnswer.anchor = GridBagConstraints.WEST;
		gbc_lblTheAnswer.fill = GridBagConstraints.VERTICAL;
		gbc_lblTheAnswer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheAnswer.gridx = 13;
		gbc_lblTheAnswer.gridy = 2;
		add(lblTheAnswer, gbc_lblTheAnswer);
		
		JLabel lblAnswerType = new JLabel("Answer type:");
		GridBagConstraints gbc_lblAnswerType = new GridBagConstraints();
		gbc_lblAnswerType.anchor = GridBagConstraints.EAST;
		gbc_lblAnswerType.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswerType.gridx = 19;
		gbc_lblAnswerType.gridy = 2;
		add(lblAnswerType, gbc_lblAnswerType);
		
		answerTypeCb = new JComboBox();
		answerTypeCb.setModel(new DefaultComboBoxModel(new String[] {"Multiple Choice", "Free Text", "Free Draw"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 20;
		gbc_comboBox.gridy = 2;
		answerTypeCb.setSelectedIndex(0);
		add(answerTypeCb, gbc_comboBox);
		
		separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 2));
		separator_1.setPreferredSize(new Dimension(100, 2));
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 25;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		add(separator_1, gbc_separator_1);
		
		qDataPanel = new JPanel();
		GridBagConstraints gbc_qDataPanel = new GridBagConstraints();
		gbc_qDataPanel.gridwidth = 12;
		gbc_qDataPanel.insets = new Insets(5, 5, 0, 5);
		gbc_qDataPanel.fill = GridBagConstraints.BOTH;
		gbc_qDataPanel.gridx = 0;
		gbc_qDataPanel.gridy = 4;
		add(qDataPanel, gbc_qDataPanel);
		GridBagLayout gbl_qDataPanel = new GridBagLayout();
		gbl_qDataPanel.columnWidths = new int[]{0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_qDataPanel.rowHeights = new int[]{94, 23, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_qDataPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_qDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		qDataPanel.setLayout(gbl_qDataPanel);
		
		JLabel lblEnterQuestion = new JLabel("Enter question:");
		GridBagConstraints gbc_lblEnterQuestion = new GridBagConstraints();
		gbc_lblEnterQuestion.anchor = GridBagConstraints.NORTH;
		gbc_lblEnterQuestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnterQuestion.insets = new Insets(0, 5, 5, 5);
		gbc_lblEnterQuestion.gridx = 0;
		gbc_lblEnterQuestion.gridy = 0;
		qDataPanel.add(lblEnterQuestion, gbc_lblEnterQuestion);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		qDataPanel.add(scrollPane, gbc_scrollPane);
		
		textAreaQ = new JTextArea();
		textAreaQ.setFont(new Font("Courier New", Font.PLAIN, 12));
		scrollPane.setViewportView(textAreaQ);
		
		separator = new JSeparator();
		separator.setSize(new Dimension(2, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(2, 2));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 12;
		gbc_separator.gridy = 4;
		add(separator, gbc_separator);
		
		answerPanel = new JPanel();
		GridBagConstraints gbc_answerPanel = new GridBagConstraints();
		gbc_answerPanel.insets = new Insets(5, 0, 0, 0);
		gbc_answerPanel.gridwidth = 12;
		gbc_answerPanel.fill = GridBagConstraints.BOTH;
		gbc_answerPanel.gridx = 13;
		gbc_answerPanel.gridy = 4;
		add(answerPanel, gbc_answerPanel);
		answerPanel.setLayout(new CardLayout(0, 0));
		
		MultipleChoicePanel multipleChoicePanel = new MultipleChoicePanel();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) multipleChoicePanel.getLayout();
		gridBagLayout_1.columnWeights = new double[]{1.0};
		answerPanel.add(multipleChoicePanel, "Multiple Choice");
		multipleChoicePanelController = new MultipleChoicePanelController(multipleChoicePanel);
		multipleChoicePanel.jsp.setPreferredSize(new Dimension(answerPanel.getWidth(),answerPanel.getHeight()));
		
		JPanel freeTextPanel = new JPanel();
		answerPanel.add(freeTextPanel, "Free Text");
		GridBagLayout gbl_freeTextPanel = new GridBagLayout();
		gbl_freeTextPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_freeTextPanel.rowHeights = new int[]{93, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_freeTextPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_freeTextPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		freeTextPanel.setLayout(gbl_freeTextPanel);
		
		JLabel lblEnterText = new JLabel("Enter text:");
		GridBagConstraints gbc_lblEnterText = new GridBagConstraints();
		gbc_lblEnterText.anchor = GridBagConstraints.NORTH;
		gbc_lblEnterText.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterText.gridx = 0;
		gbc_lblEnterText.gridy = 0;
		freeTextPanel.add(lblEnterText, gbc_lblEnterText);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		freeTextPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaA = new JTextArea();
		scrollPane_1.setViewportView(textAreaA);
		
		JPanel freeDrawPanel = new JPanel();
		answerPanel.add(freeDrawPanel, "Free Draw");
		GridBagLayout gbl_freeDrawPanel = new GridBagLayout();
		gbl_freeDrawPanel.columnWidths = new int[]{111, 85, 0, 0, 0, 0, 0};
		gbl_freeDrawPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0, 0};
		gbl_freeDrawPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_freeDrawPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		freeDrawPanel.setLayout(gbl_freeDrawPanel);
		
		JLabel ansBrowseLbl = new JLabel("Browse an image:");
		GridBagConstraints gbc_ansBrowseLbl = new GridBagConstraints();
		gbc_ansBrowseLbl.insets = new Insets(0, 0, 5, 5);
		gbc_ansBrowseLbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_ansBrowseLbl.gridx = 1;
		gbc_ansBrowseLbl.gridy = 1;
		freeDrawPanel.add(ansBrowseLbl, gbc_ansBrowseLbl);
		
		ansBrowseBtn = new JButton("Browse..");
		GridBagConstraints gbc_btnBrowseBtn = new GridBagConstraints();
		gbc_btnBrowseBtn.fill = GridBagConstraints.BOTH;
		gbc_btnBrowseBtn.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseBtn.gridx = 2;
		gbc_btnBrowseBtn.gridy = 1;
		freeDrawPanel.add(ansBrowseBtn, gbc_btnBrowseBtn);
		
		qFileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		qFileChooser.setFileFilter(extensionFilter);
		qFileChooser.setAcceptAllFileFilterUsed(false);
		aFileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		aFileChooser.setFileFilter(extensionFilter);
		aFileChooser.setAcceptAllFileFilterUsed(false);
		
		qDataPanel.setOpaque(false);
		freeDrawPanel.setOpaque(false);
		
		btnRemoveAnswerImage = new JButton("Remove Image");
		GridBagConstraints gbc_btnRemoveAnswerImage = new GridBagConstraints();
		gbc_btnRemoveAnswerImage.anchor = GridBagConstraints.WEST;
		gbc_btnRemoveAnswerImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveAnswerImage.gridx = 3;
		gbc_btnRemoveAnswerImage.gridy = 1;
		btnRemoveAnswerImage.setVisible(false);
		freeDrawPanel.add(btnRemoveAnswerImage, gbc_btnRemoveAnswerImage);
		
		btnViewAnswerImage = new JButton("View Image");
		GridBagConstraints gbc_btnViewAnswerImage = new GridBagConstraints();
		gbc_btnViewAnswerImage.anchor = GridBagConstraints.NORTH;
		gbc_btnViewAnswerImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewAnswerImage.gridx = 2;
		gbc_btnViewAnswerImage.gridy = 2;
		btnViewAnswerImage.setVisible(false);
		freeDrawPanel.add(btnViewAnswerImage, gbc_btnViewAnswerImage);
		freeTextPanel.setOpaque(false);
		answerPanel.setOpaque(false);
		
		listenChkBox = new JCheckBox("Enable listening");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTH;
		gbc_chckbxNewCheckBox.gridwidth = 2;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 4;
		gbc_chckbxNewCheckBox.gridy = 1;
		qDataPanel.add(listenChkBox, gbc_chckbxNewCheckBox);
		listenChkBox.setOpaque(false);
		
		JLabel qBrowseAnImage = new JLabel("Browse an image:");
		GridBagConstraints gbc_qBrowseAnImage = new GridBagConstraints();
		gbc_qBrowseAnImage.anchor = GridBagConstraints.NORTHWEST;
		gbc_qBrowseAnImage.insets = new Insets(0, 5, 5, 5);
		gbc_qBrowseAnImage.gridx = 0;
		gbc_qBrowseAnImage.gridy = 2;
		qDataPanel.add(qBrowseAnImage, gbc_qBrowseAnImage);
		
		qbrowseBtn = new JButton("Browse..");
		GridBagConstraints gbc_qBrowseBtn = new GridBagConstraints();
		gbc_qBrowseBtn.anchor = GridBagConstraints.NORTH;
		gbc_qBrowseBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_qBrowseBtn.insets = new Insets(0, 0, 5, 5);
		gbc_qBrowseBtn.gridx = 1;
		gbc_qBrowseBtn.gridy = 2;
		qDataPanel.add(qbrowseBtn, gbc_qBrowseBtn);
		
		qImage = new JButton();
		qImage.setText("View Image");
		GridBagConstraints gbc_qImage = new GridBagConstraints();
		gbc_qImage.anchor = GridBagConstraints.WEST;
		gbc_qImage.insets = new Insets(0, 0, 5, 5);
		gbc_qImage.gridx = 1;
		gbc_qImage.gridy = 4;
		qImage.setVisible(false);
		
		btnRemoveQuestionImage = new JButton("Remove Image");
		GridBagConstraints gbc_btnRemoveQuestionImage = new GridBagConstraints();
		gbc_btnRemoveQuestionImage.anchor = GridBagConstraints.WEST;
		gbc_btnRemoveQuestionImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveQuestionImage.gridx = 2;
		gbc_btnRemoveQuestionImage.gridy = 2;
		btnRemoveQuestionImage.setVisible(false);
		qDataPanel.add(btnRemoveQuestionImage, gbc_btnRemoveQuestionImage);
		
		chckbxHideQuestion = new JCheckBox("Hide question");
		chckbxHideQuestion.setVisible(false);
		GridBagConstraints gbc_chckbxHideQuestion = new GridBagConstraints();
		gbc_chckbxHideQuestion.gridwidth = 2;
		gbc_chckbxHideQuestion.anchor = GridBagConstraints.WEST;
		gbc_chckbxHideQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxHideQuestion.gridx = 4;
		gbc_chckbxHideQuestion.gridy = 2;
		qDataPanel.add(chckbxHideQuestion, gbc_chckbxHideQuestion);
		chckbxHideQuestion.setOpaque(false);
		qDataPanel.add(qImage, gbc_qImage);
		
		
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
	public JPanel getqDataPanel() {
		return qDataPanel;
	}
	public void setqDataPanel(JPanel qDataPanel) {
		this.qDataPanel = qDataPanel;
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
	public JPanel getQuestionDataPanel() {
		return qDataPanel;
	}
	
}
