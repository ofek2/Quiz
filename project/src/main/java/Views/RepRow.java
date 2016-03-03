package Views;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RepRow extends JPanel {

	private ArrayList<JComponent> rowItems;
	private int rowIndex;
	/**
	 * Create the panel.
	 */
	public RepRow(ArrayList<JComponent> rowItems,int rowIndex) {
		this.rowItems = rowItems;
		this.rowIndex = rowIndex;
		setLayout(new GridLayout(1, rowItems.size(), 0, 0));
		setMaximumSize(new Dimension(10000, 25));
		for (JComponent object : rowItems) {
			object.setBorder(new LineBorder(new Color(0, 0, 0)));
			if(object instanceof JLabel)
			((JLabel)object).setHorizontalAlignment(SwingConstants.CENTER);
			add(object);
		}
	}
	public void addListener(int index,ActionListener listener)
	{
		if(rowItems.get(index) instanceof JButton)
		{
			((JButton)rowItems.get(index)).addActionListener(listener);
		}
	}
	
	public ArrayList<JComponent> getRowItems() {
		return rowItems;
	}
	public void setRowItems(ArrayList<JComponent> rowItems) {
		this.rowItems = rowItems;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
}
