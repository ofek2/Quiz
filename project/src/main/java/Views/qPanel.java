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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.MultipleChoicePanelController;
import Controllers.qPanelController;
import Entities.Constants;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.Box;

/**
 * The Class qPanel.
 * This class holds a single question.
 * This class is a boundary controlled by {@link qPanelController}.
 */
public class qPanel extends JPanel implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The question number. */
	private int questionNumber;
	
	/** The question label. */
	private JLabel questionLbl;
	
	/** The label score. */
	private JLabel lblScore;
	
	/** The button remove. */
	private JButton btnRemove;
	
	/** The score text field. */
	private JTextField scoreTextField;
	
	/** The question browse button. */
	private JButton qbrowseBtn;
	
	/** The question file chooser. */
	private transient JFileChooser qFileChooser;
	
	/** The answer text area . */
	private JTextArea textAreaA;
	
	/** The listen check box. */
	private JCheckBox listenChkBox;
	
	/** The hide question check box . */
	private JCheckBox chckbxHideQuestion;
	
	/** The answer type combobox. */
	private JComboBox<String> answerTypeCb;
	
	/** The answer file chooser. */
	private transient JFileChooser aFileChooser;
	
	/** The answer panel. */
	private JPanel answerPanel;
	
	/** The choices label. */
	private JLabel choicesLbl;
	
	/** The answer browse button. */
	private JButton ansBrowseBtn;
	
	/** The extension filter. */
	private transient FileNameExtensionFilter extensionFilter;
	
	/** The multiple choice panel controller. */
	private MultipleChoicePanelController multipleChoicePanelController;
	
	/** The Constant width. */
	private final static int width=Constants.FRAME_WIDTH-80;

	/** The Constant height. */
	private final static int height=600;
	
	/** The Constant qPanelsGap. */
	private static final int qPanelsGap = 10;
	
	/** The text area q. */
	private JTextArea textAreaQ;
	
	/** The question image. */
	private JButton qImage;
	
	/** The button remove question image. */
	private JButton btnRemoveQuestionImage;
	
	/** The button remove answer image. */
	private JButton btnRemoveAnswerImage;
	
	/** The button view answer image. */
	private JButton btnViewAnswerImage;
	
	/** The head panel. */
	private JPanel headPanel;
	
	/** The question part panel. */
	private JPanel questionPartPanel;
	
	/** The text panel. */
	private JPanel textPanel;
	
	/** The listening panel. */
	private JPanel listeningPanel;
	
	/** The answer part panel. */
	private JPanel answerPartPanel;
	
	/** The answer title panel. */
	private JPanel answerTitlePanel;
	
	/** The panel_3. */
	private JPanel panel_3;
	
	/** The label. */
	private JLabel label;
	
	/** The free draw panel. */
	private JPanel freeDrawPanel;
	
	/** The image buttons panel. */
	private JPanel imageButtonsPanel;
	
	/** The answer image buttons panel. */
	private JPanel answerImageButtonsPanel;
	
	/** The panel_2. */
	private JPanel panel_2;
	
	/** The panel_4. */
	private JPanel panel_4;
	
	/** The panel. */
	private JPanel panel;
	
	/** The main panel. */
	public JPanel mainPanel;
	
	/** The panel_1. */
	private JPanel panel_1;
	
	/** The panel_5. */
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

	
	/**
	 * Gets the answer image buttons panel.
	 *
	 * @return the answer image buttons panel
	 */
	public JPanel getAnswerImageButtonsPanel() {
		return answerImageButtonsPanel;
	}


	/**
	 * Sets the answer image buttons panel.
	 *
	 * @param answerImageButtonsPanel the new answer image buttons panel
	 */
	public void setAnswerImageButtonsPanel(JPanel answerImageButtonsPanel) {
		this.answerImageButtonsPanel = answerImageButtonsPanel;
	}


	/**
	 * Gets the image buttons panel.
	 *
	 * @return the image buttons panel
	 */
	public JPanel getImageButtonsPanel() {
		return imageButtonsPanel;
	}


	/**
	 * Sets the image buttons panel.
	 *
	 * @param imageButtonsPanel the new image buttons panel
	 */
	public void setImageButtonsPanel(JPanel imageButtonsPanel) {
		this.imageButtonsPanel = imageButtonsPanel;
	}


	/**
	 * Gets the lbl score.
	 *
	 * @return the lbl score
	 */
	public JLabel getLblScore() {
		return lblScore;
	}
	
	/**
	 * Sets the lbl score.
	 *
	 * @param lblScore the new lbl score
	 */
	public void setLblScore(JLabel lblScore) {
		this.lblScore = lblScore;
	}
	
	/**
	 * Gets the btn remove.
	 *
	 * @return the btn remove
	 */
	public JButton getBtnRemove() {
		return btnRemove;
	}
	
	/**
	 * Sets the btn remove.
	 *
	 * @param btnRemove the new btn remove
	 */
	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}
	
	/**
	 * Gets the qbrowse btn.
	 *
	 * @return the qbrowse btn
	 */
	public JButton getQbrowseBtn() {
		return qbrowseBtn;
	}
	
	/**
	 * Sets the qbrowse btn.
	 *
	 * @param qbrowseBtn the new qbrowse btn
	 */
	public void setQbrowseBtn(JButton qbrowseBtn) {
		this.qbrowseBtn = qbrowseBtn;
	}
	
	/**
	 * Gets the removes the question image btn.
	 *
	 * @return the removes the question image btn
	 */
	public JButton getRemoveQuestionImageBtn() {
		return btnRemoveQuestionImage;
	}
	
	/**
	 * Gets the removes the answer image btn.
	 *
	 * @return the removes the answer image btn
	 */
	public JButton getRemoveAnswerImageBtn() {
		return btnRemoveAnswerImage;
	}
	
	/**
	 * Gets the q file chooser.
	 *
	 * @return the q file chooser
	 */
	public JFileChooser getqFileChooser() {
		return qFileChooser;
	}
	
	/**
	 * Sets the q file chooser.
	 *
	 * @param qFileChooser the new q file chooser
	 */
	public void setqFileChooser(JFileChooser qFileChooser) {
		this.qFileChooser = qFileChooser;
	}
	
	/**
	 * Gets the text area q.
	 *
	 * @return the text area q
	 */
	public JTextArea getTextAreaQ() {
		return textAreaQ;
	}
	
	/**
	 * Sets the text area q.
	 *
	 * @param textAreaQ the new text area q
	 */
	public void setTextAreaQ(JTextArea textAreaQ) {
		this.textAreaQ = textAreaQ;
	}
	
	/**
	 * Gets the text area a.
	 *
	 * @return the text area a
	 */
	public JTextArea getTextAreaA() {
		return textAreaA;
	}
	
	/**
	 * Sets the text area a.
	 *
	 * @param textAreaA the new text area a
	 */
	public void setTextAreaA(JTextArea textAreaA) {
		this.textAreaA = textAreaA;
	}
	
	/**
	 * Gets the answer type cb.
	 *
	 * @return the answer type cb
	 */
	public JComboBox<String> getAnswerTypeCb() {
		return answerTypeCb;
	}
	
	/**
	 * Sets the answer type cb.
	 *
	 * @param answerTypeCb the new answer type cb
	 */
	public void setAnswerTypeCb(JComboBox<String> answerTypeCb) {
		this.answerTypeCb = answerTypeCb;
	}
	
	/**
	 * Gets the a file chooser.
	 *
	 * @return the a file chooser
	 */
	public JFileChooser getaFileChooser() {
		return aFileChooser;
	}
	
	/**
	 * Sets the a file chooser.
	 *
	 * @param aFileChooser the new a file chooser
	 */
	public void setaFileChooser(JFileChooser aFileChooser) {
		this.aFileChooser = aFileChooser;
	}
	
	/**
	 * Gets the choices lbl.
	 *
	 * @return the choices lbl
	 */
	public JLabel getChoicesLbl() {
		return choicesLbl;
	}
	
	/**
	 * Sets the choices lbl.
	 *
	 * @param choicesLbl the new choices lbl
	 */
	public void setChoicesLbl(JLabel choicesLbl) {
		this.choicesLbl = choicesLbl;
	}
	
	/**
	 * Gets the ans browse btn.
	 *
	 * @return the ans browse btn
	 */
	public JButton getAnsBrowseBtn() {
		return ansBrowseBtn;
	}
	
	/**
	 * Sets the ans browse btn.
	 *
	 * @param ansBrowseBtn the new ans browse btn
	 */
	public void setAnsBrowseBtn(JButton ansBrowseBtn) {
		this.ansBrowseBtn = ansBrowseBtn;
	}
	
	/**
	 * Gets the extension filter.
	 *
	 * @return the extension filter
	 */
	public FileNameExtensionFilter getExtensionFilter() {
		return extensionFilter;
	}
	
	/**
	 * Sets the extension filter.
	 *
	 * @param extensionFilter the new extension filter
	 */
	public void setExtensionFilter(FileNameExtensionFilter extensionFilter) {
		this.extensionFilter = extensionFilter;
	}
	
	/**
	 * Gets the multiple choice panel controller.
	 *
	 * @return the multiple choice panel controller
	 */
	public MultipleChoicePanelController getMultipleChoicePanelController() {
		return multipleChoicePanelController;
	}
	
	/**
	 * Sets the multiple choice panel controller.
	 *
	 * @param multipleChoicePanelController the new multiple choice panel controller
	 */
	public void setMultipleChoicePanelController(MultipleChoicePanelController multipleChoicePanelController) {
		this.multipleChoicePanelController = multipleChoicePanelController;
	}
	
	/**
	 * Gets the score text field.
	 *
	 * @return the score text field
	 */
	public JTextField getScoreTextField() {
		return scoreTextField;
	}
	
	/**
	 * Sets the score text field.
	 *
	 * @param textField the new score text field
	 */
	public void setScoreTextField(JTextField textField) {
		this.scoreTextField = textField;
	}
	
	/**
	 * Gets the answer panel.
	 *
	 * @return the answer panel
	 */
	public JPanel getAnswerPanel() {
		return answerPanel;
	}
	
	/**
	 * Sets the answer panel.
	 *
	 * @param answerPanel the new answer panel
	 */
	public void setAnswerPanel(JPanel answerPanel) {
		this.answerPanel = answerPanel;
	}
	
	/**
	 * Gets the chckbx hide question.
	 *
	 * @return the chckbx hide question
	 */
	public JCheckBox getChckbxHideQuestion() {
		return chckbxHideQuestion;
	}
	
	/**
	 * Sets the chckbx hide question.
	 *
	 * @param chckbxHideQuestion the new chckbx hide question
	 */
	public void setChckbxHideQuestion(JCheckBox chckbxHideQuestion) {
		this.chckbxHideQuestion = chckbxHideQuestion;
	}
	
	/**
	 * Q browse btn add listener.
	 *
	 * @param listener the listener
	 */
	public void qBrowseBtnAddListener(ActionListener listener)
	{
		qbrowseBtn.addActionListener(listener);
	}
	
	/**
	 * Removes the btn add listener.
	 *
	 * @param listener the listener
	 */
	public void removeBtnAddListener(ActionListener listener)
	{
		btnRemove.addActionListener(listener);
	}
	
	/**
	 * A type c badd item listener.
	 *
	 * @param listener the listener
	 */
	public void aTypeCBaddItemListener(ItemListener listener)
	{
		answerTypeCb.addItemListener(listener);
	}
	
	/**
	 * Listen chk box add listner.
	 *
	 * @param listener the listener
	 */
	public void listenChkBoxAddListner(ActionListener listener)
	{
		listenChkBox.addActionListener(listener);
	}
	
	/**
	 * Ans browse btn add listener.
	 *
	 * @param listener the listener
	 */
	public void ansBrowseBtnAddListener(ActionListener listener)
	{
		ansBrowseBtn.addActionListener(listener);
	}
	
	/**
	 * Q image btn add listener.
	 *
	 * @param listener the listener
	 */
	public void qImageBtnAddListener(ActionListener listener)
	{
		qImage.addActionListener(listener);
	}
	
	/**
	 * View answer image btn add listener.
	 *
	 * @param listener the listener
	 */
	public void viewAnswerImageBtnAddListener(ActionListener listener)
	{
		btnViewAnswerImage.addActionListener(listener);
	}
	
	/**
	 * Removes the question image btn add listener.
	 *
	 * @param listener the listener
	 */
	public void removeQuestionImageBtnAddListener(ActionListener listener)
	{
		btnRemoveQuestionImage.addActionListener(listener);
	}
	
	/**
	 * Removes the answer image btn add listener.
	 *
	 * @param listener the listener
	 */
	public void removeAnswerImageBtnAddListener(ActionListener listener)
	{
		btnRemoveAnswerImage.addActionListener(listener);
	}
	
	/**
	 * Gets the listen chk box.
	 *
	 * @return the listen chk box
	 */
	public JCheckBox getListenChkBox() {
		return listenChkBox;
	}
	
	/**
	 * Sets the listen chk box.
	 *
	 * @param listenChkBox the new listen chk box
	 */
	public void setListenChkBox(JCheckBox listenChkBox) {
		this.listenChkBox = listenChkBox;
	}
	
	/**
	 * Gets the question lbl.
	 *
	 * @return the question lbl
	 */
	public JLabel getQuestionLbl() {
		return questionLbl;
	}
	
	/**
	 * Gets the q image.
	 *
	 * @return the q image
	 */
	public JButton getqImage() {
		return qImage;
	}
	
	/**
	 * Gets the btn view answer image.
	 *
	 * @return the btn view answer image
	 */
	public JButton getbtnViewAnswerImage() {
		return btnViewAnswerImage;
	}
	
	/**
	 * Sets the q image.
	 *
	 * @param qImage the new q image
	 */
	public void setqImage(JButton qImage) {
		this.qImage = qImage;
	}

	/**
	 * Gets the question image broswe button.
	 *
	 * @return the question image broswe button
	 */
	public JButton getQuestionImageBrosweButton() {
		return qbrowseBtn;
	}
	
	/**
	 * Sets the question lbl.
	 *
	 * @param questionLbl the new question lbl
	 */
	public void setQuestionLbl(JLabel questionLbl) {
		this.questionLbl = questionLbl;
	}
	
	/**
	 * Gets the question number.
	 *
	 * @return the question number
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	
	/**
	 * Sets the question number.
	 *
	 * @param questionNumber the new question number
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	/**
	 * Gets the question file chooser.
	 *
	 * @return the question file chooser
	 */
	public JFileChooser getQuestionFileChooser() {
		return qFileChooser;
	}
	
	/**
	 * Sets the question file chooser.
	 *
	 * @param fileChooser the new question file chooser
	 */
	public void setQuestionFileChooser(JFileChooser fileChooser) {
		this.qFileChooser= fileChooser;
	}
	
	/**
	 * Gets the answer file chooser.
	 *
	 * @return the answer file chooser
	 */
	public JFileChooser getAnswerFileChooser() {
		return aFileChooser;
	}
	
	/**
	 * Sets the answer file chooser.
	 *
	 * @param fileChooser the new answer file chooser
	 */
	public void setAnswerFileChooser(JFileChooser fileChooser) {
		this.aFileChooser= fileChooser;
	}
	
	/**
	 * Adds the text area q key listener.
	 *
	 * @param listener the listener
	 */
	public void addTextAreaQKeyListener(KeyListener listener){
		textAreaQ.addKeyListener(listener);
	}
	
	/**
	 * Adds the text area a key listener.
	 *
	 * @param listener the listener
	 */
	public void addTextAreaAKeyListener(KeyListener listener){
		textAreaA.addKeyListener(listener);
	}
	
	/**
	 * Adds the score text field key listener.
	 *
	 * @param listener the listener
	 */
	public void addScoreTextFieldKeyListener(KeyListener listener){
		scoreTextField.addKeyListener(listener);
	}
	
	/**
	 * Adds the chckbx hide question listener.
	 *
	 * @param listener the listener
	 */
	public void addChckbxHideQuestionListener(ActionListener listener){
		chckbxHideQuestion.addActionListener(listener);
	}
	
	/**
	 * Initialize j file choosers.
	 */
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
