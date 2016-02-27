package project;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		
		setModel(new DefaultTableModel(studentsArr,new String[] {"Student Id", "Student Name", "Grade", "Options"}));
	}
}
