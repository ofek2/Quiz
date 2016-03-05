package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import project.ObjectFileManager;
import Entities.CourseEntity;
import Entities.StudentEntity;
import Views.CTable;
import Views.RepRow;
import Views.ReportsView;
import Views.ViewPanel;


public class ReportsController {
	public static ReportsView view;
	private Container previousView;
	private ArrayList<String> quizzesNames;
	private ArrayList<Object> quizzesScores;
	private String coursePath;
	private SearchStudent searchStudent;
	private CTable table;
	public ReportsController(ReportsView view, Container previousView) {
		this.view = view;
		this.previousView = previousView;
		view.btnReportsShowGradesAddListener(new produceReports());	
		searchStudent = new SearchStudent();
		view.mntmExitAddListener(new ExitListener());
		view.btnExportExcelFileAddListener(new ExportExcelFileListener());
		//view.table = new CustomTable(view);
	}
	
	class ExitListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel)previousView);
		}
		
	}
	
	class ExportExcelFileListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			  String fileName = coursePath+"/Report.xls";

			  PrintWriter out;
			try {
				FileWriter excelFile = new FileWriter(fileName);
				out = new PrintWriter(excelFile);
				out.print("Student Id"+"\t");
				for (int i = 0; i < quizzesNames.size(); i++) {
					out.print(quizzesNames.get(i)+"\t");
				}
				out.println();
				for (int i = 1; i < table.getRows().size(); i++) {
					for (int j = 0; j < quizzesNames.size()+1; j++) {
						out.print(((JLabel)table.getRows().get(i).getRowItems().get(j)).getText()+"\t");
					}
					out.println();
				}
//				out.println("a,b,c,d");
//				out.println("e,f,g,h");
//				out.println("i,j,k,l");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		}
		
		
	}
	
	class produceReports implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int courseIndexInCoursesArray = CourseEntity.getIndex((String) view.getReportsCourses()
					.getSelectedItem());
			
			try {			
				quizzesNames = new ArrayList<String>();
				coursePath = InitialWindowController.coursesFiles.
						get(courseIndexInCoursesArray).getCourseFolder().getCanonicalPath();
				File quizzes = new File(coursePath+"/Quizzes");
				if(quizzes.listFiles().length>0){
				for (File quizFolder : quizzes.listFiles()) {
					quizzesNames.add(quizFolder.getName());
				}
				quizzesNames.add("AVG");
				loadStudentsScoresToTable(quizzesNames.size(), quizzesNames,"all");
				}
				else
					JOptionPane.showMessageDialog(null
							, "There are no quizzes under this course name, please choose another course"
							, "Alert",
							JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class SearchStudent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			loadStudentsScoresToTable(quizzesNames.size(), quizzesNames,
					(String) view.getReportsStudentsIds().getSelectedItem());
		}
		
	}
	
	public void loadStudentsScoresToTable(int size,ArrayList<String> quizzesNames
			,String searchStudentId) {
		view.setTitleButtons(new JButton[size]);
		JButton[] titleButtons = view.getTitleButtons();
		for (int i = 0; i < titleButtons.length; i++) {
			titleButtons[i] = new JButton(quizzesNames.get(i));
		}
		table = new CTable(titleButtons);
		ArrayList<Object> labels = new ArrayList<>();		
		File students = new File(coursePath+"/Students");
		
		int tableRowToWrite=1;
		if(students.listFiles().length>0){
			view.getReportsStudentsIds().removeAllItems();
			view.btnSearchStudentAddListener(searchStudent);
		for (File studentFile : students.listFiles()) {
			String studentId = (String) studentFile.getName().
					subSequence(0,studentFile.getName().length()-4);
			if(searchStudentId.equals("all")){
				createStudentRow(studentId,studentFile,tableRowToWrite,table);
				tableRowToWrite = tableRowToWrite+1;
			}
			else if(studentId.equals(searchStudentId))
			{
				createStudentRow(studentId,studentFile,tableRowToWrite,table);
			}
		}	
		}
		else
			JOptionPane.showMessageDialog(null
					, "There are no students under this course name, please choose another course"
					, "Alert",
					JOptionPane.ERROR_MESSAGE);
	}
	
	public void createStudentRow(String studentId, File studentFile,
			int tableRowToWrite, CTable table)
	{
		double avg = 0;
		quizzesScores = new ArrayList<Object>();
		quizzesScores.add(new JLabel(studentId));
		view.getReportsStudentsIds().addItem(studentId);
		try {
			StudentEntity result =(StudentEntity) ObjectFileManager.
					loadObject(studentFile.getCanonicalPath());
			String score;
			int i;
			for (i = 0; i < quizzesNames.size()-1; i++) {
				 score = result.getScore(quizzesNames.get(i));
				 if(score.equals("-1"))
				 {
					 quizzesScores.add(new JLabel("-"));
					 avg += 0;
				 }
				 else
				 {
					 quizzesScores.add(new JLabel(score));
					 avg += Integer.parseInt(score);
				 }
			}		
			avg = avg/(i+1);
			quizzesScores.add(new JLabel(String.format("%.2f",avg)));
//			System.out.println(tableRowToWrite);
			table.add(new RepRow(quizzesScores, tableRowToWrite));			
			view.getScrollPane().setViewportView(table);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
//
//

