package Entities;

import java.io.File;

import Controllers.InitialWindowController;

/**
 * The Class CourseEntity.
 * This class is an entity for a course.
 */
public class CourseEntity {

	/** The course folder. */
	private File courseFolder;

	/** The course id. */
	private String courseId;

	/** The course name. */
	private String courseName;

	/** The course folder name. */
	private String courseFolderName;

	/**
	 * Instantiates a new course entity.
	 *
	 * @param courseFolder
	 *            the course folder
	 * @param courseId
	 *            the course id
	 * @param courseName
	 *            the course name
	 */
	public CourseEntity(File courseFolder, String courseId, String courseName) {
		this.courseFolder = courseFolder;
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseFolderName = courseId + "," + courseName;
	}

	/**
	 * Gets the course folder.
	 *
	 * @return the course folder
	 */
	public File getCourseFolder() {
		return courseFolder;
	}

	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course id.
	 *
	 * @param courseId
	 *            the new course id
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName
	 *            the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the course folder name.
	 *
	 * @param courseId
	 *            the course id
	 * @param courseName
	 *            the course name
	 * @return the course folder name
	 */
	public static String getCourseFolderName(String courseId, String courseName) {
		return courseId + "," + courseName;
	}

	/**
	 * Gets the course folder name.
	 *
	 * @return the course folder name
	 */
	public String getCourseFolderName() {
		return courseFolderName;
	}

	/**
	 * Sets the course folder name.
	 *
	 * @param courseFolderName
	 *            the new course folder name
	 */
	public void setCourseFolderName(String courseFolderName) {
		this.courseFolderName = courseFolderName;
	}

	/**
	 * Course exist.
	 *
	 * @return true, if successful
	 */
	public boolean CourseExist() {
		for (int i = 0; i < InitialWindowController.coursesFiles.size(); i++)
			if (courseId.equals(InitialWindowController.coursesFiles.get(i).courseId))
				return true;
		return false;

	}

	/**
	 * Check position.
	 *
	 * @return the int
	 */
	public int checkPosition() {
		int i;
		for (i = 0; i < InitialWindowController.coursesFiles.size(); i++)
			if (Integer.parseInt(courseId) < Integer.parseInt(InitialWindowController.coursesFiles.get(i).courseId))
				return i;
		return i;

	}

	/**
	 * Gets the index.
	 *
	 * @param courseName
	 *            the course name
	 * @return the index
	 */
	public static int getIndex(String courseName) {
		int i;
		for (i = 0; i < InitialWindowController.coursesFiles.size(); i++)
			if (InitialWindowController.coursesFiles.get(i).courseFolderName.equals(courseName))
				return i;
		return -1;
	}

}
