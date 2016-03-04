package Views;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.MainFrameController;
import Controllers.ReportsController;
import project.GradesDistributionGraph;

public class CTable extends JPanel{
	private JButton [] titleButtons;
	private ArrayList<RepRow> rows;
	public CTable(JButton [] titleButtons)
	{
		this.rows = new ArrayList<RepRow>();
		this.titleButtons = titleButtons;
		initiatePanel();
	}
	private void initiatePanel() {
		// TODO Auto-generated method stub
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
	@Override
	public Component add(Component comp) {
		super.add(comp);
		rows.add((RepRow) comp);
		return comp;
	};
	class titleButtonListener implements ActionListener
	{
		
		private int quizCoulmn;
		private ArrayList<String> quizScores;
		public titleButtonListener(int quizCoulmn) {
			super();
			this.quizCoulmn = quizCoulmn;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println("shitty");
			quizScores = new ArrayList<String>();
			for (int i = 1; i < rows.size(); i++) {
				quizScores.add(((JLabel)rows.get(i).getRowItems().get(quizCoulmn)).getText());
			}
			GradesDistributionGraph gradesDistributionGraph = 
					new GradesDistributionGraph(quizScores, ReportsController.view);
			MainFrameController.view.changeContentPane(gradesDistributionGraph);
//			for (int i = 0; i < quizScores.size(); i++) {
//				System.out.println(quizScores.get(i));
//			}
			
		}
		
	}
	public JButton[] getTitleButtons() {
		return titleButtons;
	}
	public void setTitleButtons(JButton[] titleButtons) {
		this.titleButtons = titleButtons;
	}
	public ArrayList<RepRow> getRows() {
		return rows;
	}
	public void setRows(ArrayList<RepRow> rows) {
		this.rows = rows;
	}
	public void removeAllItems()
	{
		for (int i = 0; i < rows.size(); i++) {
			remove((Component)rows.get(i));
		}
		
	}
	
}
