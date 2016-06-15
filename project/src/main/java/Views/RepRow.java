package Views;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Class RepRow.
 * This class is a single row in the reports table.
 */
public class RepRow extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The row items. */
	private ArrayList<Object> rowItems;
	
	/** The row index. */
	private int rowIndex;
	
	/**
	 * Create the panel.
	 *
	 * @param rowItems the row items
	 * @param rowIndex the row index
	 */
	public RepRow(ArrayList<Object> rowItems,int rowIndex) {
		this.rowItems = rowItems;
		this.rowIndex = rowIndex;
		
		setLayout(new GridLayout(1, rowItems.size(), 0, 0));
		setMinimumSize(new Dimension(10000, 30));
		setMaximumSize(new Dimension(10000, 40));
		for (Object object : rowItems) {
			((JComponent) object).setBorder(new LineBorder(new Color(0, 0, 0)));
		
			if(object instanceof JLabel)
			((JLabel)object).setHorizontalAlignment(SwingConstants.CENTER);
			
			add((Component) object);
		}
	}
	
	/**
	 * Adds the listener.
	 *
	 * @param index the index
	 * @param listener the listener
	 */
	public void addListener(int index,ActionListener listener)
	{
		if(rowItems.get(index) instanceof JButton)
		{
			((JButton)rowItems.get(index)).addActionListener(listener);
		}
	}
	
	/**
	 * Gets the row items.
	 *
	 * @return the row items
	 */
	public ArrayList<Object> getRowItems() {
		return rowItems;
	}
	
	/**
	 * Sets the row items.
	 *
	 * @param rowItems the new row items
	 */
	public void setRowItems(ArrayList<Object> rowItems) {
		this.rowItems = rowItems;
	}
	
	/**
	 * Gets the row index.
	 *
	 * @return the row index
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	
	/**
	 * Sets the row index.
	 *
	 * @param rowIndex the new row index
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
}
