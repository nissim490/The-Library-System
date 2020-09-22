package entity;

import java.util.ArrayList;

import common.Functions;

public class DisciplinaryInquiryReport {
	private String DisciplinaryInquiryReportID;
	private String ReaderCardNumber;
	private String WorkerNumber;
	private String Content;
	private String Verdict;// {Active,Frozen,Locked};
	private String Date;
	/**parameters of class*/

	public DisciplinaryInquiryReport(String disciplinaryinquiryreportID, String readerCardNumber, String workerNumber,
			String content, String verdict, String date)
	/**Main constructor of the class*/
	{
		super();
		DisciplinaryInquiryReportID = disciplinaryinquiryreportID;
		ReaderCardNumber = readerCardNumber;
		WorkerNumber = workerNumber;
		Content = content;
		Verdict = verdict;
		Date = date;
	}
	

	public static void AddNewDisciplinaryInquiryReport(String disciplinaryInquiryReportID, String readerCardNumber, String workerNumber,
			String content, String verdict, String date, String NewDelaysAmount)
	/**add new disciplinary inquiry report to database*/ 
	{
		
		
		 ArrayList<String> key = new ArrayList<String>();		  
	      key.add(disciplinaryInquiryReportID);	      
	      key.add(readerCardNumber);	      
	      key.add(workerNumber);	      
	      key.add(content);      
	      key.add(verdict);	      
	      key.add(date);
	      key.add(NewDelaysAmount);
	  	  Functions.askFromDB("INSERT INTO g17db.tbldisciplinaryinquiryreport (`DisciplinaryInquiryReportID`, `ReaderCardNumber`, `WorkerNumber`, `Content`, `Verdict`, `Date`,`NewDelaysAmount`) values  (?,?,?,?,?,?,?);",key);
	}

	public static void ReceiveDisciplinaryInquiryReportInformation(String DisciplinaryInquiryReportID)
	/**get information of specific disciplinary inquiry report from database by DisciplinaryInquiryReportID */
	{
		
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(DisciplinaryInquiryReportID);
	      askForResult.add("*");      
	      Functions.askFromDB("SELECT * FROM g17db.tbldisciplinaryinquiryreport WHERE DisciplinaryInquiryReportID = ?;",key,askForResult);
		
		
		
	}
	


	public static void RemoveDisciplinaryInquiryReport(String DisciplinaryInquiryReportID) 
	/**remove disciplinary inquiry report from database*/ 
	{
		ArrayList<String> key = new ArrayList<String>();
	    key.add(DisciplinaryInquiryReportID);
    	 Functions.askFromDB("DELETE * FROM  g17db.tbldisciplinaryInquiryReport WHERE DisciplinaryInquiryReportID = ?;",key);
	}
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getDisciplinaryInquiryReportID() {
		return DisciplinaryInquiryReportID;
	}

	public String getReaderCardNumber() {
		return ReaderCardNumber;
	}


	public String getWorkerNumber() {
		return WorkerNumber;
	}

	
	public String getContent() {
		return Content;
	}

	/**
	 * 
	 * @param Content  d
	 */
	public void setContent(String Content) throws Exception {
		 if(Content==null)		
			 throw new NullPointerException("The Content is null");
		if(Content == " ")
			 throw new Exception("The content of the message is empty");
		 this.Content = Content;
	}

	public String getVerdict() {
		return Verdict;
	}


	public String getDate() {
		return Date;
	}

	/**
	 * 
	 * @param Date g
	 */
public void setDate(String Date) throws Exception {
	 if(Date==null)		
		 throw new NullPointerException("Date is null");
		if(Date=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(Date))
		{	 
			this.Date = Date;
		  
		}
	}
	public void setVerdict(String Verdict) throws Exception {
		 if(Verdict==null)		
			 throw new NullPointerException("Verdict is null");
		
		 if(Verdict!="Active"||Verdict!="Frozen"||Verdict!="Locked")		
			 this.Verdict = Verdict;
				  
				else throw new Exception("Verdict can be Active or Locked or Frozen");
	
		
	}
	public void setWorkerNumber(String WorkerNumber) throws Exception {
		 if(WorkerNumber==null)		
			 throw new NullPointerException("Worker Number is null");
		 if(!Functions.isNumeric(WorkerNumber) )	
			 throw new Exception("Worker Number  must be a number");
		int number = Integer.parseInt(WorkerNumber);
		 
		 if(number<0)		
			 throw new Exception("Worker Number must be greater than or equal to zero");
		
	 
		 this.WorkerNumber = WorkerNumber;
	 
	}
	public void setReaderCardNumber(String ReaderCardNumber) throws Exception {
		 if(ReaderCardNumber==null)		
			 throw new NullPointerException("Reader CardNumber is null");
		 if(!Functions.isNumeric(ReaderCardNumber) )	
			 throw new Exception("Reader CardNumber  must be a number");
		int number = Integer.parseInt(ReaderCardNumber);
		 if(number<0)		
			 throw new Exception("Reader CardNumber must be greater than or equal to zero");
		
	 
		 this.ReaderCardNumber = ReaderCardNumber;
	 
	}
	public void setDisciplinaryInquiryReportID(String DisciplinaryInquiryReportID) throws Exception {
		 if(DisciplinaryInquiryReportID==null)		
			 throw new NullPointerException("Disciplin aryInquiry Report ID  is null");
		 if(!Functions.isNumeric(DisciplinaryInquiryReportID) )	
			 throw new Exception("Disciplin aryInquiry Report ID  must be a number");
		int number = Integer.parseInt(DisciplinaryInquiryReportID);
		
		 if(number<0)		
			 throw new Exception("Disciplin aryInquiry Report ID must be greater than or equal to zero");
		
	 
		 this.DisciplinaryInquiryReportID = DisciplinaryInquiryReportID;
	 
	}
}