package model;

/**
 * 
 * @author Robert
 * @date 9-10-2017
 */
public class EntryModel {
	private String entryName;
	private String entryDescription;
	private Exception entryException;
	private EntryStatus entryStatus;
	private String entryDate;
	private String entryStartTime;
	private String entryEndTime;
	private Boolean entryIsLocked;
	private int entry_id;
	
	public int getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
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
