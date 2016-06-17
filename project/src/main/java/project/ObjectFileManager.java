package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Class ObjectFileManager.
 * This class is used for saving and reading objects files.
 */
public class ObjectFileManager {
	
	/**
	 * Instantiates a new object file manager.
	 */
	public ObjectFileManager()
	{
	}
	
	/**
	 * Save object.
	 *
	 * @param obj the object to be saved
	 * @param path the path
	 * @return true, if successful
	 */
	public static boolean saveObject(Object obj,String path)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Load object.
	 *
	 * @param path the path
	 * @return the object
	 */
	public static Object loadObject(String path)
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object result = ois.readObject();
			ois.close();	
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			return null;
		
	}
	
	/**
	 * Folder size.
	 *
	 * @param directory the directory
	 * @return the long
	 */
	public static long folderSize(File directory) {
	    long length = 0;
	    for (File file : directory.listFiles()) {
	        if (file.isFile())
	            length += file.length();
	        else
	            length += folderSize(file);
	    }
	    return length;
	}
}
