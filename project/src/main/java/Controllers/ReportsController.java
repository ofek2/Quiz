package Controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//import com.google.api.services.drive.model.File;






import javax.swing.JOptionPane;

import project.CustomTable;
import Entities.CourseEntity;
import Views.ReportsView;


public class ReportsController {
	public ReportsView view;
	private Container previousView;
	private ArrayList<String> quizzesNames;
	public ReportsController(ReportsView view, Container previousView) {
		this.view = view;
		this.previousView = previousView;
		quizzesNames = new ArrayList<String>();
		view.btnReportsShowGradesAddListener(new produceReports());		
		//view.table = new CustomTable(view);
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
				String coursePath = InitialWindowController.coursesFiles.
						get(courseIndexInCoursesArray).getCourseFolder().getCanonicalPath();
				File quizzes = new File(coursePath+"/Quizzes");
				if(quizzes.listFiles().length>0){
				for (File quizFolder : quizzes.listFiles()) {
					quizzesNames.add(quizFolder.getName());
				}
				quizzesNames.add("AVG");
				view.setTitleButtons(quizzesNames.size(), quizzesNames);
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
	

}
//
//

