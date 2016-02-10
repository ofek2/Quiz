package Entities;

import java.io.File;

import Controllers.InitialWindowController;

public class CourseEntity {
	private File courseFolder;
	private String courseId;
	private String courseName;
	private String courseFolderName;
	public CourseEntity(File courseFolder,String courseId,String courseName) {
		this.courseFolder = courseFolder;
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseFolderName = courseId+","+courseName;
	}

	public File getCourseFolder() {
		return courseFolder;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public static String getCourseFolderName(String courseId,String courseName) {
		return courseId+","+courseName;
	}
	public String getCourseFolderName() {
		return courseFolderName;
	}
	public void setCourseFolderName(String courseFolderName) {
		this.courseFolderName = courseFolderName;
	}
	public boolean CourseExist()
	{
		for(int i=0;i<InitialWindowController.coursesFiles.size();i++)
			if(courseId.equals(InitialWindowController.coursesFiles.get(i).courseId))
				return true;
		return false;
			
	}

	
}
