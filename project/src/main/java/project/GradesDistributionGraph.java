package project;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import Controllers.MainFrameController;
import Views.ViewPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class GradesDistributionGraph extends ViewPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFXPanel fxPanel;
	private ArrayList<String> quizScores;
	private JMenu graphMenu;
	private Container previousView;
	private Thread thread;
    final static String first = "0-54.9";
    final static String second = "55-64";
    final static String third = "65-69";
    final static String fourth = "70-80";
    final static String fifth = "81-90";
    final static String last = "91-100";
    final static double[] partition={55,65,70,81,91,100};
    private double[] partitionsPercentages = {0,0,0,0,0,0};
	public GradesDistributionGraph(ArrayList<String> quizScores,
			Container previousView) {
		this.quizScores = quizScores;
		this.previousView = previousView;
		setLayout(null);
		fxPanel = new JFXPanel();
		Platform.setImplicitExit(false);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setBackground(java.awt.Color.WHITE);
		menuBar.setBounds(0, 0, MainFrameController.view.getWidth(), 30);
		add(menuBar);
		
		graphMenu = new JMenu("File");
		menuBar.add(graphMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		graphMenu.add(mntmExit);
		mntmExit.addActionListener(new ExitListener());
		fxPanel.setBounds(MainFrameController.view.getContentPane().getWidth()/8,70,MainFrameController.view.getContentPane().getWidth()*6/8, 800);
		fxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(fxPanel);
		thread = new Thread(this);
		Platform.runLater(thread);
	}


	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initFX(fxPanel);
	}
	protected void initFX(JFXPanel fxPanel) {
		// TODO Auto-generated method stub
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Grades Average");
        xAxis.setLabel("Grades");       
        yAxis.setLabel("Percentage");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Percentage");       
        for (int i = 0; i < quizScores.size(); i++) {
        	if(quizScores.equals("-"))
        		partitionsPercentages[0]+=1;
        	else
			if(Double.parseDouble(quizScores.get(i))<partition[0])
				partitionsPercentages[0]+=1;
			else if(Double.parseDouble(quizScores.get(i))<partition[1])
				partitionsPercentages[1]+=1;
			else if(Double.parseDouble(quizScores.get(i))<partition[2])
				partitionsPercentages[2]+=1;
			else if(Double.parseDouble(quizScores.get(i))<partition[3])
				partitionsPercentages[3]+=1;
			else if(Double.parseDouble(quizScores.get(i))<partition[4])
				partitionsPercentages[4]+=1;
			else if(Double.parseDouble(quizScores.get(i))<=partition[5])
				partitionsPercentages[5]+=1;				
		}
        partitionsPercentages[0]=(partitionsPercentages[0]/quizScores.size())*100;
		partitionsPercentages[1]=(partitionsPercentages[1]/quizScores.size())*100;
		partitionsPercentages[2]=(partitionsPercentages[2]/quizScores.size())*100;
		partitionsPercentages[3]=(partitionsPercentages[3]/quizScores.size())*100;
		partitionsPercentages[4]=(partitionsPercentages[4]/quizScores.size())*100;
		partitionsPercentages[5]=(partitionsPercentages[5]/quizScores.size())*100;
        series1.getData().add(new XYChart.Data(first, partitionsPercentages[0]));
        series1.getData().add(new XYChart.Data(second, partitionsPercentages[1]));
        series1.getData().add(new XYChart.Data(third, partitionsPercentages[2]));
        series1.getData().add(new XYChart.Data(fourth, partitionsPercentages[3]));
        series1.getData().add(new XYChart.Data(fifth, partitionsPercentages[4]));      
        series1.getData().add(new XYChart.Data(last, partitionsPercentages[5]));     
        
        bc.getData().add(series1);
		final Scene scene = new Scene(bc, MainFrameController.view.getContentPane().getWidth()*6/8,  MainFrameController.view.getContentPane().getHeight()-30, Color.web("#666970"));
		fxPanel.setScene(scene);
		
	}
	
	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MainFrameController.view.changeContentPane((ViewPanel)previousView);

		}
		
	}
}
