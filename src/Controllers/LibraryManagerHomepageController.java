package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import common.Functions;
import entity.User;




public class LibraryManagerHomepageController extends Application implements Initializable {
	ObservableList<DisciplinaryInquiryReport> Mylist = FXCollections.observableArrayList();
	public static final String ICON_IMAGE_LOC = "/files/icon.png";
	//ArrayList<ArrayList<String>> resultworker;
	//ArrayList<ArrayList<String>> resultuser;
	//ArrayList<ArrayList<String>> resultreader;
	//ArrayList<ArrayList<String>> result;
	
	//////////////
    private User MyUserData;
    ObservableList<SubStat> MylistSubStat = FXCollections.observableArrayList();
    ObservableList<SubBook> MylistSubBook = FXCollections.observableArrayList();
    ObservableList<SubReader> MylistSubReader = FXCollections.observableArrayList();
    ArrayList<ArrayList<String>> resultworker;
    ArrayList<ArrayList<String>> resultstat;
    ArrayList<ArrayList<String>> resultuser2;
    ObservableList<Subworker> MylistSubworker = FXCollections.observableArrayList();
    ArrayList<ArrayList<String>> resultbook;
    ArrayList<ArrayList<String>> resultborrow;
    ArrayList<ArrayList<String>> resultuser;
    ArrayList<ArrayList<String>> result;
    ArrayList<ArrayList<String>> resultreader;
    ArrayList<ArrayList<String>> resultreader2;
    ArrayList<ArrayList<String>> resultActivities;
    ArrayList<String> askForResult3 = new ArrayList<String>();
    ArrayList<String> askForResult4 = new ArrayList<String>();
    ArrayList<String> key3 = new ArrayList<String>();
    ArrayList<String> key4 = new ArrayList<String>();
    String k = "";
    Stage pStage;
    Scene pScene;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneTabs;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab searchesTab;
    @FXML
    private Tab searchesTab1;
    @FXML
    private Tab searchesTab2;
    @FXML
    private ImageView Refresh;
    @FXML
    private ImageView Refresh2;
   

    @FXML
    private JFXComboBox<String> cmbxBookSearchTypes;

    @FXML
    private JFXTextField bookIDInput;

  

    @FXML
    private TableView<SubBook> tblBookSearchResults;

    @FXML
    private TableColumn<SubBook, String> tblcolBookRowNum;

    @FXML
    private TableColumn<SubBook, String> tblcolBookName;

    @FXML
    private TableColumn<SubBook, String> tblcolBookAuthor;

    @FXML
    private TableColumn<SubBook, String> tblcolBookSubject;

    @FXML
    private TableColumn<SubBook, String> tblcolBookStatus;

 
        
   
 

    @FXML
    private JFXComboBox<String> cmbxReaderSearchTypes;

    @FXML
    private JFXTextField memberIDInput;



    @FXML
    private TableView<SubReader> tblReaderSearchResults;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderRowNum;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderCardNumber;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderFullName;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderStatus;

   

    

    @FXML
    private JFXComboBox<String> cmbxWorkerSearchTypes;

    @FXML
    private JFXTextField memberIDInput1;

 

    @FXML
    private TableView<Subworker> tblWorkerSearchResults;

    @FXML
    private TableColumn<Subworker, String> tblcolWorkerRowNum;

    @FXML
    private TableColumn<Subworker, String> tblcolWorkerNumber;

    @FXML
    private TableColumn<Subworker, String> tblcolWorkerFullName;

    @FXML
    private TableColumn<Subworker, String> tblcolWorkerPermissions;

    
    @FXML
    private Tab statisticReportsTab;

    @FXML
    private JFXComboBox<String> cmbxStatisticReportType;

    @FXML
    private JFXButton btnGenerateNewStatisticReport;





    @FXML
    private TableView<SubStat> tblOldPeriodicActivitiesReports;

    @FXML
    private TableColumn<SubStat, String> tblcolStatRowNum;

    @FXML
    private TableColumn<SubStat, String> tblcolStatReportID;

    @FXML
    private TableColumn<SubStat, String> tblcolStatProducerName;

    @FXML
    private TableColumn<SubStat, String> tblcolStatProductionDate;

    @FXML
    private JFXButton btnViewOldStatisticReport;

    @FXML
    private Tab disciplinaryInquiryReportsTab;
////////////////////////////////////////////////
  
    @FXML
    private TableView<DisciplinaryInquiryReport> tblDisciplinaryInquiryReport;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisRowNum;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisReportID;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisReaderName;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisReaderCardNumber;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisProducerName;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String>  tblcolDisProductionDate;

    @FXML
    private TableColumn<DisciplinaryInquiryReport, String> tblcolDisVerdict;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btnCreateNewReport;

    @FXML
    void Refresh(MouseEvent event) {
    	MylistSubStat.clear();
    	loadStatData();
    }
    @FXML
    void Refresh2(MouseEvent event) {
    	Mylist.clear();
    	loadData();
    }
	 /** creating new disciplinary report
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void btnCreateNewReport(ActionEvent event) {//Transfer user information between windows and opening window
    	
    	//CreateNewDisciplinaryInquiryReportByManagerController MainPage = new CreateNewDisciplinaryInquiryReportByManagerController();
    	//MainPage.load(MyUserData);
    	//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/CreateNewDisciplinaryInquiryReportByManager.fxml"), "Disciplinary Inquiry Report", null); 
    	
        
    	try {
        	Stage stage = new Stage(); 
            Parent root;                   
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/CreateNewDisciplinaryInquiryReportByManager.fxml"));
			root = loader.load();
			CreateNewDisciplinaryInquiryReportByManagerController CreateDisController = loader.getController();
			CreateDisController.load(MyUserData);
			//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
			
			//BookController.loadAnchor(AnchorPaneTabs);
			
            Scene scene = new Scene(root);
            stage.setTitle("Create New Disciplinary Inquiry Report");
            stage.setScene(scene);
            stage.show();
            setStageIcon(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

    }
    
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }
    
    
	 /** to view this disciplinary report
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void btnView(ActionEvent event) {/**brings in relevant data from the DB and initialization to arrylist and Transfer reportInfo information between windows and opening window*/ 
    	int   size2 = resultuser.size();
    	int	 size4 = result.size();
    	int size1= resultreader.size();
       		
            
                 String DisciplinaryInquiryReportID = "";
            	 String ReaderName = "";
            	 String ReaderCardNumber = "";
            	 String ManagerName = "";
            	 String Verdict = "";
            	 String Date = "";
            	 String AmountOfBookDelays = "";
            	 String Content = "";
            	 
            	 
            	 int pos = tblDisciplinaryInquiryReport.getSelectionModel().getSelectedIndex();
            	 ReaderCardNumber = tblDisciplinaryInquiryReport.getItems().get(pos).getReaderCardNumber();
            	 ReaderName = tblDisciplinaryInquiryReport.getItems().get(pos).getReaderName();
            	 ManagerName = tblDisciplinaryInquiryReport.getItems().get(pos).getManagerName();
            	 DisciplinaryInquiryReportID = tblDisciplinaryInquiryReport.getItems().get(pos).getDisciplinaryInquiryReportID();
            	 Verdict = tblDisciplinaryInquiryReport.getItems().get(pos).getVerdict();
            	 Date = tblDisciplinaryInquiryReport.getItems().get(pos).getDate();
            	 
            	 for(int l=0;l<size4;l++) {
            		 if(result.get(l).get(1).equals(ReaderCardNumber)) {
            			 Content = result.get(l).get(3);
            		 }
            	 }

            	 for(int l=0;l<size1;l++) {
            		 if(resultreader.get(l).get(0).equals(ReaderCardNumber)) {
            			 AmountOfBookDelays = resultreader.get(l).get(4);
            		 }
            	 }

            
            	 
    	ArrayList<String> reportInfo = new ArrayList<String>();

    	reportInfo.add(DisciplinaryInquiryReportID);
    	reportInfo.add(ReaderName);
    	reportInfo.add(ReaderCardNumber);
    	reportInfo.add(ManagerName);
    	reportInfo.add(Date);
    	reportInfo.add(Verdict);
    	
    	reportInfo.add(AmountOfBookDelays);
    	reportInfo.add(Content);	
    	
    	try {
       	Stage stage = new Stage(); 
           Parent root;                   
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/gui/ViewNewOldDisciplinaryInquiryReportByManager.fxml"));
    		root = loader.load();
    		ViewNewOldDisciplinaryInquiryReportByManagerController ReportController = loader.getController();
    		ReportController.loadreport(reportInfo);
    		
           Scene scene = new Scene(root);
           stage.setTitle("View Disciplinary Inquiry Report");
           stage.setScene(scene);
           stage.show();
           setStageIcon(stage);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
       
    
    }

