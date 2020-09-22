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




public class ViewLendingReportByManagerController {

	ArrayList<String> LendingReport = new ArrayList<String>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField lbmediannormalborrowd;

    @FXML
    private JFXTextField lbavaragenormalborrowd;

    @FXML
    private JFXTextField lbavaragedemandborrowd;

    @FXML
    private JFXTextField lbmediandemandborrowd;

    @FXML
    private BarChart<?, ?> DemandedBarChart;

    @FXML
    private CategoryAxis DemandedX;

    @FXML
    private NumberAxis DemandedY;

    @FXML
    private BarChart<?, ?> NormalBarChart;

    @FXML
    private CategoryAxis NormalX;

    @FXML
    private NumberAxis NormalY;

    @FXML
    void initialize() {


    }
    /**
     * load information for the lending report
     * @param AvarageDemanded d
     * @param AvarageNormal f
     * @param MedianDemanded g
     * @param MedianNormal h
     * @param DemandedDC d
     * @param NormalDC s
     * @param DemandedMaxParts a
     * @param NormalMaxParts e
     *
     */
    public void loadLendingReport(double AvarageDemanded,double AvarageNormal,double MedianDemanded,double MedianNormal,int[] DemandedDC,int[] NormalDC,double DemandedMaxParts,double NormalMaxParts) {
    	//this.LendingReport = lendingreport;
    	lbmediannormalborrowd.setText(String.valueOf(MedianNormal));
    	lbavaragenormalborrowd.setText(String.valueOf(AvarageNormal));
    	lbavaragedemandborrowd.setText(String.valueOf(AvarageDemanded));
    	lbmediandemandborrowd.setText(String.valueOf(MedianDemanded));

    	
    	XYChart.Series set1 = new XYChart.Series<>();
    	XYChart.Series set2 = new XYChart.Series<>();

    	set1.setName("Normal Histogram");
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*0)+"-"+NormalMaxParts, NormalDC[0]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*2), NormalDC[1]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*2)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*3), NormalDC[2]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*3)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*4), NormalDC[3]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*4)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*5), NormalDC[4]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*5)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*6), NormalDC[5]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*6)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*7), NormalDC[6]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*7)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*8), NormalDC[7]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*8)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*9), NormalDC[8]));
    	set1.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(NormalMaxParts*9)+"-"+new DecimalFormat("##.##").format(NormalMaxParts*10), NormalDC[9]));
 
    	NormalBarChart.getData().addAll(set1);
    	
    	set2.setName("Demanded Histogram");
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*0)+"-"+DemandedMaxParts, DemandedDC[0]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*2), DemandedDC[1]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*2)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*3), DemandedDC[2]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*3)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*4), DemandedDC[3]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*4)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*5), DemandedDC[4]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*5)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*6), DemandedDC[5]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*6)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*7), DemandedDC[6]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*7)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*8), DemandedDC[7]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*8)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*9), DemandedDC[8]));
    	set2.getData().add(new XYChart.Data(new DecimalFormat("##.##").format(DemandedMaxParts*9)+"-"+new DecimalFormat("##.##").format(DemandedMaxParts*10), DemandedDC[9]));
    	
    	DemandedBarChart.getData().addAll(set2);
    	
    	
    }
    
    
}
