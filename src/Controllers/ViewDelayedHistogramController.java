package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;




public class ViewDelayedHistogramController {

	ArrayList<String> DelayedReport = new ArrayList<String>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<?, ?> AmountBarChart;

    @FXML
    private CategoryAxis AmountX;

    @FXML
    private NumberAxis AmountY;

    @FXML
    private BarChart<?, ?> DurationBarChart;

    @FXML
    private CategoryAxis DurationX;

    @FXML
    private NumberAxis DurationY;

    @FXML
    void initialize() {


    }
    /**
     * create the delayed histogram
     * @param AmountDC d 
     * @param DurationDC f
     * @param AmountMaxParts g
     * @param DurationMaxParts h
     * @param pos s
     *
     */
    public void loadHistogram(int[] AmountDC,int[] DurationDC,double AmountMaxParts,double DurationMaxParts,int pos) {

    	XYChart.Series set1 = new XYChart.Series<>();
    	XYChart.Series set2 = new XYChart.Series<>();
    	//int newpos = (pos/len)*10;
    	System.out.println("pos is:"+pos);
    	
    	set1.setName("Amount Histogram");
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*0)+"-"+AmountMaxParts, AmountDC[0]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*2), AmountDC[1]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*2)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*3), AmountDC[2]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*3)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*4), AmountDC[3]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*4)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*5), AmountDC[4]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*5)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*6), AmountDC[5]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*6)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*7), AmountDC[6]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*7)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*8), AmountDC[7]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*8)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*9), AmountDC[8]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(AmountMaxParts*9)+"-"+new DecimalFormat("##.##").format(AmountMaxParts*10), AmountDC[9]));
 
    	AmountBarChart.getData().addAll(set1);
    	
    	set2.setName("Duration Histogram");
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*0)+"-"+DurationMaxParts, DurationDC[0]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*2), DurationDC[1]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*2)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*3), DurationDC[2]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*3)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*4), DurationDC[3]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*4)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*5), DurationDC[4]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*5)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*6), DurationDC[5]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*6)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*7), DurationDC[6]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*7)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*8), DurationDC[7]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*8)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*9), DurationDC[8]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DurationMaxParts*9)+"-"+new DecimalFormat("##.##").format(DurationMaxParts*10), DurationDC[9]));
    	
    	DurationBarChart.getData().addAll(set2);
    	
    	
    }
    
    
}
