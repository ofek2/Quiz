package Views;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.Serializable;

public class MultipleChoicePanel extends JPanel implements Serializable {

	public JPanel panel;
	public JScrollPane jsp;

	public MultipleChoicePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 444, 0 };
		gridBagLayout.rowHeights = new int[] { 229, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		jsp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.fill = GridBagConstraints.BOTH;

		gbc_jsp.gridx = 0;
		gbc_jsp.gridy = 0;
		add(jsp, gbc_jsp);
		JLabel choicesLbl = new JLabel("Choices:");
		choicesLbl.setAlignmentX(LEFT_ALIGNMENT);
		choicesLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		choicesLbl.setOpaque(false);
		panel.setOpaque(false);
		panel.add(choicesLbl);
		
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		setOpaque(false);
	}
}
