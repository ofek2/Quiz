package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import Views.GmailAuthFrame;
import project.GoogleMail;

/**
 * The Class GmailAuthFrameController.
 * This class controls Gmail authentication process
 */
public class GmailAuthFrameController {
	
	/** The view. */
	private GmailAuthFrame view;
	
	/** The student grading controllers. */
	private ArrayList<StudentGradingController> studentGradingControllers;
	
	/** The students quizzes paths. */
	private ArrayList<String> studentsQuizzesPaths;

	/**
	 * Instantiates a new Gmail auth frame controller.
	 *
	 * @param view the view
	 * @param studentGradingControllers a list of StudentGradingController 
	 * @param studentsQuizzesPaths a list of the students' quizzes paths 
	 */
	public GmailAuthFrameController(GmailAuthFrame view, ArrayList<StudentGradingController> studentGradingControllers,
			ArrayList<String> studentsQuizzesPaths) {
		this.view = view;
		this.studentGradingControllers = studentGradingControllers;
		this.studentsQuizzesPaths = studentsQuizzesPaths;

		authorizeGmailAccount();

		this.view.sendBtnAddListener(new sendBtnListener());

	}

	/**
	 * Authorize Gmail account.
	 */
	private void authorizeGmailAccount() {
		// TODO Auto-generated method stub
		try {
			String url = GoogleMail.startAuthorize();
			view.loadURL(url);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The listener interface for receiving sendBtn events.
	 * The class that is interested in processing a sendBtn
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addsendBtnListener<code> method. When
	 * the sendBtn event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see sendBtnEvent
	 */
	class sendBtnListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to send graded quizzes to your students?",
					"Alert", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String quizName = "";
				if (!studentGradingControllers.isEmpty())
					quizName = studentGradingControllers.get(0).getQuizName();
				for (int i = 0; i < studentGradingControllers.size(); i++) {
					try {
						String studentId = new File(studentsQuizzesPaths.get(i)).getName();
						String temp = getStudentQuizPath(studentsQuizzesPaths.get(i));
						String pdfPath = studentsQuizzesPaths.get(i)+"/"+studentId+".pdf";
						convertHtmlToPdf(temp,pdfPath);
						GoogleMail.SendMail(studentGradingControllers.get(i).getStudentEmail(), quizName + " - Graded Quiz",
								pdfPath	, quizName + ".pdf");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null,
						"The graded quizzes were sent successfully to your students' emails.",
						"Emails Delivered Successfully", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		private String getStudentQuizPath(String studentFolder) throws IOException {
			// TODO Auto-generated method stub
			File folder = new File(studentFolder);
			if(folder.exists())
				for(int i = 0;i<folder.listFiles().length;i++)
					if(folder.listFiles()[i].getName().endsWith(".html"))
						return folder.listFiles()[i].getCanonicalPath();
						
			return null;
		}
		private void convertHtmlToPdf(String htmlPath,String pdfPath){
		try {
		    OutputStream file = new FileOutputStream(new File(pdfPath));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    document.open();
		    HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.parse(new FileReader(new File(htmlPath)));
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
	}
}
