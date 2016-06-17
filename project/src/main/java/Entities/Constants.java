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
	public static String HELP_INTRO = "help_intro_";
	public static int HELP_INTRO_AMOUNT = 3;
	/** The mainscreen help folder. */
	public static String HELP_MAIN = "help_main_";
	public static int HELP_MAIN_AMOUNT = 6;
	/** The newquiz help folder. */
	public static String HELP_NEWQUIZ= "help_newquiz_";
	public static int HELP_NEWQUIZ_AMOUNT = 3;
	/** The gradequiz help folder. */
	public static String HELP_GRADEQUIZ = "help_grade_";
	public static int HELP_GRADEQUIZ_AMOUNT = 2;
	/** The reportsscreen help folder. */
	public static String HELP_REPORTS= "help_reports_";
	public static int HELP_REPORTS_AMOUNT = 2;
}
