package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entities.QuizObjectEntity;

public class ObjectFileManager {
	//private String path;
	public ObjectFileManager()
	{
		//this.path=path;
	}
	public boolean saveObject(Object obj,String path)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public Object loadObject(String path)
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object result = ois.readObject();
			ois.close();	
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
		
	}
}
