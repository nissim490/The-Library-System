package Controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class ViewPeriodicActivitiesReportByManagerController {
	
	ArrayList<String> PeriodicActivitiesReport = new ArrayList<String>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField lbProductionDate;

    @FXML
    private JFXTextField lbProducerName;

    @FXML
    private JFXTextField lbActiveReader;

    @FXML
    private JFXTextField lbFrozenReader;

    @FXML
    private JFXTextField lbLockedReader;

    @FXML
    private JFXTextField lbBorrowedBook;

    @FXML
    private JFXTextField lbDelaysReader;

    @FXML
    private JFXTextField lbReportNum;

    @FXML
    void initialize() {
    	
    }
    /**
     * load the report information to the page
     * @param periodicactivitiesreport 3
     *
     */
    public void loadActivitiesReport(ArrayList<String> periodicactivitiesreport) {
    	this.PeriodicActivitiesReport = periodicactivitiesreport;
    	lbReportNum.setText(PeriodicActivitiesReport.get(0));
    	lbProducerName.setText(PeriodicActivitiesReport.get(1));
    	lbActiveReader.setText(PeriodicActivitiesReport.get(2));
    	lbFrozenReader.setText(PeriodicActivitiesReport.get(3));
    	lbLockedReader.setText(PeriodicActivitiesReport.get(4));
    	lbDelaysReader.setText(PeriodicActivitiesReport.get(5));
    	lbBorrowedBook.setText(PeriodicActivitiesReport.get(6));
    	lbProductionDate.setText(PeriodicActivitiesReport.get(7));
    	
    }
}
