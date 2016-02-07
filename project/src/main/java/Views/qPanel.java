package Views;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;





import project.checkBoxFieldPanel;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.BoxLayout;

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
	private JPanel multipleChoicePanel;
	/**
	 * Create the panel.
	 */
	public qPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setPreferredSize(new Dimension(725, 300));
		setMaximumSize(new Dimension(725,300));
		
		setLayout(null);
		
		questionLbl = new JLabel("Question "+questionNumber);
		questionLbl.setBounds(8, 8, 95, 27);
		questionLbl.setFont(new Font("Serif", Font.BOLD, 20));
		add(questionLbl);
		
		btnRemove = new JButton("X");
		btnRemove.setFont(new Font("Serif", Font.BOLD, 10));
		btnRemove.setBounds(668, 9, 47, 34);
		add(btnRemove);
		
		JLabel enterQuestionLbl = new JLabel("The question:");
		enterQuestionLbl.setBounds(8, 68, 95, 18);
		enterQuestionLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(enterQuestionLbl);
		
		
		JLabel questionTypeLbl = new JLabel("Answer type:");
		questionTypeLbl.setBounds(401, 69, 95, 18);
		questionTypeLbl.setFont(new Font("Serif", Font.PLAIN, 13));
		add(questionTypeLbl);
		
		
		answerTypeCb = new JComboBox<String>();
		answerTypeCb.setBounds(527, 68, 160, 20);
		answerTypeCb.addItem("Multiple Choice");
		answerTypeCb.addItem("Free text");
		answerTypeCb.addItem("Free drawing");
		answerTypeCb.setSelectedIndex(0);
		answerTypeCb.setMaximumSize(answerTypeCb.getPreferredSize());
		add(answerTypeCb);
		setMinimumSize(new Dimension(800, 250));
		
		setAlignmentY(0.0f);
		
		lblScore = new JLabel("Score:");
		lblScore.setBounds(134, 18, 52, 14);
		add(lblScore);
		
		scoreField = new JTextField();
		scoreField.setBounds(179, 15, 52, 20);
		add(scoreField);
		scoreField.setColumns(10);
		
		qDataPanel = new JPanel();
		qDataPanel.setBounds(8, 96, 376, 191);
		add(qDataPanel);
		qDataPanel.setLayout(null);
		qDataPanel.setOpaque(false);
		
		JLabel qLabel = new JLabel("Enter question:");
		qLabel.setBounds(10, 14, 117, 14);
		qDataPanel.add(qLabel);
		
		listenChkBox = new JCheckBox("Enable listening");
		listenChkBox.setFont(new Font("Tahoma", Font.PLAIN, 9));
		listenChkBox.setBounds(287, 10, 97, 23);
		listenChkBox.setOpaque(false);
		qDataPanel.add(listenChkBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 11, 175, 89);
		qDataPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		chckbxHideQuestion = new JCheckBox("Hide question");
		chckbxHideQuestion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxHideQuestion.setOpaque(false);
		chckbxHideQuestion.setVisible(false);
		chckbxHideQuestion.setBounds(287, 36, 97, 23);
		qDataPanel.add(chckbxHideQuestion);
		fileChooser = new JFileChooser();
		extensionFilter = new FileNameExtensionFilter("Img","jpg","gif","png");
		fileChooser.setFileFilter(extensionFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		browseBtn = new JButton("Browse..");
		browseBtn.setBounds(131, 107, 89, 23);
		qDataPanel.add(browseBtn);
		
		JLabel browseLabel = new JLabel("Browse an image:");
		browseLabel.setBounds(10, 111, 104, 14);
		qDataPanel.add(browseLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(389, 68, 2, 219);
		add(separator);
		
		answerPanel = new JPanel();
		answerPanel.setBounds(398, 98, 317, 191);
		add(answerPanel);
		answerPanel.setLayout(new CardLayout(0, 0));
		
		multipleChoicePanel = new JPanel();
		answerPanel.add(multipleChoicePanel, "name_739422495432258");
		multipleChoicePanel.setLayout(new BoxLayout(multipleChoicePanel, BoxLayout.X_AXIS));
		multipleChoicePanel.add(new checkBoxFieldPanel());
	
	
		
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
}
