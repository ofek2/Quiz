package project;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Views.GradingWindowView;
import Entities.StudentEntity;

public class CustomTable extends JTable{

	public CustomTable()
	{
		super();
	}
	public CustomTable(ArrayList<StudentEntity> students)
	{
		super();
		Object [][] studentsArr = new Object[students.size()][4];
		for(int i=0;i<students.size();i++)
		{
			studentsArr[i][0] = students.get(i).getStudentId(); 
			studentsArr[i][1] = students.get(i).getStudentName();
			studentsArr[i][2] = (String)"Not yet graded";
			studentsArr[i][3] = students.get(i).getStudentName();
		}
		JTable jTable = new JTable(studentsArr,new String[] {"Student Id", "Student Name", "Grade", "Options"});
		jTable.getColumn("Options").setCellRenderer(new ButtonRenderer());
		jTable.getColumn("Options").setCellEditor( new ButtonEditor(new JCheckBox()));
		GradingWindowView.setTable(jTable);
		//		System.out.println(studentsArr[0][0]);
//		getModel().setValueAt(studentsArr[0][0],1,0 );
//		setModel(new DefaultTableModel(studentsArr,new String[] {"Student Id", "Student Name", "Grade", "Options"}));
//		getColumn("Options").setCellRenderer(new ButtonRenderer());
//		getColumn("Options").setCellEditor( new ButtonEditor(new JCheckBox()));
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {

		  public ButtonRenderer() {
		    setOpaque(true);
		  }

		  public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		    if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		    } else {
		      setForeground(table.getForeground());
		      setBackground(UIManager.getColor("Button.background"));
		    }
		    setText((value == null) ? "" : value.toString());
		    return this;
		  }
		}

		/**
		 * @version 1.0 11/09/98
		 */

		class ButtonEditor extends DefaultCellEditor {
		  protected JButton button;

		  private String label;

		  private boolean isPushed;

		  public ButtonEditor(JCheckBox checkBox) {
		    super(checkBox);
		    button = new JButton();
		    button.setOpaque(true);
		    button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        fireEditingStopped();
		      }
		    });
		  }

		  public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {
		    if (isSelected) {
		      button.setForeground(table.getSelectionForeground());
		      button.setBackground(table.getSelectionBackground());
		    } else {
		      button.setForeground(table.getForeground());
		      button.setBackground(table.getBackground());
		    }
		    label = (value == null) ? "" : value.toString();
		    button.setText(label);
		    isPushed = true;
		    return button;
		  }

		  public Object getCellEditorValue() {
		    if (isPushed) {
		      // 
		      // 
		      JOptionPane.showMessageDialog(button, label + ": Ouch!");
		      // System.out.println(label + ": Ouch!");
		    }
		    isPushed = false;
		    return new String(label);
		  }

		  public boolean stopCellEditing() {
		    isPushed = false;
		    return super.stopCellEditing();
		  }

		  protected void fireEditingStopped() {
		    super.fireEditingStopped();
		  }
		}
}