	 /** creating new statistic report
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void btnGenerateNewStatisticReport(ActionEvent event) {
        int readersize,ActiveSize,ActiveBorrow,ActiveCnt = 0,FrozenCnt = 0, LockedCnt = 0,DelayedReaderCnt = 0;
        String lastreportid = "", newreportid = "";
        int amounutofdigits,numberlength,amountofzero;

        switch(cmbxStatisticReportType.getValue())
        {
            case "Periodic Activities Report":
            {
                ArrayList<String> key1 = new ArrayList<String>();
                ArrayList<String> key2 = new ArrayList<String>();
                ArrayList<String> key3 = new ArrayList<String>();
                ArrayList<String> askForResult1 = new ArrayList<String>();
                ArrayList<String> askForResult2 = new ArrayList<String>();
                ArrayList<String> askForResult3 = new ArrayList<String>();

                String sql = "SELECT * FROM g17db.tblreader;";
                askForResult1.add("CardStatus");
                askForResult1.add("MissedReturn");

                resultreader2 = Functions.askFromDB(sql ,key1,askForResult1);
                readersize = resultreader2.size();
                for(int i = 0;i<readersize;i++) {
                    if(resultreader2.get(i).get(0).equals("Active")) {
                        ActiveCnt++;
                    }
                    if(resultreader2.get(i).get(0).equals("Frozen")) {
                        FrozenCnt++;
                    }
                    if(resultreader2.get(i).get(0).equals("Locked")) {
                        LockedCnt++;
                    }
                    if(resultreader2.get(i).get(1).equals("Yes")) {
                        DelayedReaderCnt++;
                    }
                }

                sql = "SELECT * FROM g17db.tblbookborrow WHERE Status = 'Active';";
                askForResult2.add("*");

                resultborrow = Functions.askFromDB(sql ,key2,askForResult2);
                ActiveBorrow = resultborrow.size();
                //////////////////////////////////

                sql = "SELECT * FROM g17db.tblperiodicactivitiesreport;";
                askForResult3.add("PeriodicActivitiesReportID");

                resultActivities = Functions.askFromDB(sql ,key3,askForResult3);
                ActiveSize = resultActivities.size();

                lastreportid = resultActivities.get(ActiveSize-1).get(0);

                newreportid = "PAR";
                amounutofdigits = lastreportid.length()-3;
                numberlength = (int) (Math.log10(Integer.parseInt(lastreportid.substring(3,lastreportid.length()))+1) + 1);
                amountofzero = amounutofdigits - numberlength;
                for(int b=0;b<amountofzero;b++) {
                    newreportid = newreportid+"0";
                }
                newreportid = newreportid+(Integer.parseInt(lastreportid.substring(3,lastreportid.length()))+1);



                ArrayList<String> PeriodicActivitiesReport = new ArrayList<String>();
                PeriodicActivitiesReport.add(newreportid);
                PeriodicActivitiesReport.add(MyUserData.getFirstName()+" "+MyUserData.getLastName());
                PeriodicActivitiesReport.add(String.valueOf(ActiveCnt));
                PeriodicActivitiesReport.add(String.valueOf(FrozenCnt));
                PeriodicActivitiesReport.add(String.valueOf(LockedCnt));
                PeriodicActivitiesReport.add(String.valueOf(DelayedReaderCnt));
                PeriodicActivitiesReport.add(String.valueOf(ActiveBorrow));
                PeriodicActivitiesReport.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

                ArrayList<String> newkey = new ArrayList<String>();
                newkey.add(newreportid);
                newkey.add(String.valueOf(ActiveBorrow));
                newkey.add(String.valueOf(ActiveCnt));
                newkey.add(String.valueOf(FrozenCnt));
                newkey.add(String.valueOf(LockedCnt));
                newkey.add(String.valueOf(DelayedReaderCnt));
                newkey.add(MyUserData.getFirstName()+" "+MyUserData.getLastName());
                newkey.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

                // JFXButton yesButton = new JFXButton("YES, Please");
                //JFXButton noButton = new JFXButton("No, Please");
                Functions.askFromDB("INSERT INTO g17db.tblperiodicactivitiesreport  (`PeriodicActivitiesReportID`, `AmountOfBorrowedBookCopies`, `AmountOfActiveReaders`, `AmountOfFrozenReaders`, `AmountOfLockedReaders`, `AmountOfDelayedReaders`,`ProducerName`, `ProductionDate`) values (?,?,?,?,?,?,?,?)",newkey);
                //AlertMaker.showMaterialDialog(bookInfoContainer, AnchorPaneTabs, Arrays.asList(yesButton, noButton), "Confirm Renew Operation", "Are you sure want to renew the book ?");
                try {
                    Stage stage = new Stage();
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/ViewPeriodicActivitiesReportByManager.fxml"));
                    root = loader.load();
                    ViewPeriodicActivitiesReportByManagerController ActivitiesController = loader.getController();
                    ActivitiesController.loadActivitiesReport(PeriodicActivitiesReport);

                    Scene scene = new Scene(root);
                    stage.setTitle("View Periodic Activities Report");
	                stage.setScene(scene);
	                stage.show();
	                setStageIcon(stage);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            }
            case "Book Lending Report":
            {

                ArrayList<String> key = new ArrayList<String>();
                ArrayList<String> askForResult = new ArrayList<String>();
                //String sql = "SELECT * FROM g17db.tblbookborrow;";

                String sql = "Select bb.BorrowingDate, bb.ReturnDate,bb.ActualReturnDate,bb.BorrowedDelayed,bb.BorrowedMissUsed , b.Popularity From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bb.BookCopyID=bc.BookCopyID;";
                askForResult.add("*");
                //askForResult.add("BorrowingDate");
                //askForResult.add("ReturnDate");
                //askForResult.add("ActualReturnDate");
                //askForResult.add("BorrowedDelayed");
                //askForResult.add("BorrowedMissUsed");
                //askForResult.add("Popularity");
                //Select bb.BorrowingDate, bb.ReturnDate,bb.ActualReturnDate,bb.BorrowedDelayed,bb.BorrowedMissUsed , b.Popularity From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bb.BookCopyID=bc.BookCopyID;
                //Select b.Popularity From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bc.BookCopyID='BC000054' and bb.BookCopyID=bc.BookCopyID;
                resultborrow = Functions.askFromDB(sql ,key,askForResult);
                ActiveBorrow = resultborrow.size();

                ArrayList<Long> DemandedDatesResultArray = new ArrayList<Long>();
                ArrayList<Long> NormalDatesResultArray = new ArrayList<Long>();
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                Date d1 = null;
                Date d2 = null;
                Long diff;
                long diffDays;
                int demandedCnt2 = 0, normalCnt2 = 0;
                double demandedCnt = 0,normalCnt = 0;
                for(int i = 0;i<ActiveBorrow;i++) {

                    if(!(resultborrow.get(i).get(2).equals(""))) {
                        try {
                            d1 = format.parse(resultborrow.get(i).get(0));
                            d2 = format2.parse(resultborrow.get(i).get(2));

                            //in milliseconds
                            diff = d2.getTime() - d1.getTime();
                            //System.out.print("\nnot empty: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+diff);
                            // long diffSeconds = diff / 1000 % 60;
                            // long diffMinutes = diff / (60 * 1000) % 60;
                            //long diffHours = diff / (60 * 60 * 1000) % 24;
                            diffDays = diff / (24 * 60 * 60 * 1000);
                            if(resultborrow.get(i).get(5).equals("Demanded")) {
                                DemandedDatesResultArray.add(diffDays);
                                demandedCnt++;
                                demandedCnt2++;
                            }
                            else {
                                NormalDatesResultArray.add(diffDays);
                                normalCnt++;
                                normalCnt2++;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        if(resultborrow.get(i).get(4).equals("Yes") || (resultborrow.get(i).get(4).equals("No") && resultborrow.get(i).get(3).equals("No"))){
                            try {
                                d1 = format.parse(resultborrow.get(i).get(0));
                                d2 = format2.parse(resultborrow.get(i).get(1));

                                //in milliseconds
                                diff = d2.getTime() - d1.getTime();
                                //System.out.print("\nempty1: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+diff);

                                // long diffSeconds = diff / 1000 % 60;
                                // long diffMinutes = diff / (60 * 1000) % 60;
                                //long diffHours = diff / (60 * 60 * 1000) % 24;
                                diffDays = diff / (24 * 60 * 60 * 1000);

                                if(resultborrow.get(i).get(5).equals("Demanded")) {
                                    DemandedDatesResultArray.add(diffDays);
                                    demandedCnt++;
                                    demandedCnt2++;
                                }
                                else {
                                    NormalDatesResultArray.add(diffDays);
                                    normalCnt++;
                                    normalCnt2++;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                d1 = format.parse(resultborrow.get(i).get(0));
                                d2 = format.parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

                                //in milliseconds
                                diff = d2.getTime() - d1.getTime();
                                //System.out.print("\nempty2: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+diff);
                                // long diffSeconds = diff / 1000 % 60;
                                // long diffMinutes = diff / (60 * 1000) % 60;
                                //long diffHours = diff / (60 * 60 * 1000) % 24;
                                diffDays = diff / (24 * 60 * 60 * 1000);

                                if(resultborrow.get(i).get(5).equals("Demanded")) {
                                    DemandedDatesResultArray.add(diffDays);
                                    demandedCnt++;
                                    demandedCnt2++;
                                }
                                else {
                                    NormalDatesResultArray.add(diffDays);
                                    normalCnt++;
                                    normalCnt2++;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }


                double AvarageDemanded,AvarageNormal;
                long sum = 0;
                for(int i = 0;i<demandedCnt;i++) {
                    sum = sum + DemandedDatesResultArray.get(i);
                }
                AvarageDemanded = sum/demandedCnt;
                sum = 0;
                for(int i = 0;i<normalCnt;i++) {
                    sum = sum + NormalDatesResultArray.get(i);
                }
                AvarageNormal = sum/normalCnt;

                //System.out.print("\nAvarageDemanded is: "+AvarageDemanded+"\nAvarageNormal is: "+AvarageNormal);
                double MedianDemanded, MedianNormal;
                Collections.sort(DemandedDatesResultArray);
                Collections.sort(NormalDatesResultArray);
                //MedianDemanded = DemandedDatesResultArray.get((int)(demandedCnt2/2));
                //MedianNormal = NormalDatesResultArray.get((int)(normalCnt2/2));
                double temp;
                int index;
                if(DemandedDatesResultArray.size()%2==1) {
                    index = (DemandedDatesResultArray.size())/2;
                    MedianDemanded = DemandedDatesResultArray.get(index);
                    //returnList2.add(temp);
                }

                else {
                    index = DemandedDatesResultArray.size()/2;
                    temp = DemandedDatesResultArray.get(index);
                    temp += DemandedDatesResultArray.get(index-1);
                    temp /=2;
                    MedianDemanded = temp;
                }

                if(NormalDatesResultArray.size()%2==1) {
                    index = (NormalDatesResultArray.size())/2;
                    MedianNormal = NormalDatesResultArray.get(index);
                    //returnList2.add(temp);
                }

                else {
                    index = NormalDatesResultArray.size()/2;
                    temp = NormalDatesResultArray.get(index);
                    temp += NormalDatesResultArray.get(index-1);
                    temp /=2;
                    MedianNormal = temp;
                }



                //System.out.println("\n");
                //for(int i = 0;i<demandedCnt2;i++) {
                //	System.out.println(i+": "+DemandedDatesResultArray.get(i)+", ");
                //}
                //System.out.println("\n");
                //for(int i = 0;i<normalCnt2;i++) {
                //	System.out.println(i+": "+NormalDatesResultArray.get(i)+", ");
                //}

                //System.out.print("\nMedianDemanded is: "+MedianDemanded+"\nMedianNormal is: "+MedianNormal);

                //System.out.print(DemandedDatesResultArray.get(demandedCnt2-1));
                //System.out.print(NormalDatesResultArray.get(normalCnt2-1));



                int[] DemandedDC = new int[10];
                int[] NormalDC = new int[10];

                double DemandedMaxParts = DemandedDatesResultArray.get(demandedCnt2-1)/10.0; //2 -> 0-2, 2-4, 4-6, 6-8.....18-20 -- 7
                double NormalMaxParts = NormalDatesResultArray.get(normalCnt2-1)/10.0;
                double PartPointer = 0.0;
                for(int i = 0;i<demandedCnt2;i++) {
                    for(int j = 0;j<10;j++) {
                        if(((DemandedMaxParts*j)<DemandedDatesResultArray.get(i)) && ((DemandedMaxParts*(j+1))>=DemandedDatesResultArray.get(i))) {
                            DemandedDC[j]++;
                        }
                    }
                }
                for(int i = 0;i<normalCnt2;i++) {
                    for(int j = 0;j<10;j++) {
                        if(((NormalMaxParts*j)<NormalDatesResultArray.get(i)) && ((NormalMaxParts*(j+1))>=NormalDatesResultArray.get(i))) {
                            NormalDC[j]++;
                        }
                    }
                }
                //	System.out.println("\n");
                //	System.out.println(Arrays.toString(DemandedDC));
                //	System.out.println("\n");
                //	System.out.println(Arrays.toString(NormalDC));



                try {
                    Stage stage = new Stage();
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/ViewLendingReportByManager.fxml"));
                    root = loader.load();
                    ViewLendingReportByManagerController LendingController = loader.getController();
                    LendingController.loadLendingReport(AvarageDemanded,AvarageNormal,MedianDemanded,MedianNormal,DemandedDC,NormalDC,DemandedMaxParts,NormalMaxParts);

                    Scene scene = new Scene(root);
                    stage.setTitle("View Book Lending Report");
	                stage.setScene(scene);
	                stage.show();
	                setStageIcon(stage);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                break;
            }
            case "Book Delays Report":
            {

                
                
               // System.out.println(BookDC[0][0]+", "+BookDC[0][1]+", "+BookDC[0][2]+", "+BookDC[0][3]+", "+BookDC[0][4]+", "+BookDC[0][5]);
               // System.out.println("\n"+BookDC[1][0]+", "+BookDC[1][1]+", "+BookDC[1][2]+", "+BookDC[1][3]+", "+BookDC[1][4]+", "+BookDC[1][5]);
                
                
                
                /*

                try {
                    Stage stage = new Stage();
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/ViewBooksDelaysReportByManager.fxml"));
                    root = loader.load();
                    ViewBooksDelaysReportByManagerController delayController = loader.getController();
                    delayController.loadDelayReport(returnList,    returnList2,  BookDC, MaxParts, BookDelayCnt, BookIDArray, Booklistsizes);
                    //avgArraylist   medianArray  BookDC[][]   MaxParts

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/


 		  /*	for(int i =0; i <arr.size();i++) {
				sum = 0L;
				for(int j=0; j < arr.get(i).size();j++) {
					sum += arr.get(i).get(j);
				}
				returnSum = (double)sum;
				returnList.add(returnSum/arr.get(i).size());
			}
			return returnList; */



