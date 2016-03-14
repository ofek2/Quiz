package project;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dropbox.client2.ProgressListener;

import Controllers.DropBoxSimple;

public class progListener extends ProgressListener
	{
		private double currentFileSize;
		private double percent;
		private double lastPercent;
		private double lastFileSize;
		private String type;
		private long totalDropboxSize;
		public JDialog dialog;
		private JLabel label;
		public  progListener(long totalDropboxSize,String type) {
			// TODO Auto-generated constructor stub
			currentFileSize = 0f;
			percent = 0f;
			lastPercent=0f;
			lastFileSize=0f;
			this.totalDropboxSize = totalDropboxSize;
			this.type = type;
			//DropBoxSimple.downloadProgressLabel.setText("0% of files have been ");
		
			//DropBoxSimple.downloadProgressD.setVisible(true);
			dialog = new JDialog();
			dialog.setSize(400, 200);
			dialog.setLocationRelativeTo(null);
			label = new JLabel("0% of files have been "+type);
			//downloadProgressLabel.setLocation(170, 100);
			JPanel panel = new JPanel();
			//panel.setLayout(null);
			//panel.setSize(400,200);
			panel.add(label);
			dialog.getContentPane().add(panel);
			dialog.setVisible(true);
		}
		@Override
		public void onProgress(long arg0, long arg1) {
			// TODO Auto-generated method stub
			if(lastFileSize>arg0)
				lastFileSize=0;
			currentFileSize += arg0-lastFileSize;
			percent = 100.0f*(double)currentFileSize/totalDropboxSize;
			label.setText(String.format("%.2f",percent)
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