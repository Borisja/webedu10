package model;

import java.util.ArrayList;

/**
 * 
 * @author Robert
 * @date 9-10-2017
 */
public class SprintModel {
	private String sprintName;
	private String sprintStartDate;
	private String sprintEndDate;
	private String sprintDescription;
	private ArrayList<EntryModel> entries;
	
	public String getSprintName() {
		return sprintName;
	}
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}
	public String getSprintStartDate() {
		return sprintStartDate;
	}
	public void setSprintStartDate(String sprintStartDate) {
		this.sprintStartDate = sprintStartDate;
	}
	public String getSprintEndDate() {
		return sprintEndDate;
	}
	public void setSprintEndDate(String sprintEndDate) {
		this.sprintEndDate = sprintEndDate;
	}
	public String getSprintDescription() {
		return sprintDescription;
	}
	public void setSprintDescription(String sprintDescription) {
		this.sprintDescription = sprintDescription;
	}
	public ArrayList<EntryModel> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<EntryModel> entries) {
		this.entries = entries;
	}
}
