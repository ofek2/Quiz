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
import Entities.Constants;
import Entities.CourseEntity;
import Entities.StudentEntity;
import Views.CTable;
import Views.HelpFrame;
import Views.RepRow;
import Views.ReportsView;
import Views.ViewPanel;

/**
 * The Class ReportsController. This class controls the {@link ReportsView}
 * events. This class is used for producing reports.
 */
public class ReportsController {

	/** The view. */
	public static ReportsView view;

	/** The previous view. */
	private Container previousView;

	/** The quizzes names. */
	private ArrayList<String> quizzesNames;

	/** The quizzes scores. */
	private ArrayList<Object> quizzesScores;

	/** The course path. */
	private String coursePath;

	/** The search student. */
	private SearchStudent searchStudent;

	/** The table. */
	private CTable table;

	/** The enable exporting and searching. */
	private boolean enableExportingAndSearching = false;

	/** The course name. */
	private String courseName;

	/**
	 * Instantiates a new reports controller.
	 *
	 * @param view
	 *            the view
	 * @param previousView
	 *            the previous view
	 */
	public ReportsController(ReportsView view, Container previousView) {
		this.view = view;
		this.previousView = previousView;
		this.view.btnReportsShowGradesAddListener(new ProduceReports());
		this.view.btnSearchStudentAddListener(new SearchStudent());
		this.view.mntmExitAddListener(new ExitListener());
		this.view.btnExportExcelFileAddListener(new ExportExcelFileListener());
		this.view.addHelpActionListener(new HelpListener());
		// view.table = new CustomTable(view);
	}

	/**
	 * The listener interface for receiving exit events. The class that is
	 * interested in processing a exit event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When the exit event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see ExitEvent
	 */
	class ExitListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel) previousView);
		}

	}

	/**
	 * The listener interface for receiving exportExcelFile events. The class
	 * that is interested in processing a exportExcelFile event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addExportExcelFileListener
	 * <code> method. When the exportExcelFile event occurs, that object's
	 * appropriate method is invoked.
	 *
	 * @see ExportExcelFileEvent
	 */
	class ExportExcelFileListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (enableExportingAndSearching) {
				String fileName = coursePath + "/Report.xls";

				PrintWriter out;
				try {
					FileWriter excelFile = new FileWriter(fileName);
					out = new PrintWriter(excelFile);
					out.print("Student Id" + "\t");
					for (int i = 0; i < quizzesNames.size(); i++) {
						out.print(quizzesNames.get(i) + "\t");
					}
					out.println();
					for (int i = 1; i < table.getRows().size(); i++) {
						for (int j = 0; j < quizzesNames.size() + 1; j++) {
							out.print(((JLabel) table.getRows().get(i).getRowItems().get(j)).getText() + "\t");
						}
						out.println();
					}
					out.close();
					JOptionPane.showMessageDialog(null, "The report was saved into: " + coursePath, "Alert",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(null, "You have to produce report before exporting", "Alert",
						JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * The Class ProduceReports.
	 */
	class ProduceReports implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			courseName = (String) view.getReportsCourses().getSelectedItem();
			int courseIndexInCoursesArray = CourseEntity.getIndex(courseName);

			try {
				quizzesNames = new ArrayList<String>();
				coursePath = InitialWindowController.coursesFiles.get(courseIndexInCoursesArray).getCourseFolder()
						.getCanonicalPath();
				File quizzes = new File(coursePath + "/Quizzes");
				if (quizzes.listFiles().length > 0) {
					for (File quizFolder : quizzes.listFiles()) {
						quizzesNames.add(quizFolder.getName());
					}
					quizzesNames.add("AVG");
					loadStudentsScoresToTable(quizzesNames.size(), quizzesNames, "all");
				} else {
					enableExportingAndSearching = false;
					view.setTable(null);
					view.getReportsStudentsIds().removeAllItems();
					JOptionPane.showMessageDialog(null,
							"There are no quizzes under this course name, please choose another course", "Alert",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/**
	 * The Class SearchStudent.
	 */
	class SearchStudent implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (enableExportingAndSearching)
				loadStudentsScoresToTable(quizzesNames.size(), quizzesNames,
						(String) view.getReportsStudentsIds().getSelectedItem());
			else
				JOptionPane.showMessageDialog(null, "You have to produce report before searching", "Alert",
						JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Load students scores to table.
	 *
	 * @param size
	 *            the size
	 * @param quizzesNames
	 *            the quizzes names
	 * @param searchStudentId
	 *            the search student id
	 */
	public void loadStudentsScoresToTable(int size, ArrayList<String> quizzesNames, String searchStudentId) {
		view.setTitleButtons(new JButton[size]);
		JButton[] titleButtons = view.getTitleButtons();
		for (int i = 0; i < titleButtons.length; i++) {
			titleButtons[i] = new JButton(quizzesNames.get(i));
		}
		table = new CTable(titleButtons);
		view.setTable(table);
		view.setViewPortForScrollPane(table);
		ArrayList<Object> labels = new ArrayList<>();
		File students = new File(coursePath + "/Students");

		int tableRowToWrite = 1;
		if (students.listFiles().length > 0) {
			enableExportingAndSearching = true;
			view.getReportsStudentsIds().removeAllItems();
			for (File studentFile : students.listFiles()) {
				String studentId = (String) studentFile.getName().subSequence(0, studentFile.getName().length() - 4);
				if (searchStudentId.equals("all")) {
					createStudentRow(studentId, studentFile, tableRowToWrite);
					tableRowToWrite = tableRowToWrite + 1;
				} else if (studentId.equals(searchStudentId)) {
					createStudentRow(studentId, studentFile, tableRowToWrite);
				}
			}
			view.getCourseLabel().setText("Course id: " + courseName);

		} else {
			enableExportingAndSearching = false;
			view.getReportsStudentsIds().removeAllItems();
			view.getScrollPane().setViewportView(null);
			view.getCourseLabel().setText("");
			JOptionPane.showMessageDialog(null,
					"There are no students under this course name, please choose another course", "Alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Creates the student row.
	 *
	 * @param studentId
	 *            the student id
	 * @param studentFile
	 *            the student file
	 * @param tableRowToWrite
	 *            the table row to write
	 */
	public void createStudentRow(String studentId, File studentFile, int tableRowToWrite) {
		double avg = 0;
		quizzesScores = new ArrayList<Object>();
		quizzesScores.add(new JLabel(studentId));
		view.getReportsStudentsIds().addItem(studentId);
		try {
			StudentEntity result = (StudentEntity) ObjectFileManager.loadObject(studentFile.getCanonicalPath());
			String score;
			int i;
			for (i = 0; i < quizzesNames.size() - 1; i++) {
				score = result.getScore(quizzesNames.get(i));
				if (score.equals("-1")) {
					quizzesScores.add(new JLabel("-"));
					avg += 0;
				} else {
					quizzesScores.add(new JLabel(score));
					avg += Integer.parseInt(score);
				}
			}
			avg = avg / (i);
			quizzesScores.add(new JLabel(String.format("%.2f", avg)));
			// System.out.println(tableRowToWrite);
			table.add(new RepRow(quizzesScores, tableRowToWrite));
			view.table.revalidate();
			// view.setViewPortForScrollPane(table);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The listener interface for receiving help events. The class that is
	 * interested in processing a help event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addHelpListener<code> method. When the help event
	 * occurs, that object's appropriate method is invoked.
	 *
	 * @see HelpEvent
	 */
	class HelpListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new helpFrameController(new HelpFrame(Constants.HELP_REPORTS,Constants.HELP_REPORTS_AMOUNT));
		}

	}

}
