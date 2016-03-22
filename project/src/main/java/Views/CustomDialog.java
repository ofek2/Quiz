package Views;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomDialog extends JDialog{
	private JLabel label;
	private JPanel panel;
	public CustomDialog()
	{
		super();
		setSize(400,200);
		setLocationRelativeTo(null);
		label = new JLabel();
		panel = new JPanel();
		panel.add(label);
		getContentPane().add(panel);
		setVisible(true);
	}
	public CustomDialog(String text)
	{
		super();
		setSize(400,200);
		setLocationRelativeTo(null);
		label = new JLabel(text);
		panel = new JPanel();
		panel.add(label);
		getContentPane().add(panel);
		setVisible(true);
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public void setLabelText(String text) {
		label.setText(text);
	}
}
