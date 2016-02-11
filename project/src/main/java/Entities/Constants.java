package Entities;

import java.awt.Toolkit;

public class Constants {
	public final static int realtiveFrameXPos=(int)Toolkit.getDefaultToolkit().getScreenSize().width/4;
	public final static int realtiveFrameYPos=(int)Toolkit.getDefaultToolkit().getScreenSize().height/4;
	public final static double realtiveFrameInitWidth=1920;
	public final static double realtiveFrameInitHeight=1080;
	public final static double ratio = realtiveFrameInitWidth/realtiveFrameInitHeight;
	
}
