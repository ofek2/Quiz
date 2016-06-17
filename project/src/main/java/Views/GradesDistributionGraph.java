package Views;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EtchedBorder;
import Controllers.MainFrameController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

/**
 * The Class GradesDistributionGraph.
 * This class is used for creating grades distribution graphs in the {@link ReportsView}.
 */
public class GradesDistributionGraph extends ViewPanel implements Runnable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The fx panel. */
	private JFXPanel fxPanel;
	
	/** The quiz scores. */
	private ArrayList<String> quizScores;
	
	/** The title. */
	private String title;
	
	/** The graph menu. */
	private JMenu graphMenu;
	
	/** The previous view. */
	private Container previousView;
	
	/** The thread. */
	private Thread thread;
    
    /** The Constant first. */
    final static String first = "0-54.9";
    
    /** The Constant second. */
    final static String second = "55-64";
    
    /** The Constant third. */
    final static String third = "65-69";
    
    /** The Constant fourth. */
    final static String fourth = "70-80";
    
    /** The Constant fifth. */
    final static String fifth = "81-90";
    
    /** The Constant last. */
    final static String last = "91-100";
    
    /** The Constant partition. */
    final static double[] partition={55,65,70,81,91,100};
    
    /** The partitions percentages. */
    private double[] partitionsPercentages = {0,0,0,0,0,0};
	
	/**
	 * Instantiates a new grades distribution graph.
	 *
	 * @param quizScores the quiz scores
	 * @param title the title
	 * @param previousView the previous view
	 */
	public GradesDistributionGraph(ArrayList<String> quizScores,String title,
			Container previousView) {
		this.quizScores = quizScores;
		this.title = title;
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
		fxPanel.setBounds(MainFrameController.view.getContentPane().getWidth()/8,40,MainFrameController.view.getContentPane().getWidth()*6/8,620);
		fxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(fxPanel);
		thread = new Thread(this);
		Platform.runLater(thread);
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		initFX(fxPanel);
	}
	
	/**
	 * Inits the fx.
	 *
	 * @param fxPanel the fx panel
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void initFX(JFXPanel fxPanel) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(title);
        xAxis.setLabel("Grades");       
        yAxis.setLabel("Percentage");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Percentage");       
        for (int i = 0; i < quizScores.size(); i++) {
        	if(quizScores.get(i).equals("-"))
        		partitionsPercentages[0]+=1;
        	else if(Double.parseDouble(quizScores.get(i))<partition[0])
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
	
	/**
	 * The listener interface for receiving exit events.
	 * The class that is interested in processing a exit
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When
	 * the exit event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitEvent
	 */
	class ExitListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			MainFrameController.view.changeContentPane((ViewPanel)previousView);

		}
		
	}
}
