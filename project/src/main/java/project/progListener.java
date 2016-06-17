package project;

import com.dropbox.client2.ProgressListener;
import Views.CustomDialog;


/** 
 * This class is used for presenting a progress bar when downloading or uploading 
 * files from/to Dropbox.
 */
public class progListener extends ProgressListener
	{
		
		/** The current file size. */
		private double currentFileSize;
		
		/** The percent. */
		private double percent;
		
		/** The last file size. */
		private double lastFileSize;
		
		/** The total Dropbox size. */
		private long totalDropboxSize;
		
		/** The dialog. */
		public CustomDialog dialog;
		
		/** The type. */
		private String type;
		
		/**
		 * Instantiates a new prog listener.
		 *
		 * @param totalDropboxSize the total dropbox size
		 * @param type the type
		 */
		public  progListener(long totalDropboxSize,String type) {
			init(totalDropboxSize,type);
			
		}
		
		/**
		 * Inits the progress bar.
		 *
		 * @param totalDropboxSize the total dropbox size
		 * @param type the type
		 */
		public void init(long totalDropboxSize,String type) {
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
			dialog.setVisible(false);
		}
		
		/* (non-Javadoc)
		 * @see com.dropbox.client2.ProgressListener#onProgress(long, long)
		 */
		@Override
		public void onProgress(long arg0, long arg1) {
			if(lastFileSize>arg0)
				lastFileSize=0;
			currentFileSize += arg0-lastFileSize;
			percent = 100.0f*(double)currentFileSize/totalDropboxSize;
			dialog.setLabelText(String.format("%.2f",percent)
			+"% of files have been "+type);
			lastFileSize = arg0;
			
		}

		/* (non-Javadoc)
		 * @see com.dropbox.client2.ProgressListener#progressInterval()
		 */
		@Override
		public long progressInterval() {
			return 1;
		}
		
		/**
		 * Sets the type.
		 *
		 * @param type the new type
		 */
		public void setType(String type)
		{
			this.type=type;
		}
		
		/**
		 * Gets the total dropbox size.
		 *
		 * @return the total dropbox size
		 */
		public long getTotalDropboxSize() {
			return totalDropboxSize;
		}
		
		/**
		 * Sets the total dropbox size.
		 *
		 * @param totalDropboxSize the new total dropbox size
		 */
		public void setTotalDropboxSize(long totalDropboxSize) {
			this.totalDropboxSize = totalDropboxSize;
		}
		
	}