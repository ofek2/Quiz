package Views;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.MainFrameController;
import Controllers.sendMails;

/**
 * The Class CustomDialog.
 * This class is used for creating a dialog with a label.
 * Used in {@link sendMails} for presenting how many mails were sent already.
 * Also used in several Dropbox actions for presenting some progress.
 */
public class CustomDialog extends JDialog{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label. */
	private JLabel label;
	
	/** The panel. */
	private JPanel panel;
	
	/**
	 * Instantiates a new custom dialog.
	 */
	public CustomDialog()
	{
		super(MainFrameController.view);
		setSize(400,200);
		setLocationRelativeTo(null);
		label = new JLabel();
		panel = new JPanel();
		panel.add(label);
		getContentPane().add(panel);
		
	}
	
	/**
	 * Instantiates a new custom dialog.
	 *
	 * @param text the text
	 */
	public CustomDialog(String text)
	{
		super();
		setSize(400,200);
		setLocationRelativeTo(null);
		label = new JLabel(text);
		panel = new JPanel();
		panel.add(label);
		getContentPane().add(panel);
		
	}
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public JLabel getLabel() {
		return label;
	}
	
	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Sets the panel.
	 *
	 * @param panel the new panel
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * Sets the label text.
	 *
	 * @param text the new label text
	 */
	public void setLabelText(String text) {
		label.setText(text);
	}
}
