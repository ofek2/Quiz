package Entities;

import java.io.File;
import java.io.IOException;

public class Constants {

	public static String ROOTPATH;
	static{
		try {
			ROOTPATH = new File(".").getCanonicalPath() + "/OnlineQuizChecker/";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	final public static String APP_NAME = "OnlineQuizChecker";
	final public static String SAVE = "save";
	final public static String SAVE_AND_EXIT = "save_and_exit";
	
	public static int FRAME_WIDTH = 1000;
	public static int FRAME_HEIGHT = 700;
}
