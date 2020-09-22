package Controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.mysql.fabric.xmlrpc.base.Array;

import Controllers.LibraryManagerHomepageController.SubReader;
import common.Functions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewBooksDelaysReportByManagerController implements Initializable {
	ArrayList<ArrayList<String>> resultborrow;
	ObservableList<SubDelay> MylistSubDelay = FXCollections.observableArrayList();

	static int part;
	public int Len;
	int[] newdcResult;
	public ArrayList<Double> avgArraylist = new ArrayList<Double>();
	public ArrayList<Double> medianArray = new ArrayList<Double>();
	public int[][] BookDC;
	public double[] MaxParts; 
	public int[] BookDelayCnt; 
	public ArrayList<String> BookIDArray = new ArrayList<String>();
	public int[] Booklistsizes;
	
	ArrayList<ArrayList<String>> resultbook;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SubDelay> tblBookDelays;

    @FXML
    private TableColumn<SubDelay, String> tblcRowNum;

    @FXML
    private TableColumn<SubDelay, String> tblcBookCatalogNum;

    @FXML
    private TableColumn<SubDelay, String> tblcBookName;

    @FXML
    private TableColumn<SubDelay, String> tblcAverage;

    @FXML
    private TableColumn<SubDelay, String> tblcMedian;

    @FXML
    private TableColumn<SubDelay, String> tblcAverageD;

    @FXML
    private TableColumn<SubDelay, String> tblcMedianD;
    
    /**
     * viewing the selected report in the row
     * @param 
     * @param ActionEvent
     * @return void
     */
    @FXML
    void ViewSelected(ActionEvent event) {

             	 int pos = tblBookDelays.getSelectionModel().getSelectedIndex();
             	  	// @SuppressWarnings("unchecked")
             	//ArrayList<String> list = 
             	 String bookID = tblBookDelays.getItems().get(pos).getBookID();

 	
             	//int size5 = BookDC[0].length;


//int[] AmountDC,int[] DurationDC,double AmountMaxParts,double DurationMaxParts
             	try {
                	Stage stage = new Stage(); 
                    Parent root;                   
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/ViewDelayedHistogram.fxml"));
					root = loader.load();
					ViewDelayedHistogramController DelayedHistogramController = loader.getController();
					DelayedHistogramController.loadHistogram(newdcResult,BookDC[pos],part,MaxParts[pos],(int)(((double)(pos+1)/Len)*10));
					System.out.println("pos+1: "+(pos+1)+", len+1: "+(Len)+" = "+(int)(((double)(pos+1)/Len)*10));
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
    /**
     * init window
     * @param url
     * @param rb
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	tblBookDelays.setEditable(true);
    	tblcRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcBookCatalogNum.setCellValueFactory(new PropertyValueFactory<>("BookCatalogNumber"));
    	tblcBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcAverage.setCellValueFactory(new PropertyValueFactory<>("AvgAmount"));
    	tblcMedian.setCellValueFactory(new PropertyValueFactory<>("MedianAmount"));
    	tblcAverageD.setCellValueFactory(new PropertyValueFactory<>("AvgDuration"));
    	tblcMedianD.setCellValueFactory(new PropertyValueFactory<>("MedianDuration"));
    	
    	
    	ArrayList<String> key = new ArrayList<String>();
        ArrayList<String> askForResult = new ArrayList<String>();
        //String sql = "SELECT * FROM g17db.tblbookborrow;";

        String sql = "Select  b.BookID,b.BookName,b.AuthorName,bb.BorrowingDate, bb.ReturnDate,bb.ActualReturnDate,bb.BorrowedDelayed,bb.BorrowedMissUsed From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bb.BookCopyID=bc.BookCopyID order by b.BookID;";
        askForResult.add("*");
        //askForResult.add("BookID");
        //askForResult.add("BookName");
        //askForResult.add("AuthorName");
        //askForResult.add("BorrowingDate");
        //askForResult.add("ReturnDate");
        //askForResult.add("ActualReturnDate");
        //askForResult.add("BorrowedDelayed");
        //askForResult.add("BorrowedMissUsed");

        //Select bb.BorrowingDate, bb.ReturnDate,bb.ActualReturnDate,bb.BorrowedDelayed,bb.BorrowedMissUsed , b.Popularity From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bb.BookCopyID=bc.BookCopyID;
        //Select b.Popularity From g17db.tblbook as b, g17db.tblbookborrow as bb, g17db.tblbookcopy as bc where bc.BookID=b.BookID and bc.BookCopyID='BC000054' and bb.BookCopyID=bc.BookCopyID;
        resultborrow = Functions.askFromDB(sql ,key,askForResult);
        int ActiveBorrow = resultborrow.size();


        ArrayList<String> BookIDArray = new ArrayList<String>();
        ArrayList<Long> BookResultArray = new ArrayList<Long>();
        ArrayList<ArrayList<Long>> ResultArray = new ArrayList<ArrayList<Long>>();

        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        //ArrayList<Integer> BookDelayCnt = new ArrayList<Integer>();
        String BookPointer = resultborrow.get(0).get(0);
        BookIDArray.add(BookPointer);
        Date d1 = null;
        Date d2 = null;
        Long diff;
        int[] BookDelayCnt = new int[30];

        long diffDays;

        int arrayPointer = 0;

        for(int i = 0;i<ActiveBorrow;i++) {

            if(!(resultborrow.get(i).get(5).equals(""))) {
                try {
                    d1 = format2.parse(resultborrow.get(i).get(4));
                    d2 = format2.parse(resultborrow.get(i).get(5));

                    //in milliseconds
                    diff = d2.getTime() - d1.getTime();
                    //

                    diffDays = diff / (24 * 60 * 60 * 1000);
                    // System.out.print("\nnot empty: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+diffDays);
                    if(resultborrow.get(i).get(0).equals(BookPointer)) {
   
                        if(diffDays>=0) {
                        	BookResultArray.add(diffDays);
                            BookDelayCnt[arrayPointer]++;
                        }

                    }
                    else {
                        arrayPointer++;
                        ResultArray.add(BookResultArray);
                        BookPointer = resultborrow.get(i).get(0);
                        BookIDArray.add(BookPointer);
                        BookResultArray = new ArrayList<Long>();

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                if(resultborrow.get(i).get(7).equals("Yes") || (resultborrow.get(i).get(7).equals("No") && resultborrow.get(i).get(6).equals("No"))){
                    //System.out.print("\nempty1: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+0);
                    if(resultborrow.get(i).get(0).equals(BookPointer)) {
                        diffDays = 0;
                        BookResultArray.add(diffDays);


                    }
                    else {
                        arrayPointer++;
                        ResultArray.add(BookResultArray);
                        BookPointer = resultborrow.get(i).get(0);
                        BookIDArray.add(BookPointer);
                        BookResultArray = new ArrayList<Long>();

                    }
                }
                else {
                    try {
                        d1 = format2.parse(resultborrow.get(i).get(4));
                        d2 = format.parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

                        //in milliseconds
                        diff = d2.getTime() - d1.getTime();

                        diffDays = diff / (24 * 60 * 60 * 1000);
                        //System.out.print("\nempty2: d2:"+d2.getTime()+" - d1:"+d1.getTime()+" = "+diffDays);
                        if(resultborrow.get(i).get(0).equals(BookPointer)) {
                            BookResultArray.add(diffDays);
                            if(diffDays>=0) {
                                BookDelayCnt[arrayPointer]++;
                            }


                        }
                        else {
                            arrayPointer++;
                            ResultArray.add(BookResultArray);
                            BookPointer = resultborrow.get(i).get(0);
                            BookIDArray.add(BookPointer);
                            BookResultArray = new ArrayList<Long>();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

System.out.println("BookIDArray size:"+BookIDArray.size());
		this.Len = BookIDArray.size()-1;
        ArrayList<Double> returnList = new ArrayList<Double>();
        Long sum;
        double returnSum;
        for(int i =0; i <ResultArray.size();i++) {
            sum = 0L;
            for(int j=0; j < ResultArray.get(i).size();j++) {
                sum += ResultArray.get(i).get(j);
            }
            returnSum = (double)sum;
            returnList.add(returnSum/ResultArray.get(i).size());
        }


        //for(int i = 0;i<returnList.size();i++) {
        //		System.out.print(i+": "+returnList.get(i)+", ");
        //}


        ArrayList<Double> returnList2 = new ArrayList<Double>();
        double temp;
        int index;
        for(int i =0; i <ResultArray.size();i++) {
            Collections.sort(ResultArray.get(i));
        }

        System.out.println("\n"+ResultArray);
        for(int i =0; i <ResultArray.size();i++) {
            if(ResultArray.get(i).size()> 0){
                if(ResultArray.get(i).size()%2==1) {
                    index = (ResultArray.get(i).size())/2;
                    temp = ResultArray.get(i).get(index);
                    returnList2.add(temp);
                }

                else {
                    index = ResultArray.get(i).size()/2;
                    temp = ResultArray.get(i).get(index);
                    temp += ResultArray.get(i).get(index-1);
                    temp /=2;
                    returnList2.add(temp);
                }
            }
        }
        //System.out.println("\nmedianOffilleds"+returnList2);

        this.BookDC = new int[ResultArray.size()][10];

        int[] Booklistsizes = new int[ResultArray.size()];

        for(int i = 0;i<ResultArray.size();i++) {
            Booklistsizes[i] = ResultArray.get(i).size();
        }


        double[] MaxParts = new double[ResultArray.size()]; //12
        for(int i = 0;i<ResultArray.size();i++) {
            MaxParts[i] = ResultArray.get(i).get(ResultArray.get(i).size()-1)/10.0;//v
            System.out.println(i+", "+(ResultArray.get(i).size()-1)+" = "+MaxParts[i]);
        }
        this.MaxParts = MaxParts;

       /* double PartPointer = 0.0;
        for(int k = 0;k<ResultArray.size();k++) {//12
            for(int i = 0;i<ResultArray.get(k).size();i++) {
                for(int j = 0;j<10;j++) { // 0-0.2  0.2-0.4  0.4-0.6  0.6-0.8  0.8-1  1-1.2  1.2-1.4  1.4-1.6  1.6-1.8  1.8-2
                    //   3                                        1                                 1
                    System.out.print("\nk - "+k+", i - "+i+", j - "+j+" = "+ResultArray.get(k).get(i)+", maxpart is: "+MaxParts[k]*j);
                    if(((MaxParts[k]*j)<ResultArray.get(k).get(i)) && ((MaxParts[k]*(j+1))>=ResultArray.get(k).get(i))) {
                        BookDC[k][j]++; //[10 parts][amount of books]
                    }
                    else
                    {
                        if(j==0) {
                            if(ResultArray.get(k).get(i).equals((long)0)) {
                                BookDC[k][j]++;
                            }
                        }
                        if(j==9) {
                            if(ResultArray.get(k).get(i).equals((long)(MaxParts[k]*10))) {
                                BookDC[k][j]++;
                            }
                        }
                    }
                }
            }
        }*/

        
        
        //int[] PartBookDC = new int[10];

        //double dMaxParts = MaxParts[x]; //2 -> 0-2, 2-4, 4-6, 6-8.....18-20 -- 7
       //double PartPointer = 0.0;   0-0.3  0.3-0.6  0.9-1.2  1.2-1.5  1.8-2.1   2.1-2.4   2.4-2.7  2.7-3
        						//     0      0        1        0        0         0         0        1
        for(int x = 0;x<BookIDArray.size()-1;x++) {
        	for(int i = 0;i<BookDelayCnt[x] ;i++) {
                for(int j = 0;j<10;j++) {
                	System.out.print("\nx - "+x+", i - "+i+", j - "+j+" = "+ResultArray.get(x).get(i)+", maxpart is: "+MaxParts[x]*j);
                    if(((MaxParts[x]*j)<ResultArray.get(x).get(i)) && ((MaxParts[x]*(j+1))>=ResultArray.get(x).get(i))) {
                    	BookDC[x][j]++;
                    }
                    else
                    {
                        if(j==0) {
                            if(ResultArray.get(x).get(i).equals((long)0)) {
                                BookDC[x][j]++;
                            }
                        }
                        if(j==9) {
                            if(ResultArray.get(x).get(i).equals((long)(MaxParts[x]*10))) {
                                BookDC[x][j]++;
                            }
                        }
                    }
                }
            }
        }
    	
    	
        this.BookDC = BookDC;
        
        
        //double[] AmountMaxParts = new double[];
        //DemandedDatesResultArray.get(demandedCnt2-1)/10.0;
        
        ArrayList<Double> x = medianOfPositivefilleds(ResultArray);
        System.out.println("xxx:"+x);
    	
    	int i = 0,size;

    	//MylistSubDelay.clear();
	  	ArrayList<String> key2 = new ArrayList<String>();

	    ArrayList<String> askForResult2 = new ArrayList<String>();

		  sql = "SELECT * FROM g17db.tblbook Order By BookID;";
		  askForResult2.add("BookID");
		  askForResult2.add("CatalogNumber");
		  askForResult2.add("BookName");
		  
		  String rownum = "";
		  String bookname = "";
		  String bookid = "";
		  String catalognumber = "";
		  double avgdelaysduration = 0.0, avgdelaysamount= 0.0;
		  String mediandelaysduration = "", mediandelaysamount = "";
		  int cnt;
		  System.out.println(BookIDArray);
		  resultbook = Functions.askFromDB(sql ,key2,askForResult2);
		  size = resultbook.size();
		  //MylistSubDelay.add(new SubDelay("1", "2", "3", "4", "5","7", "7"));
  		/*rownum = String.valueOf(1);
  		catalognumber = resultbook.get(0).get(0);
          bookname = resultbook.get(0).get(2);
          avgdelaysduration = returnList.get(0); 
          mediandelaysduration = String.valueOf(returnList2.get(0));
          avgdelaysamount = BookDelayCnt[0]/BookIDArray.size();
          mediandelaysamount = String.valueOf(x.get(0));//String.valueOf(ResultArray.get(0).get((int)(BookIDArray.size()/2)));
         // MylistSubDelay.add(new SubDelay("1", "2", "3", "4", "5","7", "7"));   
         MylistSubDelay.add(new SubDelay(rownum, catalognumber, bookname, String.valueOf(avgdelaysamount), mediandelaysamount,String.valueOf(avgdelaysduration), mediandelaysduration));  
		  
         rownum = String.valueOf(2);
   		catalognumber = resultbook.get(1).get(0);
           bookname = resultbook.get(1).get(2);
           avgdelaysduration = returnList.get(1); 
           mediandelaysduration =String.valueOf(returnList2.get(1));
           avgdelaysamount = BookDelayCnt[1]/BookIDArray.size();
           mediandelaysamount = String.valueOf(x.get(1)); //String.valueOf(ResultArray.get(1).get((int)(ResultArray.size()/2)));
          // MylistSubDelay.add(new SubDelay("1", "2", "3", "4", "5","7", "7"));   
          MylistSubDelay.add(new SubDelay(rownum, catalognumber, bookname, String.valueOf(avgdelaysamount), mediandelaysamount,String.valueOf(avgdelaysduration), mediandelaysduration));  
 		  */
		  System.out.println("BookDelayCnt[0]"+BookDelayCnt[0]);
		  System.out.println("BookDelayCnt[1]"+BookDelayCnt[1]);
		  System.out.println("BookDelayCnt[2]"+BookDelayCnt[2]);
		  System.out.println("BookDelayCnt[3]"+BookDelayCnt[3]);
		  System.out.println("BookDelayCnt[4]"+BookDelayCnt[4]);
		  System.out.println("BookDelayCnt[5]"+BookDelayCnt[5]);
		  System.out.println("BookDelayCnt[6]"+BookDelayCnt[6]);
		  System.out.println("BookDelayCnt[7]"+BookDelayCnt[7]);
		  System.out.println("BookDelayCnt[8]"+BookDelayCnt[8]);
		  System.out.println("BookDelayCnt[9]"+BookDelayCnt[9]);
		  System.out.println("BookDelayCnt[10]"+BookDelayCnt[10]);
		  System.out.println("BookDelayCnt[11]"+BookDelayCnt[11]);
		  System.out.println("BookDelayCnt[12]"+BookDelayCnt[12]);
		  System.out.println("BookDelayCnt[13]"+BookDelayCnt[13]);

		  System.out.println("BookIDArray: "+BookIDArray);
		  System.out.println("\nreturnList: $"+returnList);
		  System.out.println("returnList2:"+returnList2);
		  System.out.println("resultbook:"+resultbook);
		  System.out.println("xxx"+x);

  		    for(i=0;i<resultbook.size();i++) {
  		    	System.out.println("mainLoop"+i);
  		    	for(int j = 0;j<BookIDArray.size()-1;j++ ) {
  	  		    	//System.out.println("subLoop"+i);
  		    		if(resultbook.get(i).get(0).equals(BookIDArray.get(j))) {
  		    			System.out.println("subLoop"+i);
  		    			rownum = String.valueOf(i);
  			   		  catalognumber = resultbook.get(i).get(1);
  			           bookname = resultbook.get(i).get(2);
  			           
  			           avgdelaysduration = returnList.get(j); 
  			           mediandelaysduration =String.valueOf(returnList2.get(j));
  			           avgdelaysamount = BookDelayCnt[j];
  			          // System.out.println("x.get(j)"+x.get(j));
  			            
  			           Double sos = x.get(j);
  			           System.out.println("sos"+sos);
  			          // System.out.println("mediandelaysamount:"+mediandelaysamount.substring(0,1));
  			          MylistSubDelay.add(new SubDelay(rownum, catalognumber, bookname, String.valueOf(avgdelaysamount), sos.toString(),String.valueOf(avgdelaysduration), mediandelaysduration,BookIDArray.get(j)));  
  		    		}
  		    	}
		      
  		    }
		  
        	
        	tblBookDelays.setItems(MylistSubDelay);
            
        	newdcResult = new int[10];
        	newdcResult = AmountDCfunc(BookDelayCnt);
        
   
    }
    /**
     * creates the amount DC array for the report
     * @param array g
     * 
     * @return int[]
     */
    public static int[] AmountDCfunc(int[] array){
    	
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	
    	for(int i=0; i <array.length;i++) {
    		arr.add(array[i]);
    	}	
    	ArrayList<Integer> newArr = new ArrayList<Integer>(10);
    	for(int i=0;i<10;i++)newArr.add(0);
    	if(arr.size()>0) {
    	int max = arr.get(0);
    	for(int i =1; i < arr.size(); i++) {//get the max
    		if(arr.get(i) > max)max = arr.get(i);
    	}
    	 part = max/10;
    	int index;
    	
    	for(int i =0; i <arr.size();i++) {
    		if(arr.get(i)%2==1)
    		{
    			 index = (int)Math.floor(arr.get(i)/part);
    		}
    		else if(arr.get(i)!=0) {
    			 index = (arr.get(i)/part)-1;
    		}
    		else {
    			 index =0;
    		}
    		
    		System.out.println("arr.get(i)"+arr.get(i));
    		System.out.println("part"+part);
    		System.out.println("index"+index);
    		newArr.set(index, (newArr.get(index))+1);
    	}

    	}

    	int[] ReturnArr = new int[newArr.size()];
    	for(int i=0; i <ReturnArr.length;i++)
    	{
    		ReturnArr[i]=newArr.get(i);
    	}
    	
    	return ReturnArr;
      }
    /**
     * creates the median arraylist for the report
     * @param ArrayList<ArrayList<Long>>
     * @param 
     * @return ArrayList<Double>
     */
    static ArrayList<Double> medianOfPositivefilleds(ArrayList<ArrayList<Long>> arr){
    	System.out.println("medianOfPositivefilleds[1]"+arr);
		ArrayList<Double> returnList = new ArrayList<Double>();	
		double temp;
		int index;
		for(int i =0; i <arr.size();i++) {	
			Collections.sort(arr.get(i));
		}	
		System.out.println(arr);
	
		
		for(int i =0; i <arr.size();i++) {	
			 Iterator<Long> iterator = arr.get(i).iterator();
		      
	         while(iterator.hasNext()) {
	         Long next = iterator.next();
	         if(next < 0) {
	            iterator.remove();
	         }
	      }
		}
		
		for(int i =0; i <arr.size() ; i++) {
			if(arr.get(i).size()==0)arr.get(i).add(0L);
		}
		
		System.out.println(arr);
		
		for(int i =0; i <arr.size();i++) {	
			if(arr.get(i).size()> 0){
				if(arr.get(i).size()%2==1) {
					index = (arr.get(i).size())/2;
					temp = arr.get(i).get(index);
					returnList.add(temp);
				}
				
				else {
					index = arr.get(i).size()/2;
					temp = arr.get(i).get(index);
					temp += arr.get(i).get(index-1);
					temp /=2;
					returnList.add(temp);
				}
			}
		}	
    	System.out.println("medianOfPositivefilleds[2]"+arr);
		 return returnList;
}
    /**
     * class for the table
     *
     */
    public static class SubDelay {
    	
        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookCatalogNumber;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty AvgAmount;
        private final String MedianAmount;
        private final SimpleStringProperty AvgDuration;
        private final SimpleStringProperty MedianDuration;
        private final SimpleStringProperty BookID;
        //private CheckBox RowNum;   
        
        public SubDelay(String rownum,String bookCatalogNumber, String bookName, String avgAmount, String medianAmount, String avgDuration, String medianDuration, String bookID) {
        //public Book(String bookname, String author, String subject, String status) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.BookCatalogNumber = new SimpleStringProperty(bookCatalogNumber);
        	this.BookName = new SimpleStringProperty(bookName);
         	this.AvgAmount = new SimpleStringProperty(avgAmount);
         	this.MedianAmount = new String(medianAmount);
        	this.AvgDuration = new SimpleStringProperty(avgDuration);
         	this.MedianDuration = new SimpleStringProperty(medianDuration);
         	this.BookID = new SimpleStringProperty(bookID);
        	//this.RowNum = new CheckBox();
        }
        
        public String getBookID() {
        	return BookID.get();
        }
        
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getBookCatalogNumber() {
        	return BookCatalogNumber.get();
        }
        public void setBookCatalogNumber(String bookCatalogNumber) {
        	BookCatalogNumber.set(bookCatalogNumber);
        }
        public String getBookName() {
        	return BookName.get();
        }
        public void setBookName(String bookName) {
        	BookName.set(bookName);
        }
        public String getAvgAmount() {
        	return AvgAmount.get();
        }
        public void setAvgAmount(String avgAmount) {
        	AvgAmount.set(avgAmount);
        }
        public String getMedianAmount() {
        	return MedianAmount;
        }
        
        public String getAvgDuration() {
        	return AvgDuration.get();
        }
        public void setAvgDuration(String avgDuration) {
        	AvgDuration.set(avgDuration);
        }
        public String getMedianDuration() {
        	return MedianDuration.get();
        }
        public void setMedianDuration(String medianDuration) {
        	MedianDuration.set(medianDuration);
        }
      
    }
  }
    
   // public void loadDelayReport(ArrayList<Double> avgArraylist, ArrayList<Double> medianArray, int[][] bookDC, double[] maxParts, int[] bookDelayCnt, ArrayList<String> bookIDArray, int[] booklistsizes) {
    //	this.avgArraylist = avgArraylist;
    	//this.medianArray = medianArray;
    	//this.BookDC = bookDC;
    	//this.MaxParts = maxParts; 
    //	this.BookDelayCnt = bookDelayCnt; 
    //	this.BookIDArray = bookIDArray;
    //	this.Booklistsizes = booklistsizes;

    	
    	


    	
//}
