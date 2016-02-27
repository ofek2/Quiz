package Views;
import javax.swing.JMenuBar;
import Controllers.MainFrameController;
import project.CustomTable;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class GradingWindowView extends ViewPanel {
	private JMenu mnFile;
	public static JTable table;
	public static JScrollPane scrollPane;

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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(MainFrameController.view.getContentPane().getWidth()/4,70, MainFrameController.view.getContentPane().getWidth()/2, MainFrameController.view.getContentPane().getHeight()-100);
		add(scrollPane);
		
//		table = new CustomTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//			},
//			new String[] {
//				"Student Id", "Student Name", "Grade", "Options"
//			}
//		));
		scrollPane.setViewportView(table);

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
	
}
