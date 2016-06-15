package Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Controllers.InitialWindowController;
import Entities.Constants;
import Entities.CourseEntity;

/**
 * The Class CoursesCheckingFrame.
 * This class is a boundary controlled by {@link InitialWindowController}
 * This frame shows what is missing to create an appropriate course.
 * Appropriate course is build from at least:
 * One quiz.
 * One student.
 */
public class CoursesCheckingFrame{
	
	/** The Constant linesSpace. */
	private static final int linesSpace = 10;
	
	/** The courses files. */
	private ArrayList<CourseEntity> coursesFiles;
	
	/** The to fix array. */
	private ArrayList<String> toFixArray;
	
	/** The frame. */
	private JFrame frame;
	
	/** The panel. */
	private JPanel panel;
	
	/**
	 * Instantiates a new courses checking frame.
	 *
	 * @param coursesFiles the courses files
	 */
	public CoursesCheckingFrame(ArrayList<CourseEntity> coursesFiles){
		this.coursesFiles = coursesFiles;
		toFixArray = new ArrayList<String>();
		
	}
	
	/**
	 * Check.
	 *
	 * @return true, if successful
	 */
	public boolean check() {
		// TODO Auto-generated method stub
		initFrame();
		if(coursesFiles == null || coursesFiles.isEmpty())
		{
			handleNoCourses();
			return false;
		}
		else
		{
			for(int i=0;i<coursesFiles.size();i++)
			{
				try {
					File quizzesFolder = new File(coursesFiles.get(i).getCourseFolder().getCanonicalFile()+"/Quizzes");
					File studentsFolder = new File(coursesFiles.get(i).getCourseFolder().getCanonicalFile()+"/Students");
					if(quizzesFolder.listFiles().length == 0 || studentsFolder.listFiles().length == 0)
					{
						toFixArray.add((i+1)+". In course : "+coursesFiles.get(i).getCourseName());
						if(quizzesFolder.listFiles().length == 0)
							toFixArray.add("- There are no quizzes, please create at least one quiz.");
						if(studentsFolder.listFiles().length == 0)
							toFixArray.add("- There are no students, please add at least one student.");
					}
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(toFixArray.size()>0)
			{
				buildFrame();
				return false;
			}
		}
		frame.dispose();
		return true;
	}
	
	/**
	 * Inits the frame.
	 */
	private void initFrame() {
		
		frame=new JFrame("Warning!");
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(false);
		frame.setBackground(Color.WHITE);
		panel = new JPanel();
		panel.setSize(500,500);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JScrollPane jsp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(16);
		JPanel backPanel = new JPanel();
		backPanel.setSize(500,500);
		backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));
		backPanel.add(jsp);
		frame.setContentPane(backPanel);
		
	}
	
	/**
	 * Builds the frame.
	 */
	private void buildFrame() {
		
		Component titleSpace = Box.createVerticalStrut(linesSpace);
		Component regularSpace = Box.createVerticalStrut(linesSpace-2);
		panel.add(titleSpace);
		
		for(int i=0;i<toFixArray.size();i++)
		{
			JLabel line = new JLabel(toFixArray.get(i));
			line.setMaximumSize(new Dimension(100000,40));
			if(!toFixArray.get(i).startsWith("-")) //its a title
			{
				line.setFont(new Font("Tahoma", Font.BOLD, 17));
				panel.add(line);
				panel.add(titleSpace);
			}
			else
			{
				line.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(line);
				panel.add(regularSpace);
			}
	
		}
		frame.setVisible(true);
	}
	
	/**
	 * Handle no courses.
	 */
	private void handleNoCourses() {

		JLabel line1 = new JLabel("Please add at least one course containing :");
		line1.setMaximumSize(new Dimension(100000,40));
		line1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel line2 = new JLabel("1.At least one quiz.");
		line2.setMaximumSize(new Dimension(100000,40));
		line2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel line3 = new JLabel("2.At least one student.");
		line3.setMaximumSize(new Dimension(100000,40));
		line3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		Component verticalStrut = Box.createVerticalStrut(linesSpace);
		
		panel.add(verticalStrut);
		panel.add(line1);
		panel.add(verticalStrut);
		panel.add(line2);
		panel.add(verticalStrut);
		panel.add(line3);
		
		frame.setVisible(true);
	}

}
