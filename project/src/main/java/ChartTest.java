import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class ChartTest extends Application {
    final static String first = "0-54.9";
    final static String second = "55-64";
    final static String third = "65-69";
    final static String fourth = "70-80";
    final static String fifth = "81-90";
    final static String last = "91-100";
 
    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Grades Average");
        xAxis.setLabel("Grades");       
        yAxis.setLabel("Percentage");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Percentage");       
        series1.getData().add(new XYChart.Data(first, 3));
        series1.getData().add(new XYChart.Data(second, 10));
        series1.getData().add(new XYChart.Data(third, 25));
        series1.getData().add(new XYChart.Data(fourth, 22));
        series1.getData().add(new XYChart.Data(fifth, 10));      
        series1.getData().add(new XYChart.Data(last, 30));     
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}