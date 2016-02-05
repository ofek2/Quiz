package Views;
import javax.swing.JMenuBar;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Choice;
import java.awt.List;
import java.awt.event.ActionListener;

public class GradingWindowView extends ViewPanel {
	private JMenu mnFile;

	/**
	 * Create the panel.
	 */
	public GradingWindowView() {
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 30);
		add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSend = new JMenuItem("Send");
		mnFile.add(mntmSend);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

	}
	public void addFileListeners(ActionListener[] listener)
	{
		for (int i = 0; i < listener.length; i++) {
			mnFile.getItem(i).addActionListener(listener[i]);
		}
	}
}
