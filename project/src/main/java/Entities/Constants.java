package Entities;

import java.io.File;
import java.io.IOException;

/**
 * The Class Constants.
 * This class holds several constants used throughout the project.
 */
public class Constants {

	/** The rootpath. */
	public static String ROOTPATH;

	static {
		try {
			ROOTPATH = new File(".").getCanonicalPath() + "/OnlineQuizChecker/";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** The Constant APP_NAME. */
	final public static String APP_NAME = "OnlineQuizChecker";
	
	/** The Constant SAVE. */
	final public static String SAVE = "save";
	
	/** The Constant SAVE_AND_EXIT. */
	final public static String SAVE_AND_EXIT = "save_and_exit";

	/** The frame width. */
	public static int FRAME_WIDTH = 1000;
	
	/** The frame height. */
	public static int FRAME_HEIGHT = 700;

	/** The introscreen help folder. */
	public static String INTROSCREEN_HELP_FOLDER = "/help/introScreen";
	
	/** The mainscreen help folder. */
	public static String MAINSCREEN_HELP_FOLDER = "/help/mainScreen";
	
	/** The newquiz help folder. */
	public static String NEWQUIZ_HELP_FOLDER = "/help/newQuizScreen";
	
	/** The gradequiz help folder. */
	public static String GRADEQUIZ_HELP_FOLDER = "/help/gradeQuizScreen";
	
	/** The reportsscreen help folder. */
	public static String REPORTSSCREEN_HELP_FOLDER = "/help/reportsScreen";
}
