package Controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controllers.ReaderHomepageController.Book;
import entity.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ViewNewOldDisciplinaryInquiryReportByManagerController {
	ObservableList<Book> Mylist = FXCollections.observableArrayList();
//	private DisciplinaryInquiryReportsByManagerController rep;
	private ArrayList<String> report1 = new ArrayList<String>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtReportID;

    @FXML
    private JFXTextField txtReaderName;

    @FXML
    private JFXTextField txtReaderCardNumber;

    @FXML
    private JFXTextField txtMangerName;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtVerdict;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    void initialize() {

    }
    /**
     * load the report information to the page
     * @param report d
     *
     */
    public void loadreport(ArrayList<String> report){
    	  
	    this.report1=report;
		txtReportID.setText(report.get(0));
		txtReaderName.setText(report.get(1));
		txtReaderCardNumber.setText(report.get(2));
		txtMangerName.setText(report.get(3));
		txtDate.setText(report.get(4));
		txtVerdict.setText(report.get(5));
	//	txtBookDelays.setText(report.get(6));
		txtDescription.setText(report.get(7));
		

	}
}
