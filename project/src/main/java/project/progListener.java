package project;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dropbox.client2.ProgressListener;

import Controllers.DropBoxSimple;
import Views.CustomDialog;

public class progListener extends ProgressListener
	{
		private double currentFileSize;
		private double percent;
		private double lastFileSize;
		private long totalDropboxSize;
		public CustomDialog dialog;
		private String type;
		
		public  progListener(long totalDropboxSize,String type) {
			// TODO Auto-generated constructor stub
			currentFileSize = 0f;
			percent = 0f;
			lastFileSize=0f;
			this.totalDropboxSize = totalDropboxSize;
			this.type = type;

			dialog = new CustomDialog();
			if(type.equals("downloaded"))
			dialog.setTitle("Loading your files");
			else
				dialog.setTitle("Uploading your files");
			
			dialog.setLabelText("0% of files have been "+type);
		
		}
		@Override
		public void onProgress(long arg0, long arg1) {
			// TODO Auto-generated method stub
			if(lastFileSize>arg0)
				lastFileSize=0;
			currentFileSize += arg0-lastFileSize;
			percent = 100.0f*(double)currentFileSize/totalDropboxSize;
			dialog.setLabelText(String.format("%.2f",percent)
			+"% of files have been "+type);
			lastFileSize = arg0;
			
		}

		@Override
		public long progressInterval() {
			// TODO Auto-generated method stub
			return 1;
		}
		public void setType(String type)
		{
			this.type=type;
		}
	}