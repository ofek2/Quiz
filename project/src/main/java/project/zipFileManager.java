package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class zipFileManager {
	public zipFileManager(){
		
	}
	public static void createZipFile(File fileToZip,String zipFilePath)
	{
		 FileOutputStream fos;
		try {
			fos = new FileOutputStream(zipFilePath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			addDirToZipArchive(zos,fileToZip,null);
			zos.flush();
			fos.flush();
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
	    if (fileToZip == null || !fileToZip.exists()) {
	        return;
	    }

	    String zipEntryName = fileToZip.getName();
	    if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
	        zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
	    }

	    if (fileToZip.isDirectory()) {
	        System.out.println("+" + zipEntryName);
	        for (File file : fileToZip.listFiles()) {
	            addDirToZipArchive(zos, file, zipEntryName);
	        }
	    } else {
	        System.out.println("   " + zipEntryName);
	        byte[] buffer = new byte[1024];
	        FileInputStream fis = new FileInputStream(fileToZip);
	        zos.putNextEntry(new ZipEntry(zipEntryName));
	        int length;
	        while ((length = fis.read(buffer)) > 0) {
	            zos.write(buffer, 0, length);
	        }
	        zos.closeEntry();
	        fis.close();
	    }
	}
	
	public static  void unZipIt(String zipFile, String outputFolder){

         byte[] buffer = new byte[1024];
        	
         try{
        		
        	//create output directory is not exists
        	File folder = new File(outputFolder);
        	if(!folder.exists()){
        		folder.mkdir();
        	}
        		
        	//get the zip file content
        	ZipInputStream zis = 
        		new ZipInputStream(new FileInputStream(zipFile));
        	//get the zipped file list entry
        	ZipEntry ze = zis.getNextEntry();
        		
        	while(ze!=null){
        			
        	   String fileName = ze.getName();
               File newFile = new File(outputFolder + File.separator + fileName);
                    
               System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                    
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                  
                FileOutputStream fos = new FileOutputStream(newFile);             

                int len;
                while ((len = zis.read(buffer)) > 0) {
           		fos.write(buffer, 0, len);
                }
            		
                fos.close();   
                ze = zis.getNextEntry();
        	}
        	
            zis.closeEntry();
        	zis.close();
        		
        	System.out.println("Done");
        		
        }catch(IOException ex){
           ex.printStackTrace(); 
        }
       }    
     
}
