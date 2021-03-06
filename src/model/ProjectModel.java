package model;

public class ProjectModel {
	
	private int projectId;
	private boolean projectIsDeleted;
	private String projectDescription;
	private String projectName;
	private int projectEntryFk;
	private int projectUserStoryFk;
	private int projectSprintFk;
	private int projectCustomerFk
;	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public boolean isProjectIsDeleted() {
		return projectIsDeleted;
	}
	public void setProjectIsDeleted(boolean projectIsDeleted) {
		this.projectIsDeleted = projectIsDeleted;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Override
	public String toString(){
		return this.projectName;
	}
	public int getProjectCustomerFk() {
		return projectCustomerFk;
	}
	public void setProjectCustomerFk(int projectCustomerFk) {
		this.projectCustomerFk = projectCustomerFk;
	}

}