                //for(int i =0; i <ResultArray.size();i++) {	//i cols
                //for(int j=0; j < 10;j++) { //j rows
                //System.out.println(Arrays.deepToString(BookDC));
                //sum += ResultArray.get(i).get(j);
                //}
                //returnSum = (double)sum;
                //returnList.add(returnSum/ResultArray.get(i).size());
                //}

                //System.out.println(BookDC[0][0]+", "+BookDC[0][1]+", "+BookDC[0][2]+", "+BookDC[0][3]+", "+BookDC[0][4]+", "+BookDC[0][5]);
                //System.out.println(Arrays.toString(BookDC[0]));

 		  	/*
 		  	int[] DemandedDC = new int[10];
 		  	int[] NormalDC = new int[10];

 		  	double DemandedMaxParts = DemandedDatesResultArray.get(demandedCnt2-1)/10.0; //2 -> 0-2, 2-4, 4-6, 6-8.....18-20 -- 7
 		  	double NormalMaxParts = NormalDatesResultArray.get(normalCnt2-1)/10.0;
 		  	double PartPointer = 0.0;
 		  	for(int i = 0;i<demandedCnt2;i++) {
 		  		for(int j = 0;j<10;j++) {
 		  			if(((DemandedMaxParts*j)<DemandedDatesResultArray.get(i)) && ((DemandedMaxParts*(j+1))>=DemandedDatesResultArray.get(i))) {
 		  				DemandedDC[j]++;
 		  			}
 		  		}
 		  	}
 		  	for(int i = 0;i<normalCnt2;i++) {
 		  		for(int j = 0;j<10;j++) {
 		  			if(((NormalMaxParts*j)<NormalDatesResultArray.get(i)) && ((NormalMaxParts*(j+1))>=NormalDatesResultArray.get(i))) {
 		  				NormalDC[j]++;
 		  			}
 		  		}
 		  	}
 		  	System.out.println("\n");
 		  	System.out.println(Arrays.toString(DemandedDC));
 		  	System.out.println("\n");
 		  	System.out.println(Arrays.toString(NormalDC));

*/

