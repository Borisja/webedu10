package model;

/**
 * 
 * @author Robert
 * @date 9-10-2017
 */
public class EntryModel {
	private int entryId;
	private String entryName;
	private String entryDescription;
	private Exception entryException;
	private EntryStatus entryStatus;
	private String entryDate;
	private String entryStartTime;
	private String entryEndTime;
	private Boolean entryIsLocked;
	
	public int getEntryId() {
		return entryId;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getEntryDescription() {
		return entryDescription;
	}
	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}
	public Exception getEntryException() {
		return entryException;
	}
	public void setEntryException(Exception entryException) {
		this.entryException = entryException;
	}
	public EntryStatus getEntryStatus() {
		return entryStatus;
	}
	public void setEntryStatus(EntryStatus entryStatus) {
		this.entryStatus = entryStatus;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntryStartTime() {
		return entryStartTime;
	}
	public void setEntryStartTime(String entryStartTime) {
		this.entryStartTime = entryStartTime;
	}
	public String getEntryEndTime() {
		return entryEndTime;
	}
	public void setEntryEndTime(String entryEndTime) {
		this.entryEndTime = entryEndTime;
	}
	public Boolean getEntryIsLocked() {
		return entryIsLocked;
	}
	public void setEntryIsLocked(Boolean entryIsLocked) {
		this.entryIsLocked = entryIsLocked;
	}
}
