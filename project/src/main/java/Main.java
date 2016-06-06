import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

import javax.swing.SwingUtilities;

import Controllers.IntroFrameController;
import Views.IntroFrame;

public class Main {

	public static void main(String[] args) {
			new IntroFrameController(new IntroFrame());
	}

}
