package Views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.MainFrameController;
import Controllers.ReportsController;

/**
 * The Class CTable.
 * This class is used for creating the table in {@link ReportsView}.
 */
public class CTable extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The title buttons. */
	private JButton [] titleButtons;
	
	/** The rows. */
	private ArrayList<RepRow> rows;
	
	/**
	 * Instantiates a new c table.
	 *
	 * @param titleButtons the title buttons
	 */
	public CTable(JButton [] titleButtons)
	{
		this.rows = new ArrayList<RepRow>();
		this.titleButtons = titleButtons;
		initiatePanel();
	}
	
	/**
	 * Initiate panel.
	 */
	private void initiatePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ArrayList<Object> titleRowItems = new ArrayList<Object>();
		titleRowItems.add(new JLabel("Student Id"));
		for (JButton button : titleButtons) {
			titleRowItems.add(button);
		}
		RepRow titleRow = new RepRow(titleRowItems,0);
		for (int i=1;i<titleRow.getRowItems().size();i++)
		{
			titleRow.addListener(i, new titleButtonListener(i));
		}
		
		add(titleRow);
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#add(java.awt.Component)
	 */
	@Override
	public Component add(Component comp) {
		super.add(comp);
		revalidate();
		rows.add((RepRow) comp);
		return comp;
	};
	
	/**
	 * The listener interface for receiving titleButton events.
	 * The class that is interested in processing a titleButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addtitleButtonListener<code> method. When
	 * the titleButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see titleButtonEvent
	 */
	class titleButtonListener implements ActionListener
	{
		
		/** The quiz coulmn. */
		private int quizCoulmn;
		
		/** The quiz scores. */
		private ArrayList<String> quizScores;
		
		/**
		 * Instantiates a new title button listener.
		 *
		 * @param quizCoulmn the quiz coulmn
		 */
		public titleButtonListener(int quizCoulmn) {
			super();
			this.quizCoulmn = quizCoulmn;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			quizScores = new ArrayList<String>();
			for (int i = 1; i < rows.size(); i++) {
				quizScores.add(((JLabel)rows.get(i).getRowItems().get(quizCoulmn)).getText());
			}
			GradesDistributionGraph gradesDistributionGraph = 
					new GradesDistributionGraph(quizScores,((JButton)e.getSource()).getText()+" Graph", ReportsController.view);
			MainFrameController.view.changeContentPane(gradesDistributionGraph);			
		}
		
	}
	
	/**
	 * Gets the title buttons.
	 *
	 * @return the title buttons
	 */
	public JButton[] getTitleButtons() {
		return titleButtons;
	}
	
	/**
	 * Sets the title buttons.
	 *
	 * @param titleButtons the new title buttons
	 */
	public void setTitleButtons(JButton[] titleButtons) {
		this.titleButtons = titleButtons;
	}
	
	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public ArrayList<RepRow> getRows() {
		return rows;
	}
	
	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(ArrayList<RepRow> rows) {
		this.rows = rows;
	}
	
	/**
	 * Removes the all items.
	 */
	public void removeAllItems()
	{
		for (int i = 0; i < rows.size(); i++) {
			remove((Component)rows.get(i));
		}
		
	}
	
}
