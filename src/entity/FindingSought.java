package entity;

public class FindingSought {
    private int BookID;
	private String BookName;
	private String AuthorName;
	private String ArchiveStatus;
	
	public FindingSought(int bookID, String bookName, String authorName, String archiveStatus) {
		BookID = bookID;
		BookName = bookName;
		AuthorName = authorName;
		ArchiveStatus = archiveStatus;
	}
	
	public int getBookID() {
		return BookID;
	}
	public void setBookID(int bookID) {
		BookID = bookID;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public String getArchiveStatus() {
		return ArchiveStatus;
	}
	public void setArchiveStatus(String archiveStatus) {
		ArchiveStatus = archiveStatus;
	}
	
}
