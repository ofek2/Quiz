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

/**
 * The Class GradingWindowView.
 * This class is used for grading the students.
 */
public class GradingWindowView extends ViewPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The mn file. */
	private JMenu mnFile;
	
	/** The table. */
	public static JTable table;
	
	/** The scroll pane. */
	public static JScrollPane scrollPane;
	
	/** The table panel. */
	public JPanel tablePanel;
	
	/** The mn help menu. */
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
		add(scrollPane);
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		JPanel titlePanel = new JPanel();

		titlePanel.setLayout(new GridLayout(0, 3, 0, 0));
		titlePanel.setMaximumSize(new Dimension(10000, 60));
		titlePanel.setBackground(Color.white);
		
		JLabel lblStudentid = new JLabel("Student Id");
		lblStudentid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblStudentid.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblStudentid);

		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblGrade);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblOptions);
		tablePanel.add(titlePanel);
		scrollPane.setViewportView(tablePanel);

	}
	
	/**
	 * Adds the file listeners.
	 *
	 * @param listener the listener
	 */
	public void addFileListeners(ActionListener[] listener)
	{
		for (int i = 0; i < listener.length; i++) {
			mnFile.getItem(i).addActionListener(listener[i]);
		}
	}
	
	/**
	 * Gets the mn file.
	 *
	 * @return the mn file
	 */
	public JMenu getMnFile() {
		return mnFile;
	}
	
	/**
	 * Sets the mn file.
	 *
	 * @param mnFile the new mn file
	 */
	public void setMnFile(JMenu mnFile) {
		this.mnFile = mnFile;
	}
	
	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * Sets the table.
	 *
	 * @param customTable the new table
	 */
	public static void setTable(JTable customTable) {
		table = customTable;
		scrollPane.setViewportView(table);
	}
	
	/**
	 * Adds the help action listener.
	 *
	 * @param listener the listener
	 */
	public void addHelpActionListener(ActionListener listener)
	{
		mnHelpMenu.getItem(0).addActionListener(listener);
	}
}