 		   try {
              	Stage stage = new Stage();
                  //AnchorPane root;
                  //FXMLLoader loader = new FXMLLoader();
                  //loader.setLocation(getClass().getResource("/gui/ViewBooksDelaysReportByManager.fxml"));
                  LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ViewBooksDelaysReportByManager.fxml"), "Main Page", null);
					//root = loader.load();
					//ViewLendingReportByManagerController LendingController = loader.getController();
					//LendingController.loadLendingReport(AvarageDemanded,AvarageNormal,MedianDemanded,MedianNormal,DemandedDC,NormalDC,DemandedMaxParts,NormalMaxParts);

	                //Scene scene = new Scene(root);
	               // stage.setScene(scene);
	               // stage.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}






                break;
            }
        }
    }

	 /** view old periodic report
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void btnViewOldStatisticReport(ActionEvent event) {
        {

            int pos = tblOldPeriodicActivitiesReports.getSelectionModel().getSelectedIndex();
            // @SuppressWarnings("unchecked")
            //ArrayList<String> list =
            String PeriodicActivitiesReportID = tblOldPeriodicActivitiesReports.getItems().get(pos).getPeriodicActivitiesReportID();
            String ProducerName = tblOldPeriodicActivitiesReports.getItems().get(pos).getProducerName();
            String ProductionDate = tblOldPeriodicActivitiesReports.getItems().get(pos).getProductionDate();



            String AmountOfBorrowedBookCopies = "",
                    AmountOfActiveReaders = "",
                    AmountOfFrozenReaders = "",
                    AmountOfLockedReaders = "",
                    AmountOfDelayedReaders = "";

            int size = resultstat.size();
            for(int s=0;s<size;s++) {
                if(resultstat.get(s).get(0).equals(PeriodicActivitiesReportID)) {

                    AmountOfBorrowedBookCopies = resultstat.get(s).get(3);
                    AmountOfActiveReaders = resultstat.get(s).get(4);
                    AmountOfFrozenReaders = resultstat.get(s).get(5);
                    AmountOfLockedReaders = resultstat.get(s).get(6);
                    AmountOfDelayedReaders = resultstat.get(s).get(7);

                }
            }


            ArrayList<String> StatInfo = new ArrayList<String>();
            StatInfo.add(PeriodicActivitiesReportID);
            StatInfo.add(ProducerName);
            StatInfo.add(AmountOfActiveReaders);
            StatInfo.add(AmountOfFrozenReaders);
            StatInfo.add(AmountOfLockedReaders);
            StatInfo.add(AmountOfDelayedReaders);
            StatInfo.add(AmountOfBorrowedBookCopies);
            StatInfo.add(ProductionDate);


            //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
            //	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");


            try {
                Stage stage = new Stage();
                Parent root;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/ViewPeriodicActivitiesReportByManager.fxml"));
                root = loader.load();
                ViewPeriodicActivitiesReportByManagerController ActivitiesController = loader.getController();
                ActivitiesController.loadActivitiesReport(StatInfo);

                //BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());

                //BookController.loadAnchor(AnchorPaneTabs);

                Scene scene = new Scene(root);
                stage.setTitle("View Periodic Activities Report");
                stage.setScene(scene);
                stage.show();
                setStageIcon(stage);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
	 /**refreshing book table info
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void RefreshBookImgbtn(MouseEvent event) {
       // JFXButton yesButton = new JFXButton("YES, Please");
        //JFXButton noButton = new JFXButton("No, Please");
       // AlertMaker.showMaterialDialog(somthing, MainAnchor, Arrays.asList(yesButton, noButton), "Confirm Renew Operation", "Are you sure want to renew the book ?");
    	MylistSubBook.clear();
    	loadBookData();
    }
	 /**refreshing reader table info
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void RefreshReaderImgbtn(MouseEvent event) {
    	MylistSubReader.clear();
    	loadReaderData();
    }
	 /**refreshing worker table info
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void RefreshWorkerImgbtn(MouseEvent event) {
    	MylistSubworker.clear();
    	loadWorkerData();
    }
	 /**init combobox
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void cmbxBookSearchTypes(ActionEvent event) {/**Initialize the PromptText of cmbxBookSearchTypes*/
    	switch(cmbxBookSearchTypes.getValue()) 
        { 
            case "Book Name": 
            	bookIDInput.setPromptText("i.e: For Cluck's Sake!");
                break; 
            case "Book Author": 
            	bookIDInput.setPromptText("i.e: Author1 OR Author1, Authr2,..");
                break; 
            case "Book Subject": 
            	bookIDInput.setPromptText("i.e: Crime OR Food OR Humor...");
                break; 
            case "Free Text": 
            	bookIDInput.setPromptText("i.e: lord voldemort, best seller...");
                break; 
        } 
    	
    }
	 /**loading the worker information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void loadworkerInfo(ActionEvent event) {/** brings in relevant data worker Info from the DB  and Initialize tabls,The function has an action that displays data in the table by double-clicking*/

    	
    	String Fullname;
    	int i,size2,size,cnt=1;
    	if(memberIDInput1.getText().trim() != "") {
    		size2 = resultuser.size();
    		size = resultworker.size();
    		
        	switch(cmbxWorkerSearchTypes.getValue()) 
            { 
                case "First Name": 
                	cnt=1;
                	MylistSubworker.clear();
                	tblWorkerSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultworker.get(j).get(0))) {
           					   if(resultuser.get(i).get(1).toUpperCase().contains(memberIDInput1.getText().trim().toUpperCase())) {
           						 Fullname = (resultuser2.get(i).get(1)+" "+resultuser2.get(i).get(2));
         					    String  permissions = (resultuser2.get(i).get(7));
         					   	String rownum = String.valueOf(cnt);
         		                String WorkerNumber = resultworker.get(j).get(1);
         		               
         		                cnt++;
           		             MylistSubworker.add(new Subworker(rownum, WorkerNumber, Fullname, permissions)); 
           					   }
           					    
                        }
                    }
                	  }
                	  tblWorkerSearchResults.setItems(MylistSubworker);
                    break; 
                case "Last Name": 
                	cnt=1;
                	MylistSubworker.clear();
                	tblWorkerSearchResults.getItems().clear();
                
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultworker.get(j).get(0))) {
           					   if(resultuser.get(i).get(2).toUpperCase().contains(memberIDInput1.getText().trim().toUpperCase())) {
           						 Fullname = (resultuser2.get(i).get(1)+" "+resultuser2.get(i).get(2));
          					    String  permissions = (resultuser2.get(i).get(7));
          					   	String rownum = String.valueOf(cnt);
          		                String WorkerNumber = resultworker.get(j).get(1);
          		               
          		                cnt++;
            		             MylistSubworker.add(new Subworker(rownum, WorkerNumber, Fullname, permissions)); 
           					   }
                        }
                    }
                	  }
                	  tblWorkerSearchResults.setItems(MylistSubworker);
                    break; 
                case "ID": 
                	cnt=1;
                	MylistSubworker.clear();
                	tblWorkerSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultworker.get(j).get(0))) {
           					   if(resultuser.get(i).get(3).contains(memberIDInput1.getText().trim())) {
           						 Fullname = (resultuser2.get(i).get(1)+" "+resultuser2.get(i).get(2));
          					    String  permissions = (resultuser2.get(i).get(7));
          					   	String rownum = String.valueOf(cnt);
          		                String WorkerNumber = resultworker.get(j).get(1);
          		               
          		                cnt++;
            		             MylistSubworker.add(new Subworker(rownum, WorkerNumber, Fullname, permissions)); 
           					   }
                        }
                    }
                	  }
                	  tblWorkerSearchResults.setItems(MylistSubworker);
                	
                    break; 
                case "worker Number": 
                	cnt=1;
                	MylistSubworker.clear();
                	tblWorkerSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultworker.get(j).get(0))) {
           					   if(resultworker.get(j).get(1).contains(memberIDInput1.getText().trim())) {
           						 Fullname = (resultuser2.get(i).get(1)+" "+resultuser2.get(i).get(2));
          					    String  permissions = (resultuser2.get(i).get(7));
          					   	String rownum = String.valueOf(cnt);
          		                String WorkerNumber = resultworker.get(j).get(1);
          		               
          		                cnt++;
            		             MylistSubworker.add(new Subworker(rownum, WorkerNumber, Fullname, permissions)); 
           					   }
           				   }
           			   }
                   
                	  } 
                	  
                	  tblWorkerSearchResults.setItems(MylistSubworker);
                      break; 
                	  
             }               	
                	  
        }
    	
    	
    }

	 /**loading the book information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void loadBookInfo(ActionEvent event) {/** brings in relevant data BookInfo from the DB  and Initialize tabls,The function has an action that displays data in the table by double-clicking*/
    	int i,size,cnt=1;
    	if(bookIDInput.getText().trim() != "") {
    		size = resultbook.size();
        	switch(cmbxBookSearchTypes.getValue()) 
            { 
                case "Book Name": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                        String bookname = resultbook.get(i).get(0);
                        if(bookname.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String author = resultbook.get(i).get(1);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            String archive = resultbook.get(i).get(6);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Book Author": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                	//StringBuilder stringBuilder = new StringBuilder();
                	//for(i=0;i<size;i++) {
                	//	stringBuilder.append(resultbook.get(i).get(1)+", ");
                	//}
             
                	//String finalString = stringBuilder.toString();
                    for(i=0;i<size;i++) {               
                    	String author = resultbook.get(i).get(1);
                    	if(author.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) { //need to split to tokkens... 
                            String rownum = String.valueOf(cnt);
                            String bookname = resultbook.get(i).get(0);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Book Subject": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                        String subject = resultbook.get(i).get(2);
                        if(subject.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String author = resultbook.get(i).get(1);
                            String bookname = resultbook.get(i).get(0);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Free Text": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                    	String description = resultbook.get(i).get(4);
                        if(description.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String bookname = resultbook.get(i).get(0);
                            String author = resultbook.get(i).get(1);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
            } 
    	}
    	
    }


	 /**loading the reader information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void loadMemberInfo(ActionEvent event) {/** brings in relevant data BookInfo from the DB  and Initialize tabls,The function has an action that displays data in the table by double-clicking*/
    	String Fullname;
    	int i,size2,size,cnt=1;
    	if(memberIDInput.getText().trim() != "") {
    		size2 = resultuser.size();
    		size = resultreader.size();
    		
        	switch(cmbxReaderSearchTypes.getValue()) 
            { 
                case "First Name": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(1).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           						Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus)); 
           					   }
           					    
                        }
                    }
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                    break; 
                case "Last Name": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(2).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
                        }
                    }
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                    break; 
                case "ID": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(3).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
                        }
                    }
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                	
                    break; 
                case "Reader Card Number": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultreader.get(j).get(1).contains(memberIDInput.getText().trim())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
           				   }
           			   }
                   
                	  } 
                	  
                	  tblReaderSearchResults.setItems(MylistSubReader);
                      break; 
                	  
             }               	
                	  
        }
    }

 
	 /**init window
   	 * @param url d
   	 * @param rb f
   	 * 
   	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {/**Initialize*/
    	//list = FXCollections.observableArrayList();
    	//Mylist = FXCollections.observableArrayList();
    	initCol();
    	loadData();
    	loadBookData();
    	loadReaderData();
    	loadWorkerData() ;
    	loadStatData();
    	setBookSearchComboBox();
    	setReaderSearchComboBox();
    	setcmbxWorkerSearchTypes();
    	setStatisticReportTypeComboBox();
    }

	 /**load data to the tables
  	 * @param  3
  	 * @param  e
  	 *
  	 */
    private void loadData() {/** brings in relevant data from the DB and Initialize tabl*/
        
    	int k,i, size1, size2,size3 ,size4, cnt=1;
    	Mylist.clear();
        String Fullname1 = null, Fullname2=null;
  	  	ArrayList<String> key1 = new ArrayList<String>();
  	  	ArrayList<String> key2 = new ArrayList<String>();
  	  	ArrayList<String> key3 = new ArrayList<String>();
  	  	ArrayList<String> key4 = new ArrayList<String>();
  	    ArrayList<String> askForResult1 = new ArrayList<String>();
  	    ArrayList<String> askForResult2 = new ArrayList<String>();
  	    ArrayList<String> askForResult3 = new ArrayList<String>();
  	    ArrayList<String> askForResult4 = new ArrayList<String>();
  	    
          String sql = "SELECT * FROM g17db.tblreader;";
  		   askForResult1.add("UserID");
  		   askForResult1.add("ReaderCardNumber");
  		   askForResult1.add("AmountOfBookDelays");

  		
  		resultreader = Functions.askFromDB(sql ,key1,askForResult1);
  		size1 = resultreader.size();

  		  sql = "SELECT * FROM g17db.tbluser;";
  		   askForResult2.add("UserID");
  		   askForResult2.add("FirstName");
  		   askForResult2.add("LastName");

  		
  		   resultuser = Functions.askFromDB(sql ,key2,askForResult2);
  		   size2 = resultuser.size();
  		
   		  sql = "SELECT * FROM g17db.tblworker;";
   		  askForResult3.add("UserID");
   		   askForResult3.add("WorkerID");

   		
   		    resultworker = Functions.askFromDB(sql ,key3,askForResult3);
   		     size3 = resultworker.size();
   		   
    	 sql = "SELECT * FROM g17db.tbldisciplinaryinquiryreport;";
  		  askForResult4.add("DisciplinaryInquiryReportID");
  		  askForResult4.add("ReaderCardNumber");
  		  askForResult4.add("WorkerNumber");
  		  askForResult4.add("Content");
  		  askForResult4.add("Verdict");
  		  askForResult4.add("Date");

       		
       		  result = Functions.askFromDB(sql ,key4,askForResult4);
       		   size4 = result.size();
   		   
   		   
   		   
   		   for(k = 0 ;k<size4;k++) {
   			   
  		   for(i = 0;i<size2;i++) {
  			   for(int j=0;j<size1;j++) {
  				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))&&(result.get(k).get(1).equals(resultreader.get(j).get(1)))) {
  					    Fullname1 = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
  					    break;
  					    
  				   }
  			   }
  		   }
  		   

  		   
  		   for(i = 0;i<size2;i++) {
  			   for(int j=0;j<size3;j++) {
  				  if((resultuser.get(i).get(0).equals(resultworker.get(j).get(0))&&(result.get(k).get(1).equals(resultreader.get(j).get(1))))) {
  		  			
  					    Fullname2 = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
  					    break;
  				   }
  			   }
  		   }
  		   
  	
           String rownum = String.valueOf(cnt);
           String report = result.get(k).get(0);
           String readername = Fullname1;
           String readercard = result.get(k).get(1);
           String managername = Fullname2;
           String verdict = result.get(k).get(4);
           String date = result.get(k).get(5);
           cnt++;
  		   
  		                Mylist.add(new DisciplinaryInquiryReport(rownum, report, readername, readercard,managername,verdict,date));
  				   }
   		tblDisciplinaryInquiryReport.setItems(Mylist);
   		
   		
   
    
    

   		
}
    public AnchorPane getAnchor() {
    	return AnchorPaneTabs;
    }

	 /**init combobox
 	 * @param 
 	 * @param 
 	 * @return void
 	 */
 private void setStatisticReportTypeComboBox() {
		
 		ArrayList<String> al = new ArrayList<String>();	
 		al.add("Periodic Activities Report");
 		al.add("Book Lending Report");
 		al.add("Book Delays Report");
 		ObservableList<String> CMBXStatisticReportType = FXCollections.observableArrayList(al);	
 		cmbxStatisticReportType.setItems(CMBXStatisticReportType);  	
 		}
 /**init combobox
	 * @param 
	 * @param 
	 * @return void
	 */
 private void setcmbxWorkerSearchTypes() {/**initialization cmbxWorkerSearchTypes*/
		
  		ArrayList<String> al = new ArrayList<String>();	
  		al.add("First Name");
  		al.add("Last Name");
  		al.add("ID");
  		al.add("worker Number");
  		ObservableList<String> CMBXWorkerSearchlist = FXCollections.observableArrayList(al);	
  		cmbxWorkerSearchTypes.setItems(CMBXWorkerSearchlist);  	
  		}
 /**init combobox
	 * @param 
	 * @param 
	 * @return void
	 */
    private void setBookSearchComboBox() {/**initialization cmbxBookSearchTypes*/
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Book Name");
		al.add("Book Author");
		al.add("Book Subject");
		al.add("Free Text");
		ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
		cmbxBookSearchTypes.setItems(CMBXBookSearchlist);  	
		}
	 /**init combobox
	 * @param 
	 * @param 
	 * @return void
	 */
    private void setReaderSearchComboBox() {/**initialization cmbxReaderSearchTypes*/
		
  		ArrayList<String> al = new ArrayList<String>();	
  		al.add("First Name");
  		al.add("Last Name");
  		al.add("ID");
  		al.add("Reader Card Number");
  		ObservableList<String> CMBXReaderSearchlist = FXCollections.observableArrayList(al);	
  		cmbxReaderSearchTypes.setItems(CMBXReaderSearchlist);  	
  		}
	 /**class for the table
	
	 */
    public static class DisciplinaryInquiryReport {/**Class especially for table of Disciplinary Inquiry Report*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty DisciplinaryInquiryReportID;
        private final SimpleStringProperty ReaderName;
        private final SimpleStringProperty ReaderCardNumber;
        private final SimpleStringProperty ManagerName;
        private final SimpleStringProperty Verdict;
        private final SimpleStringProperty Date;



        
        public DisciplinaryInquiryReport(String rownum,String report, String readername, String readercard, String managername, String verdict,  String date) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.DisciplinaryInquiryReportID = new SimpleStringProperty(report);
        	this.ReaderName = new SimpleStringProperty(readername);
        	this.ReaderCardNumber = new SimpleStringProperty(readercard);
        	this.ManagerName = new SimpleStringProperty(managername);
        	this.Verdict = new SimpleStringProperty(verdict);
        	this.Date = new SimpleStringProperty(date);
        }
        	
		public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getDisciplinaryInquiryReportID() {
        	return DisciplinaryInquiryReportID.get();
        }
        public void setDisciplinaryInquiryReportID(String report) {
        	DisciplinaryInquiryReportID.set(report);
        }
        
        public String getReaderName() {
        	return ReaderName.get();
        }
        public void setReaderName(String readername) {
        	ReaderName.set(readername);
        }
        
        public String getReaderCardNumber() {
        	return ReaderCardNumber.get();
        }
        public void setReaderCardNumber(String readercard) {
        	ReaderCardNumber.set(readercard);
        }
         
        public String getManagerName() {
        	return ManagerName.get();
        }
        public void setManagerName(String managername) {
        	ManagerName.set(managername);
        }
        
        public String getVerdict() {
        	return Verdict.get();
        }
        public void setVerdict(String verdict) {
        	Verdict.set(verdict);
        }
         
        public String getDate() {
        	return Date.get();
        }
        public void setDate(String date) {
        	Date.set(date);
        }
        
        
        
    }
	 /**init tables
	 * @param 
	 * @param 
	 * @return void
	 */
    private void initCol() {/**initialization table*/
    	

    	   

    	tblDisciplinaryInquiryReport.setEditable(true);
    	tblcolDisRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolDisReportID.setCellValueFactory(new PropertyValueFactory<>("DisciplinaryInquiryReportID"));
    	tblcolDisReaderName.setCellValueFactory(new PropertyValueFactory<>("ReaderName"));
    	tblcolDisReaderCardNumber.setCellValueFactory(new PropertyValueFactory<>("ReaderCardNumber"));
    	tblcolDisProducerName.setCellValueFactory(new PropertyValueFactory<>("ManagerName"));
    	tblcolDisVerdict.setCellValueFactory(new PropertyValueFactory<>("Verdict"));
    	tblcolDisProductionDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    	tblBookSearchResults.setEditable(true);
    	tblcolBookRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tblcolBookSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
    	tblcolBookStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    	////////
    	
    	tblReaderSearchResults.setEditable(true);
    	tblcolReaderRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolReaderCardNumber.setCellValueFactory(new PropertyValueFactory<>("ReaderCardNumber"));
    	tblcolReaderFullName.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
    	tblcolReaderStatus.setCellValueFactory(new PropertyValueFactory<>("CardStatus"));
    	///////////////////////////////////
    	tblWorkerSearchResults.setEditable(true);
    	tblcolWorkerRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolWorkerNumber.setCellValueFactory(new PropertyValueFactory<>("workerNumber"));
    	tblcolWorkerFullName.setCellValueFactory(new PropertyValueFactory<>("fullname"));
    	tblcolWorkerPermissions.setCellValueFactory(new PropertyValueFactory<>("Premission"));
        ////////////////////////////////
        tblOldPeriodicActivitiesReports.setEditable(true);
        tblcolStatRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
        tblcolStatReportID.setCellValueFactory(new PropertyValueFactory<>("PeriodicActivitiesReportID"));
        tblcolStatProducerName.setCellValueFactory(new PropertyValueFactory<>("ProducerName"));
        tblcolStatProductionDate.setCellValueFactory(new PropertyValueFactory<>("ProductionDate"));

    }

    public void start(Stage primaryStage) throws Exception {
    	//pStage = primaryStage;
    	//ToolbarController tb = new ToolbarController();
    	//tb.loadStage((Stage) rootPane.getScene().getWindow());
    	//this.pStage = primaryStage;
 		Parent root = FXMLLoader.load(getClass().getResource("/gui/main2.fxml"));
 		pScene = new Scene(root);
 		pScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
 		primaryStage.setTitle("Main");
 		primaryStage.setScene(pScene);
 		primaryStage.show();
 		LibraryAssistantUtil.setStageIcon(primaryStage);
 		
 	}
	 /**load statistic table data
	 * @param 
	 * @param 
	 * @return void
	 */
    private void loadStatData() {
        int i = 0,size;

        MylistSubStat.clear();
        ArrayList<String> key = new ArrayList<String>();

        ArrayList<String> askForResult = new ArrayList<String>();

        String sql = "SELECT * FROM g17db.tblperiodicactivitiesreport;";
        askForResult.add("PeriodicActivitiesReportID");
        askForResult.add("ProducerName");
        askForResult.add("ProductionDate");
        askForResult.add("AmountOfBorrowedBookCopies");
        askForResult.add("AmountOfActiveReaders");
        askForResult.add("AmountOfLockedReaders");
        askForResult.add("AmountOfLockedReaders");
        askForResult.add("AmountOfDelayedReaders");


        String rownum = "";
        String periodicActivitiesReportID = "";
        String producerName = "";
        String productionDate = "";

        resultstat = Functions.askFromDB(sql ,key,askForResult);
        size = resultstat.size();
        try {
            for(i=0;i<size;i++) {
                rownum = String.valueOf(i+1);
                periodicActivitiesReportID = resultstat.get(i).get(0);
                producerName = resultstat.get(i).get(1);
                productionDate = resultstat.get(i).get(2);


                MylistSubStat.add(new SubStat(rownum, periodicActivitiesReportID, producerName, productionDate));
                // MylistSubBook.add(new Book(bookname, author, subject, status));

            }
            tblOldPeriodicActivitiesReports.setItems(MylistSubStat);
        } catch (Exception ex) {

        }


       /* tblReaderSearchResults.setOnMouseClicked(event -> {
        	int pos = tblOldPeriodicActivitiesReports.getSelectionModel().getSelectedIndex();
    	  	// @SuppressWarnings("unchecked")
    	//ArrayList<String> list =
    	 String PeriodicActivitiesReportID = tblOldPeriodicActivitiesReports.getItems().get(pos).getPeriodicActivitiesReportID();
    	 String ProducerName = tblOldPeriodicActivitiesReports.getItems().get(pos).getProducerName();
    	 String ProductionDate = tblOldPeriodicActivitiesReports.getItems().get(pos).getProductionDate();



    	String AmountOfBorrowedBookCopies = "",
    			AmountOfActiveReaders = "",
    			AmountOfFrozenReaders = "",
    			AmountOfLockedReaders = "",
    			AmountOfDelayedReaders = "";

    	int size = resultstat.size();
    	 for(int s=0;s<size;s++) {
    		 if(resultstat.get(s).get(0).equals(PeriodicActivitiesReportID)) {

    			 AmountOfBorrowedBookCopies = resultstat.get(s).get(3);
    			 AmountOfActiveReaders = resultstat.get(s).get(4);
    			 AmountOfFrozenReaders = resultstat.get(s).get(5);
    			 AmountOfLockedReaders = resultstat.get(s).get(6);
    			 AmountOfDelayedReaders = resultstat.get(s).get(7);

    		 }
    	 }


    	 ArrayList<String> StatInfo = new ArrayList<String>();
    	 StatInfo.add(PeriodicActivitiesReportID);
    	 StatInfo.add(ProducerName);
    	 StatInfo.add(AmountOfActiveReaders);
    	 StatInfo.add(AmountOfFrozenReaders);
    	 StatInfo.add(AmountOfLockedReaders);
    	 StatInfo.add(AmountOfDelayedReaders);
    	 StatInfo.add(AmountOfBorrowedBookCopies);
    	 StatInfo.add(ProductionDate);


    	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
    		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");


    	try {
       	Stage stage = new Stage();
           Parent root;
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/gui/ViewPeriodicActivitiesReportByManager.fxml"));
			root = loader.load();
			ViewPeriodicActivitiesReportByManagerController ActivitiesController = loader.getController();
				ActivitiesController.loadActivitiesReport(StatInfo);

			//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());

			//BookController.loadAnchor(AnchorPaneTabs);

           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
        }*/
    }
	 /**class for the statistic table
	
	 */
    public static class SubStat {

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty PeriodicActivitiesReportID;
        private final SimpleStringProperty ProducerName;
        private final SimpleStringProperty ProductionDate;


        public SubStat(String rownum,String periodicActivitiesReportID, String producerName, String productionDate) {
            this.RowNum = new SimpleStringProperty(rownum);
            this.PeriodicActivitiesReportID = new SimpleStringProperty(periodicActivitiesReportID);
            this.ProducerName = new SimpleStringProperty(producerName);
            this.ProductionDate = new SimpleStringProperty(productionDate);

        }

        public String getRowNum() {
            return RowNum.get();
        }
        public void setRowNum(String rownum) {
            RowNum.set(rownum);
        }

        public String getPeriodicActivitiesReportID() {
            return PeriodicActivitiesReportID.get();
        }
        public void setPeriodicActivitiesReportID(String periodicActivitiesReportID) {
            PeriodicActivitiesReportID.set(periodicActivitiesReportID);
        }

        public String getProducerName() {
            return ProducerName.get();
        }
        public void setProducerName(String producerName) {
            ProducerName.set(producerName);
        }

        public String getProductionDate() {
            return ProductionDate.get();
        }
        public void setProductionDate(String productionDate) {
            ProductionDate.set(productionDate);
        }


    }
	 /**load books data to the table
	 * @param 
	 * @param 
	 * @return void
	 */
    private void loadBookData() {/** brings in relevant data from the DB and Transfer  information between windows ,opnning window*/
    	int i = 0,size;

    	MylistSubBook.clear();
	  	ArrayList<String> key = new ArrayList<String>();

	    ArrayList<String> askForResult = new ArrayList<String>();

		  String sql = "SELECT * FROM g17db.tblbook;";
		  askForResult.add("BookName");
		  askForResult.add("AuthorName");
		  askForResult.add("Subject");
		  askForResult.add("AmountAvailable");
		  askForResult.add("Description");
		  askForResult.add("BookID");
		  askForResult.add("ArchiveStatus");
		  askForResult.add("CatalogNumber");
		  askForResult.add("PublishedDate");
		  askForResult.add("PurchaseDate");
		  askForResult.add("TableOfContents");
		  askForResult.add("ExistingAmount");
		  askForResult.add("Location");
		  askForResult.add("Popularity");
		  askForResult.add("EditionNumber");
	
		  resultbook = Functions.askFromDB(sql ,key,askForResult);
		  size = resultbook.size();
        try {
            for(i=0;i<size;i++) {
                String rownum = String.valueOf(i+1);
                String bookname = resultbook.get(i).get(0);
                String author = resultbook.get(i).get(1);
                String subject = resultbook.get(i).get(2);
                String status = resultbook.get(i).get(3);
                String bookid = resultbook.get(i).get(5);
                String archive = resultbook.get(i).get(6);
                if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                else status = "Unavailable";

                MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
               // MylistSubBook.add(new Book(bookname, author, subject, status));

            }
        } catch (Exception ex) {
            
        }
        
        tblBookSearchResults.setItems(MylistSubBook);
        tblBookSearchResults.setOnMouseClicked(event -> {
        	if (event.getClickCount() == 2 ) {

             	 int pos = tblBookSearchResults.getSelectionModel().getSelectedIndex();
             	  	// @SuppressWarnings("unchecked")
             	//ArrayList<String> list = 
             	 String BookID = tblBookSearchResults.getItems().get(pos).getBookID();
             	 String BookName = tblBookSearchResults.getItems().get(pos).getBookName();
             	 String AuthorName = tblBookSearchResults.getItems().get(pos).getAuthorName();
             	 String Subject = tblBookSearchResults.getItems().get(pos).getSubject();
             	String CatalogNumber = "",
             	       Description = "",
             	       PublishedDate = "",
             	       PurchaseDate = "",
             	       AmountAvailable = "",
             	       ExistingAmount = "",
             	       ArchiveStatus = "",
             	       Location = "",
             	       Popularity = "",
             	       TableOfContents = "",
             	       EditionNumber = "";
             	
             	int size5 = resultbook.size();
             	 for(int s=0;s<size5;s++) {
             		 if(resultbook.get(s).get(5).equals(BookID)) {

             			  CatalogNumber = resultbook.get(s).get(7);
             		      Description = resultbook.get(s).get(4);
             		      PublishedDate = resultbook.get(s).get(8);
             		      PurchaseDate = resultbook.get(s).get(9);
             		      AmountAvailable = resultbook.get(s).get(3);
             		      ExistingAmount = resultbook.get(s).get(11);
             		      ArchiveStatus = resultbook.get(s).get(6);
             		      Location = resultbook.get(s).get(12);
             		      Popularity = resultbook.get(s).get(13);
             		      TableOfContents = resultbook.get(s).get(10);
             		      EditionNumber = resultbook.get(s).get(14);
             		 }
             	 }


             	 ArrayList<String> BookInfo = new ArrayList<String>();
             	BookInfo.add(CatalogNumber);
             	BookInfo.add(BookName);
             	BookInfo.add(AuthorName);
             	BookInfo.add(Subject);
             	if(ArchiveStatus.equals("Archived")||ArchiveStatus.equals("Frozen")){
             		BookInfo.add(ArchiveStatus);
             		BookInfo.add(ArchiveStatus);
             	}
             	else {
             		BookInfo.add(AmountAvailable);
             		BookInfo.add(ExistingAmount);
             	}
             	
             	BookInfo.add(Location);
             	BookInfo.add(Popularity);
             	BookInfo.add(EditionNumber);
             	BookInfo.add(PublishedDate);
             	BookInfo.add(PurchaseDate);
             	BookInfo.add(TableOfContents);
             	BookInfo.add(Description);
             	BookInfo.add(BookID);
             	
             	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
             		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");
             
             	
             	try {
                	Stage stage = new Stage(); 
                    Parent root;                   
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/BookDetailsByLibrarian.fxml"));
					root = loader.load();
					BookDetailsByLibrarianController BookController = loader.getController();
					BookController.loadBook(BookInfo,MyUserData);
					BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
					
					BookController.loadAnchor(AnchorPaneTabs);
					
	                Scene scene = new Scene(root);
	                stage.setTitle("Book Details");
	                stage.setScene(scene);
	                stage.show();
	                setStageIcon(stage);
	                
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
             	
             }
         }); 
    }
	 /**load reader information to the table
	 * @param 
	 * @param 
	 * @return void
	 */
        private void loadReaderData()/** brings in relevant data from the DB and Transfer  information between windows ,opnning window*/
        {
        //////////////////////////////////////////////////////
        int i, size, size2,cnt=1;
        MylistSubReader.clear();
        String Fullname;
	  	ArrayList<String> key1 = new ArrayList<String>();
	  	ArrayList<String> key2 = new ArrayList<String>();
	    ArrayList<String> askForResult1 = new ArrayList<String>();
	    ArrayList<String> askForResult2 = new ArrayList<String>();
	    
        String sql = "SELECT * FROM g17db.tblreader;";
		   askForResult1.add("UserID");
		   askForResult1.add("ReaderCardNumber");
		   askForResult1.add("CardStatus");
		   askForResult1.add("PhoneNumber");
		
		   resultreader = Functions.askFromDB(sql ,key1,askForResult1);
		size = resultreader.size();

		  sql = "SELECT * FROM g17db.tbluser;";
		  askForResult2.add("UserID");
		   askForResult2.add("FirstName");
		   askForResult2.add("Lastname");
		   askForResult2.add("ID");
		   askForResult2.add("Email");
		   askForResult2.add("Username");
		   askForResult2.add("Password");
		
		   resultuser = Functions.askFromDB(sql ,key2,askForResult2);
		   size2 = resultuser.size();
		   for(i = 0;i<size2;i++) {
			   for(int j=0;j<size;j++) {
				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
					   	String rownum = String.valueOf(cnt);
		                String ReaderCardNumber = resultreader.get(j).get(1);
		                String CardStatus = resultreader.get(j).get(2);
		                cnt++;
		                MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
				   }
			   }
		   }
		   
		   
		   
     tblReaderSearchResults.setItems(MylistSubReader);
    // tblReaderSearchResults.setOnMouseClicked(e -> {Readerevents();});
     
     
     tblReaderSearchResults.setOnMouseClicked(event -> {
         if (event.getClickCount() == 2 ) {
        	String PhoneNumber = "";
         	String Email = "";
         	String Username = "";
         	String Password = "";
         	String ID = "";
         	String UserID = "";
         	 int pos = tblReaderSearchResults.getSelectionModel().getSelectedIndex();
         	  	// @SuppressWarnings("unchecked")
         	//ArrayList<String> list = 
         	 String ReaderCardNumber = tblReaderSearchResults.getItems().get(pos).getReaderCardNumber();
         	 String Status = tblReaderSearchResults.getItems().get(pos).getCardStatus();
         	 String Fullname2 = tblReaderSearchResults.getItems().get(pos).getFullname();
         	 String arr[] = Fullname2.split(" ", 2);
         	 String firstname = arr[0]; 
         	 String lastname = arr[1];
         	 int size3 = resultreader.size();
         	 int size4 = resultuser.size();
         	 
         	 
         	 for(int k=0;k<size3;k++) {
         		 if(resultreader.get(k).get(1).equals(ReaderCardNumber)) {
         			 UserID = resultreader.get(k).get(0);
         			 PhoneNumber = resultreader.get(k).get(3);
         		 }
         	 }
         	 for(int k=0;k<size4;k++) {
         		 if(resultuser.get(k).get(0).equals(UserID)) {
         			 ID = resultuser.get(k).get(3); 
         			 Email = resultuser.get(k).get(4);
         			 Username = resultuser.get(k).get(5);
         			 Password = resultuser.get(k).get(6);
         		 }
         	 }

         	 ArrayList<String> ReaderInfo = new ArrayList<String>();
         	 ReaderInfo.add(firstname);
         	 ReaderInfo.add(lastname);
         	 ReaderInfo.add(ID);
         	 ReaderInfo.add(PhoneNumber);
         	 ReaderInfo.add(Email);
         	 ReaderInfo.add(Username);
         	 ReaderInfo.add(Password);
         	 ReaderInfo.add(ReaderCardNumber);
         	 ReaderInfo.add(Status);
         	 ReaderInfo.add(UserID);
         	 //System.out.println(firstname+" "+lastname+" "+ID+" "+PhoneNumber+" "+Email+" "+Username+" "+Password+" "+ReaderCardNumber+" "+Status+" ");

         	  
         	 //MainAnchor.getScene().getWindow().hide();
         	//((Node)Readerevents.getSource()).getScene().getWindow().hide(); //hiding primary window
         	 
         	/*Stage primaryStage = new Stage();
         	FXMLLoader loader = new FXMLLoader();
         	AnchorPane root;
         	try {
         		root = loader.load(getClass().getResource("/gui/ViewReaderPersonalInformationByManager.fxml").openStream());
         		ViewReaderPersonalInformationByManagerController ViewReaderController = loader.getController();
         		
         		ViewReaderController.loadReader(ReaderInfo);
         		Scene ReaderScene = new Scene(root);			
         		ReaderScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
         		primaryStage.setScene(ReaderScene);		
         		primaryStage.show();
         	} catch (IOException e) {
         		// TODO Auto-generated catch block
         		e.printStackTrace();
         	}*/
         	
         	try {
            	Stage stage = new Stage(); 
                Parent root;                   
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/ViewReaderPersonalInformationByManager.fxml"));
				root = loader.load();
				ViewReaderPersonalInformationByManagerController ViewReaderController = loader.getController();
				ViewReaderController.loadReader(ReaderInfo);
				ViewReaderController.loadUser(MyUserData);
				//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
				
				ViewReaderController.loadAnchor(AnchorPaneTabs);
				
                Scene scene = new Scene(root);
                stage.setTitle("Reader Personal Information");
                stage.setScene(scene);
                stage.show();
                setStageIcon(stage);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
         	
         	
         }
     });
     
     
// tblReaderSearchResults.setOnMouseClicked(e -> {Readerevents();});
		   
     
     
     
    }
   	 /**transerfing information to this window
    	 * @param thisUserData s
    	 *
    	 * 
    	 */
        public void setUser(User thisUserData) {///// brings in relevant data from the DB and Transfer  information between windows ,opnning window
        	this.MyUserData = thisUserData;
        }
   	 /**load worker information to the table
    
    	 */
    private void loadWorkerData() {///// brings in relevant data from the DB and Transfer  information between windows ,opnning window
    	int cnt2=1;
        String Fullname3;
        MylistSubworker.clear();
       // tblReaderSearchResults.setOnMouseClicked(e -> {Readerevents();});
      String  sql = "SELECT * FROM g17db.tblworker;";
        askForResult3.add("UserID"); 
        askForResult3.add("WorkerID");
        askForResult3.add("Occupation");;
        askForResult3.add("OrganizationalAffilation");
   		
        resultworker = Functions.askFromDB(sql ,key3,askForResult3);
   		int size = resultworker.size();


   		 sql = "SELECT * FROM g17db.tbluser;";
   		  askForResult4.add("UserID");
   		   askForResult4.add("FirstName");
   		   askForResult4.add("Lastname");
   		   askForResult4.add("ID");
   		   askForResult4.add("Email");
   		   askForResult4.add("Username");
   		   askForResult4.add("Password");
   		   askForResult4.add("Permissions");
   		   askForResult4.add("AddedDate");
   		   resultuser2 = Functions.askFromDB(sql ,key4,askForResult4);
   		 int  size2 = resultuser2.size();
   		   for(int i = 0;i<size2;i++) {
   			   for(int j=0;j<size;j++) {
   				   if(resultuser2.get(i).get(0).equals(resultworker.get(j).get(0))) {
   					   Fullname3 = (resultuser2.get(i).get(1)+" "+resultuser2.get(i).get(2));
   					    String  permissions = (resultuser2.get(i).get(7));
   					   	String rownum = String.valueOf(cnt2);
   		                String WorkerNumber = resultworker.get(j).get(1);
   		               
   		                cnt2++;
   		                MylistSubworker.add(new Subworker(rownum, WorkerNumber, Fullname3, permissions));
   				   }
   			   }
   		   }
       
   	   
   		   tblWorkerSearchResults.setItems(MylistSubworker);;
    	tblWorkerSearchResults.setOnMouseClicked(event -> {
	         if (event.getClickCount() == 2 ) {
	        	
	        	 String AddedDate = "";
	        	 String Occupation = "";
		         String OrganizationalAffiliation = "";
	         	String Email = "";
	         	String Username = "";
	         	String Password = "";
	         	String ID = "";
	         	String UserID = "";
	         	 int pos2 = tblWorkerSearchResults.getSelectionModel().getSelectedIndex();
	         	  	// @SuppressWarnings("unchecked")
	         	//ArrayList<String> list = 
	         	 String WorkerNumber = tblWorkerSearchResults.getItems().get(pos2).getWorkerNumber();
	         	 String Fullname2 = tblWorkerSearchResults.getItems().get(pos2).getFullname();
	        	 String permissions = tblWorkerSearchResults.getItems().get(pos2).getPremission();
	         	 String arr[] = Fullname2.split(" ", 2);
	         	 String firstname = arr[0]; 
	         	 String lastname = arr[1];
	         	 int size3 = resultworker.size();
	         	 int size4 = resultuser2.size();
	         	 
	         	 
	         	 for(int k=0;k<size3;k++) {
	         		 if(resultworker.get(k).get(1).equals(WorkerNumber)) {
	         			 UserID = resultworker.get(k).get(0);
	         			OrganizationalAffiliation=resultworker.get(k).get(3);
	         			Occupation=resultworker.get(k).get(2);
	         		 }
	         	 }
	         	 for(int k=0;k<size4;k++) {
	         		 if(resultuser2.get(k).get(0).equals(UserID)) {
	         			 ID = resultuser2.get(k).get(3);
	         			 Email = resultuser.get(k).get(4);
	         			 Username = resultuser2.get(k).get(5);
	         			 Password = resultuser2.get(k).get(6);
	         			AddedDate = resultuser2.get(k).get(8);
	         		 }
	         	 }

	         	 ArrayList<String> workerInfo = new ArrayList<String>();
	         	workerInfo.add(firstname);
	         	workerInfo.add(lastname);
	         	workerInfo.add(ID);
	         	workerInfo.add(Email);
	         	workerInfo.add(Username); 
	         	workerInfo.add(Password);
	         	workerInfo.add(WorkerNumber);
	         	workerInfo.add(Occupation);
	         	workerInfo.add(OrganizationalAffiliation);
	         	workerInfo.add(permissions);
	         	workerInfo.add(UserID);
	         	workerInfo.add(AddedDate);
	         	 //System.out.println(firstname+" "+lastname+" "+ID+" "+PhoneNumber+" "+Email+" "+Username+" "+Password+" "+ReaderCardNumber+" "+Status+" ");

	           
	         	 //MainAnchor.getScene().getWindow().hide();
	         	//((Node)Readerevents.getSource()).getScene().getWindow().hide(); //hiding primary window
	         	/*Stage primaryStage = new Stage();
	         	FXMLLoader loader = new FXMLLoader();
	         	Pane root;
	         	try {
	         		root = loader.load(getClass().getResource("/gui/ViewWorkerInformationByManager.fxml").openStream());
	         		ViewWorkerInformationByManagerController ViewWorkerController = loader.getController();
	         		
	         		ViewWorkerController.loadworker(workerInfo);
	         		Scene WorkerScene = new Scene(root);			
	         		//WorkerScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
	         		primaryStage.setScene(WorkerScene);		
	         		primaryStage.show();
	         	} catch (IOException e) {
	         		// TODO Auto-generated catch block
	         		e.printStackTrace();
	         	}*/
	         	
	         	
	         	try {
	            	Stage stage = new Stage(); 
	                Parent root;                   
	                FXMLLoader loader = new FXMLLoader();          
	                loader.setLocation(getClass().getResource("/gui/ViewWorkerInformationByManager.fxml"));
					root = loader.load();
					ViewWorkerInformationByManagerController ViewWorkerController = loader.getController();
					ViewWorkerController.loadworker(workerInfo);
					//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
					
					//BookController.loadAnchor(AnchorPaneTabs);
					
	                Scene scene = new Scene(root);
	                stage.setTitle("Worker Personal Information");
	                stage.setScene(scene);
	                stage.show();
	                setStageIcon(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	         	
	         	
	         }
	     
	     });


    }
	 /**class for the book table

	 */
    public static class SubBook {/**Class especially for table of Book*/

       /* private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty AuthorName;
        private final SimpleStringProperty Subject;
        private final SimpleStringProperty ArchiveStatus;*/
        //private CheckBox RowNum;  
        private final String RowNum;
        private final String BookName;
        private final String AuthorName;
        private final String Subject;
        private final String Status;
        private final String BookID;
        
        public SubBook(String rownum,String bookname, String author, String subject, String status, String bookid) {
        //public Book(String bookname, String author, String subject, String status) {
        	/*this.RowNum = new SimpleStringProperty(rownum);
        	this.BookName = new SimpleStringProperty(bookname);
        	this.AuthorName = new SimpleStringProperty(author);
        	this.Subject = new SimpleStringProperty(subject);
        	this.ArchiveStatus = new SimpleStringProperty(status);*/
        	//this.RowNum = new CheckBox();
        	this.RowNum = new String(rownum);
        	this.BookName = new String(bookname);
        	this.AuthorName = new String(author);
        	this.Subject = new String(subject);
        	this.Status = new String(status);
        	this.BookID = new String(bookid);
        }
        public String getBookID() {
        	return BookID;
        }

        public String getRowNum() {
        	return RowNum;
        }
        public void setRowNum(String rownum) {
       // 	this.RowNum = rownum;
        }

        public String getBookName() {
        	return BookName;
        }
        public void setBookName(String bookname) {
     //   	BookName = bookname;
        }
        
        
        public String getAuthorName() {
        	return AuthorName;
        }
        public void setAuthorName(String author) {
      //  	AuthorName = author;
        }
        
  
        public String getSubject() {
        	return Subject;
        }
        public void setSubject(String subject) {
     //   	Subject = subject;
        }
        
        
        public String getStatus() {
        	return Status;
        }
       // public void setStatus(String status) {
      //  	Status = status;
      //  }	
       /* public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getBookName() {
        	return BookName.get();
        }
        public void setBookName(String bookname) {
        	BookName.set(bookname);
        }
        
        
        public String getAuthorName() {
        	return AuthorName.get();
        }
        public void setAuthorName(String author) {
        	AuthorName.set(author);
        }
        
  
        public String getSubject() {
        	return Subject.get();
        }
        public void setSubject(String subject) {
        	Subject.set(subject);
        }
        
        
        public String getArchiveStatus() {
        	return ArchiveStatus.get();
        }
        public void setArchiveStatus(String status) {
        	ArchiveStatus.set(status);
        }*/
       /* public CheckBox getRowNum() {
            return RowNum;
        }
     
        public void setRowNum(CheckBox checkbox) {
            this.RowNum = checkbox;
        }*/
    }
    
	 /**class for the reader table
	
	 */
    public static class SubReader {/**Class especially for table of reader*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty ReaderCardNumber;
        private final SimpleStringProperty Fullname;
        private final SimpleStringProperty CardStatus;
    
        //private CheckBox RowNum;   
        
        public SubReader(String rownum,String readerCardNumber, String fullname, String cardStatus) {
        //public Book(String bookname, String author, String subject, String status) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.ReaderCardNumber = new SimpleStringProperty(readerCardNumber);
        	this.Fullname = new SimpleStringProperty(fullname);
         	this.CardStatus = new SimpleStringProperty(cardStatus);
      
        	//this.RowNum = new CheckBox();
        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getReaderCardNumber() {
        	return ReaderCardNumber.get();
        }
        public void setReaderCardNumber(String readerCardNumber) {
        	ReaderCardNumber.set(readerCardNumber);
        }
        
        
        public String getFullname() {
        	return Fullname.get();
        }
        public void setFullname(String fullname) {
        	Fullname.set(fullname);
        }
        
  
        public String getCardStatus() {
        	return CardStatus.get();
        }
        public void setCardStatus(String cardStatus) {
        	CardStatus.set(cardStatus);
        }
        
      
    }
	 /**class for the worker table
	
	 */
    public static class Subworker {/**Class especially for table of  Worker*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty WorkerNumber;
        private final SimpleStringProperty Fullname;
        private final SimpleStringProperty Premission;
    
        //private CheckBox RowNum;   
        
        public Subworker(String rownum,String WorkerNumber, String fullname, String premission) {
        
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.WorkerNumber = new SimpleStringProperty(WorkerNumber);
        	this.Fullname = new SimpleStringProperty(fullname);
         	this.Premission = new SimpleStringProperty(premission);
      
        	//this.RowNum = new CheckBox();
        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getWorkerNumber() {
        	return WorkerNumber.get();
        }
        public void setWorkerNumber(String workerNumber) {
        	WorkerNumber.set(workerNumber);
        }
        
        
        public String getFullname() {
        	return Fullname.get();
        }
        public void setFullname(String fullname) {
        	Fullname.set(fullname);
        }
        
  
        public String getPremission() {
        	return Premission.get();
        }
        public void setPremission(String Premission) {
        	this.Premission.set(Premission);
        }
        
      
    }

    
    
    /////////////////////////

	/*private static Message lateReturnsReport() {
	ArrayList<Loan> returnLoans = null;
	String booksCatalogNum = (String) messageFromClient.getObj();
	Object returnObj;
	Timestamp today = new Timestamp(System.currentTimeMillis());
	String query = null;
	if (booksCatalogNum.equals(""))
	query = "SELECT * FROM loan";
	else query = "SELECT * FROM loan WHERE CatalogNumber = "+booksCatalogNum;
	try {
	PreparedStatement ps = sqlConnection.connection.prepareStatement(query);
	returnObj = sqlConnection.selectQuery(ps);
	if (returnObj == null)
	throw new SQLException("Cannot find loans details for this book.");
	returnLoans = ClassConverter.convertObjectsToLoan(returnObj);
	

	} catch (SQLException e) {
	// TODO Auto-generated catch block
	return new Message(OperationType.LateReturnsReport, e.getMessage(), ReturnMessageType.Failed);
	}
	catch (Exception e) {
	e.printStackTrace();
	return new Message(OperationType.LateReturnsReport, "Cannot produce late returns report.", ReturnMessageType.Failed);

	}
	
	int delaysCounter = 0;
	int delayPeriod = 0;
	int maxDelay = -1;
	ArrayList<Integer> delaysNumbers = new ArrayList<>();
	ArrayList<Integer> delaysNumbersForHisto = new ArrayList<>();
	for (Loan loan : returnLoans) {
	int tempDiif = 0;
	if (loan.getActualReturnDate() != null) {
	if(loan.getActualReturnDate().after(loan.getReturnDate())) {
	tempDiif = getDaysBetween(loan.getReturnDate(),loan.getActualReturnDate()); //return difference in days
	delayPeriod =+ tempDiif;
	if (tempDiif > maxDelay) maxDelay = tempDiif;
	delaysNumbers.add(tempDiif);
	delaysNumbersForHisto.add(tempDiif);
	delaysCounter++;
	}
	else delaysNumbers.add(0); //in case there is no delay
	}
	else {
	if(today.after(loan.getReturnDate())) {
	tempDiif = getDaysBetween(loan.getReturnDate(),today); //return difference in days
	if (tempDiif > maxDelay) maxDelay = tempDiif;
	delaysNumbers.add(tempDiif);
	delaysNumbersForHisto.add(tempDiif);
	}
	}
	}

	
	double delaysAvg = getAverage(delaysNumbers);
	List<Pair<Integer, String>> returnDelayHistoDetails = distributionHistogram(delaysNumbersForHisto, 10,
	maxDelay);
	int delaysMedian = getMedian(delaysNumbers);
	ArrayList<Object> returnDetailsArr = new ArrayList<>();
	returnDetailsArr.add(delaysAvg);
	returnDetailsArr.add(delaysMedian);
	returnDetailsArr.add(returnDelayHistoDetails);
	
	

	return new Message(OperationType.LateReturnsReport, returnDetailsArr, ReturnMessageType.Succeed);
	}

}
   */
    
    
    
    
    
    
}






