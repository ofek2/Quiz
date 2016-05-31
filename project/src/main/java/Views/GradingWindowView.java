package Views;
import javax.swing.JMenuBar;
import Controllers.MainFrameController;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class GradingWindowView extends ViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu mnFile;
	public static JTable table;
	public static JScrollPane scrollPane;
	public JPanel tablePanel;
	private JMenu mnHelpMenu;
	/**
	 * Create the panel.
	 */
	public GradingWindowView() {
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSend = new JMenuItem("Send Grades");
		mnFile.add(mntmSend);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separator1.setBackground(Color.LIGHT_GRAY);
		separator1.setMaximumSize(new Dimension(2, 100));
		separator1.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(separator1);
		
		mnHelpMenu = new JMenu("Help");
		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mnHelpMenu.add(mntmHelpContents);
		menuBar.add(mnHelpMenu);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(MainFrameController.view.getContentPane().getWidth()/4,70, MainFrameController.view.getContentPane().getWidth()/2, MainFrameController.view.getContentPane().getHeight()-100);
		//scrollPane.setBounds(50,70,300,250);
		add(scrollPane);
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		JPanel titlePanel = new JPanel();
//		titlePanel.setLayout(new GridLayout(0, 4, 0, 0));
		titlePanel.setLayout(new GridLayout(0, 3, 0, 0));
		titlePanel.setMaximumSize(new Dimension(10000, 60));
		titlePanel.setBackground(Color.white);
		
		JLabel lblStudentid = new JLabel("Student Id");
		lblStudentid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblStudentid.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblStudentid);
		
//		JLabel lblStudentname = new JLabel("Student Name");
//		lblStudentname.setBorder(new LineBorder(new Color(0, 0, 0)));
//		lblStudentname.setHorizontalAlignment(SwingConstants.CENTER);
//		titlePanel.add(lblStudentname);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblGrade);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblOptions);
		tablePanel.add(titlePanel);
//		table = new CustomTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//			},
//			new String[] {
//				"Student Id", "Student Name", "Grade", "Options"
//			}
//		));
		scrollPane.setViewportView(tablePanel);

	}
	public void addFileListeners(ActionListener[] listener)
	{
		for (int i = 0; i < listener.length; i++) {
			mnFile.getItem(i).addActionListener(listener[i]);
		}
	}
	public JMenu getMnFile() {
		return mnFile;
	}
	public void setMnFile(JMenu mnFile) {
		this.mnFile = mnFile;
	}
	public JTable getTable() {
		return table;
	}
	public static void setTable(JTable customTable) {
		table = customTable;
		scrollPane.setViewportView(table);
	}
	public void addHelpActionListener(ActionListener listener)
	{
		mnHelpMenu.getItem(0).addActionListener(listener);
	}
}
