package model;

public class EmployeeModel {
	/**
	 * Employee Model.
	 */
	private int employeeId;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePassword;
	private String employeeEmail;
	
	private boolean employeeIsDeleted;
	
	private String employeeRole;

	public EmployeeModel(int id, boolean isdeleted, String firstname, String lastname, String wachtwoord, String email, String role){
		this.employeeId = id;
		this.employeeFirstName = firstname;
		this.employeeLastName = lastname;
		this.employeePassword = wachtwoord;
		this.employeeEmail = email;
		this.employeeRole = role;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeFirstname() {
		return employeeFirstName;
	}

	public String getEmployeeAchternaam() {
		return employeeLastName;
	}
	public void setEmployeeFirstname(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public void setEmployeeAchternaam(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeWachtwoord() {
		return employeePassword;
	}
	public void setEmployeeWachtwoord(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeRol() {
		return employeeRole;
	}
	public void setEmployeeRol(String employeeRol) {
		this.employeeRole = employeeRol;
	}
	
	public boolean isEmployeeIsDeleted() {
		return employeeIsDeleted;
	}

	public void setEmployeeIsDeleted(boolean employeeIsDeleted) {
		this.employeeIsDeleted = employeeIsDeleted;
	}
}
